package com.example.youhan.a4295;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class InitializationActivity extends AppCompatActivity {
    private EditText txtName;
    private String name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization);
        txtName = (EditText)findViewById(R.id.etName);
    }

    public void btInitializationComplete(View v){
        if(Validate.validate(txtName.getText().toString())){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            name = txtName.getText().toString();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(InitializationActivity.this, "Personal profiles updated", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(InitializationActivity.this, PartnerConnectionActivity.class);
                                i.putExtra("username",name);
                                startActivity(i);
                            }
                        }
                    });
        }
        else{
            Toast.makeText(InitializationActivity.this, "Please input the valid name!", Toast.LENGTH_LONG).show();
            txtName.setText("");
        }
    }
}
