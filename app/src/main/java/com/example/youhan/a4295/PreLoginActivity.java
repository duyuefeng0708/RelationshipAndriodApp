package com.example.youhan.a4295;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PreLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        setTitle("Home");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_p);
        setSupportActionBar(myToolbar);
    }

    public void btRegisterClick(View v){
        Intent i = new Intent(PreLoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    public void btLoginClick(View v){
        Intent i = new Intent(PreLoginActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
