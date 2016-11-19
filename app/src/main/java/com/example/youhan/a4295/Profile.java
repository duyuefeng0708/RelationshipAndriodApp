package com.example.youhan.a4295;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private TextView userName;
    private TextView loverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        Intent i = getIntent();
        final String user = i.getStringExtra("username");
        final String partner = i.getStringExtra("partnername");

        userName = (TextView) findViewById(R.id.tvUserName);
        loverName = (TextView) findViewById(R.id.tvLoverName);

        userName.setText(user);
        loverName.setText(partner);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_p);
        setSupportActionBar(myToolbar);

        ImageButton m1=(ImageButton)findViewById(R.id.button1_p);
        ImageButton m2=(ImageButton)findViewById(R.id.button2_p);
        ImageButton m3=(ImageButton)findViewById(R.id.button3_p);
        ImageButton m4=(ImageButton)findViewById(R.id.button4_p);
        ImageButton m5=(ImageButton)findViewById(R.id.button5_p);

        m1.setBackgroundColor(Color.parseColor("#808080"));
        m2.setBackgroundColor(Color.parseColor("#808080"));
        m3.setBackgroundColor(Color.parseColor("#808080"));
        m4.setBackgroundColor(Color.parseColor("#808080"));
        m5.setBackgroundColor(Color.parseColor("#808080"));

        LinearLayout l1 = (LinearLayout) findViewById(R.id.to_person);

        l1.setOnClickListener(new LinearLayout.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Profile.this, Person.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m1.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Profile.this, MainActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m2.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Profile.this, Timeline.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m4.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Profile.this, Location.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Profile.this, ChatActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });
    }
}
