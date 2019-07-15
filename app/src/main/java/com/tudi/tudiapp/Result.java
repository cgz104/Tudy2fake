package com.tudi.tudiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Result extends AppCompatActivity {
    private int result;
    private int total;
    TextView viewResult;
    TextView viewCompliment;
    TextView yourResult;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        viewResult = findViewById(R.id.viewResult);
        viewCompliment = findViewById(R.id.viewCompliment);
        yourResult = findViewById(R.id.yourResult);
        databaseUser = FirebaseDatabase.getInstance().getReference("User").child(MainActivity.userLogin.getUserID());

        Intent intent = getIntent();
        result = intent.getIntExtra("correct", 0);
        total = intent.getIntExtra("total", 0);

        yourResult.setText("Your result");
        viewResult.setText(Integer.toString(result));

        MainActivity.userLogin.setTotalDone(MainActivity.userLogin.getTotalDone() + total);
        MainActivity.userLogin.setTotalCorrectDone(MainActivity.userLogin.getTotalCorrectDone() + result);

        if(result < total/2.0){
            viewCompliment.setText("Try harder!!!");
        }else{
            viewCompliment.setText("Congrats!!!");
        }
        databaseUser.setValue(MainActivity.userLogin);

    }
    public void resultToHome(View view){
        Intent intent = new Intent(this, QuizOption.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, QuizOption.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
