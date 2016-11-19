package com.example.youhan.a4295;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PartnerConnectionActivity extends AppCompatActivity {
    private EditText txtPartnerName;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference chatRef;
    private String partnerName = null;
    private String ownName = null;
    HashMap<String, Object> chatChannel = new HashMap<>();
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_connection);
        i = getIntent();

        setTitle("Partner Connection");
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_p);
        setSupportActionBar(myToolbar);

        txtPartnerName = (EditText)findViewById(R.id.etPartnerName);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        chatRef = database.getReference("Chat");
    }

    public void btPartnerComplete(View v){
        ownName = i.getExtras().getString("username");
        partnerName = txtPartnerName.getText().toString();
        HashMap<String, Object> username = new HashMap<>();
        HashMap<String, Object> userDetails = new HashMap<>();
        userDetails.put("Partner", partnerName);
        username.put(ownName,userDetails);

        chatChannel.put(partnerName+""+ownName, "");
        chatChannel.put(ownName+""+partnerName, "");

        try{
            myRef.updateChildren(username);
        }
        catch (Error e){
            Toast.makeText(PartnerConnectionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(partnerName)){
                    if(dataSnapshot.child(partnerName).child("Partner").getValue().equals(ownName)){
                        chatRef.updateChildren(chatChannel);
                        Intent i = new Intent(PartnerConnectionActivity.this, MainActivity.class);
                        i.putExtra("username",ownName);
                        i.putExtra("partnername",partnerName);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(PartnerConnectionActivity.this,"Wait for your partner to add you...",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(PartnerConnectionActivity.this,"Wait for your partner to create an account...",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PartnerConnectionActivity.this,"Database error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
