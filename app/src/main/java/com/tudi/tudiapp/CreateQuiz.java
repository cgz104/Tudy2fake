package com.tudi.tudiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateQuiz extends AppCompatActivity {
    EditText question;
    EditText answer;
    EditText choice1;
    EditText choice2;
    EditText choice3;

    DatabaseReference databaseQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        databaseQuiz = FirebaseDatabase.getInstance().getReference("Quiz");

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
    }

    public void createQuiz(View view){
        String _ques = question.getText().toString();
        String _answer = answer.getText().toString();
        String c1 = choice1.getText().toString();
        String c2 = choice2.getText().toString();
        String c3 = choice3.getText().toString();

        if(!TextUtils.isEmpty(_ques) || !TextUtils.isEmpty(_answer) || !TextUtils.isEmpty(c1) || !TextUtils.isEmpty(c2) || !TextUtils.isEmpty(c3)){
            String id = databaseQuiz.push().getKey();
            Quiz quiz = new Quiz(id, _ques, _answer, c1, c2, c3);
            databaseQuiz.child(id).setValue(quiz);
        }else{
            Toast.makeText(this, "Don't left empty", Toast.LENGTH_SHORT).show();
        }
    }


}
