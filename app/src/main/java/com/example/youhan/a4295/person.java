package com.example.youhan.a4295;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Person extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        ImageButton goback = (ImageButton) findViewById(R.id.person_goback);

        goback.setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent();
                i.setClass(Person.this, Profile.class);
                startActivity(i);
            }
        });
    }
}
