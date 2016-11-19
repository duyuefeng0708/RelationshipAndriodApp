package com.example.youhan.a4295;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        final String user = i.getStringExtra("username");
        final String partner = i.getStringExtra("partnername");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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


        ImageButton i1=(ImageButton)findViewById(R.id.functionButton1);
        ImageButton i2=(ImageButton)findViewById(R.id.functionButton2);
        ImageButton i3=(ImageButton)findViewById(R.id.functionButton3);

        //i1.setBackgroundColor(Color.parseColor("#ffffff"));
        //i2.setBackgroundColor(Color.parseColor("#ffffff"));
        //i3.setBackgroundColor(Color.parseColor("#ffffff"));

        m5.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, Profile.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m4.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, Location.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, ChatActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m2.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, Timeline.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        i3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, AlarmMe.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });


    }
}
