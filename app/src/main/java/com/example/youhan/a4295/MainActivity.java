package com.example.youhan.a4295;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

<<<<<<< HEAD
        m5.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, Profile.class);
                startActivity(i);
            }
        });

        m4.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(MainActivity.this, Location.class);
                startActivity(i);
            }
        });

=======
>>>>>>> 2c27282fd8690c4c99ed7e16a938fab21a11e922
    }
}
