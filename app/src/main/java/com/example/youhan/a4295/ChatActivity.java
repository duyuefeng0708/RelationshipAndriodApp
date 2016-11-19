package com.example.youhan.a4295;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Iterator;

public class ChatActivity extends AppCompatActivity {
    private TextView tvConversation;
    private EditText txtInput;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference chatRef;
    private String user;
    private String partner;
    private String temp_key;
    private String temp_key2;
    private String chat_message;
    private String chat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setTitle("Chat");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent i = getIntent();
        user = i.getStringExtra("username");
        partner = i.getStringExtra("partnername");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        chatRef = database.getReference("Chat");
        tvConversation = (TextView)findViewById(R.id.tvConversation);
        txtInput = (EditText)findViewById(R.id.etTextInput);

        ImageButton m1=(ImageButton)findViewById(R.id.button1);
        ImageButton m2=(ImageButton)findViewById(R.id.button2);
        ImageButton m3=(ImageButton)findViewById(R.id.button3);
        ImageButton m4=(ImageButton)findViewById(R.id.button4);
        ImageButton m5=(ImageButton)findViewById(R.id.button5);

        m1.setBackgroundColor(Color.parseColor("#808080"));
        m2.setBackgroundColor(Color.parseColor("#808080"));
        m3.setBackgroundColor(Color.parseColor("#808080"));
        m4.setBackgroundColor(Color.parseColor("#808080"));
        m5.setBackgroundColor(Color.parseColor("#808080"));

        m5.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(ChatActivity.this, Profile.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m4.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(ChatActivity.this, Location.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m2.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(ChatActivity.this, Timeline.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m1.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(ChatActivity.this, MainActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        //iterator for all
        chatRef.child(user+""+partner).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterator iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    chat_message = ((DataSnapshot)iterator.next()).getValue().toString();
                    chat_name = ((DataSnapshot)iterator.next()).getValue().toString();

                    tvConversation.append(chat_name+" : "+chat_message+"\n");
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {Iterator iterator = dataSnapshot.getChildren().iterator();
                Iterator iterator2 = dataSnapshot.getChildren().iterator();
                while (iterator2.hasNext()) {
                    chat_message = ((DataSnapshot)iterator2.next()).getValue().toString();
                    chat_name = ((DataSnapshot)iterator2.next()).getValue().toString();

                    tvConversation.append(chat_name+" : "+chat_message+"\n");
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //iterator only for the node
/*
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(user+""+partner)) {
                    Iterator iterator = dataSnapshot.child(user + "" + partner).getChildren().iterator();
                    while (iterator.hasNext()) {
                        DataSnapshot temp = (DataSnapshot)iterator.next();
                        chat_message = (temp).child("msg").getValue().toString();
                        chat_name = (temp).child("name").getValue().toString();

                        tvConversation.append( chat_name+ " : " +chat_message+"\n");
                    }
                }

                if(dataSnapshot.hasChild(partner+""+user)) {
                    Iterator iterator = dataSnapshot.child(partner + "" + user).getChildren().iterator();
                    while (iterator.hasNext()) {
                        chat_message = (String) ((DataSnapshot) iterator.next()).getValue();
                        chat_name = (String) ((DataSnapshot) iterator.next()).getValue();

                        tvConversation.append(chat_name + " : " + chat_message);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    public void btSendClick(View v){
        temp_key = chatRef.child(user+""+partner).push().getKey();
        temp_key2 = chatRef.child(partner+""+user).push().getKey();
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("name",user);
        map.put("msg", txtInput.getText().toString());
        chatRef.child(user+""+partner).child(temp_key).updateChildren(map);
        chatRef.child(partner+""+user).child(temp_key2).updateChildren(map);
        txtInput.setText("");
    }
}
