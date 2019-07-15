package com.tudi.tudiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText userNameSignUp;
    EditText passwordSignUp;
    EditText emailSignUp;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseUser = FirebaseDatabase.getInstance().getReference("User");

        userNameSignUp = findViewById(R.id.userNameSignUp);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        emailSignUp = findViewById(R.id.emailSignUp);
    }

    public void createAccount(View view){
        String userN = userNameSignUp.getText().toString();
        String pass = passwordSignUp.getText().toString();
        String email = emailSignUp.getText().toString();

        if(!TextUtils.isEmpty(userN) || !TextUtils.isEmpty(pass) || !TextUtils.isEmpty(email)){
            String id = databaseUser.push().getKey();
            User user = new User(id, userN, email, pass, 0, 0);
            databaseUser.child(id).setValue(user);
            Toast.makeText(this, "Account create successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Don't left empty", Toast.LENGTH_SHORT).show();
        }
    }
}
