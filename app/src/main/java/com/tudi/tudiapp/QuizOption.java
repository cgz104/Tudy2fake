package com.tudi.tudiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizOption extends AppCompatActivity {
    TextView helloUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        helloUser = findViewById(R.id.helloUser);

        helloUser.setText("Welcome " + MainActivity.userLogin.getUserName());

    }

    public void goToQuiz(View view){
        Intent intent0 = new Intent(this, QuizMultiple.class);
        startActivity(intent0);
    }

    public void goToCreate(View view){
        Intent intent = new Intent(this, CreateQuiz.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
