package com.example.youhan.a4295;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.youhan.a4295.R.styleable.Toolbar;

public class Location extends AppCompatActivity {
    private TextView loverName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setTitle("Location");

        Intent i = getIntent();
        final String user = i.getStringExtra("username");
        final String partner = i.getStringExtra("partnername");

        loverName = (TextView)findViewById(R.id.tvLoverName);
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

        ImageView map_image=(ImageView)findViewById(R.id.map_image);

        map_image.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View view){
                Intent i = new Intent();
                i.setClass(Location.this, Map.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }

        });

        m1.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Location.this, MainActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m2.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Location.this, Timeline.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m5.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Location.this, Profile.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });

        m3.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Location.this, ChatActivity.class);
                i.putExtra("username",user);
                i.putExtra("partnername",partner);
                startActivity(i);
            }
        });
    }
}
