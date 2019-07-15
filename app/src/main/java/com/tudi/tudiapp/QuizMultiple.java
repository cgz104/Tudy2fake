package com.tudi.tudiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizMultiple extends AppCompatActivity {
    DatabaseReference databaseQuiz;
    protected ArrayList<Quiz> qList = new ArrayList<>();
    int i = 0;
    private int numberQuestion = 0;
    private int numberCorrectQuestion = 0;

    TextView qNumber;
    TextView qContent;
    RadioButton opt1;
    RadioButton opt2;
    RadioButton opt3;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_multiple);

        databaseQuiz = FirebaseDatabase.getInstance().getReference("Quiz");

        qNumber = findViewById(R.id.questionNumber);
        qContent = findViewById(R.id.question);
        opt1 = findViewById(R.id.radio_button1);
        opt2 = findViewById(R.id.radio_button2);
        opt3 = findViewById(R.id.radio_button3);
        group = findViewById(R.id.radio_group);

        databaseQuiz.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot quizSnapshot : dataSnapshot.getChildren()){
                    Quiz quiz = quizSnapshot.getValue(Quiz.class);
                    qList.add(quiz);
                    numberQuestion++;
                }
                qNumber.setText("Question " + (i + 1));
                qContent.setText(qList.get(i).getQuestion());
                opt1.setText(qList.get(i).getChoice1());
                opt2.setText(qList.get(i).getChoice2());
                opt3.setText(qList.get(i).getChoice3());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
    public void next(View view){
        i++;
        if(group.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Select one answer", Toast.LENGTH_SHORT).show();
        }
        else{
            if(i < numberQuestion){
//            opt1.setChecked(false);
//            opt2.setChecked(false);
//            opt3.setChecked(false);
                group.clearCheck(); //do radioGroup clearCheck before make radioButton setChecked
                opt1.setEnabled(true);
                opt2.setEnabled(true);
                opt3.setEnabled(true);
                opt1.setBackgroundColor(0xFFFFFFFF);
                opt2.setBackgroundColor(0xFFFFFFFF);
                opt3.setBackgroundColor(0xFFFFFFFF);
                qNumber.setText("Question " + (i + 1));
                qContent.setText(qList.get(i).getQuestion());
                opt1.setText(qList.get(i).getChoice1());
                opt2.setText(qList.get(i).getChoice2());
                opt3.setText(qList.get(i).getChoice3());
            }
            else{
                Intent intent = new Intent(this, Result.class);
                intent.putExtra("correct", numberCorrectQuestion);
                intent.putExtra("total", numberQuestion);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

    public void radio1(View view){
        opt1.setChecked(true);
        setEnableFalse();
        changeState(i);
        if(opt1.getText().equals(qList.get(i).getAnswer())){
            numberCorrectQuestion++;
        }
    }
    public void radio2(View view){
        opt2.setChecked(true);
        setEnableFalse();
        changeState(i);
        if(opt2.getText().equals(qList.get(i).getAnswer())){
            numberCorrectQuestion++;
        }
    }
    public void radio3(View view){
        opt3.setChecked(true);
        setEnableFalse();
        changeState(i);
        if(opt3.getText().equals(qList.get(i).getAnswer())){
            numberCorrectQuestion++;
        }
    }

    public void changeState(int i){
        if(opt1.getText().equals(qList.get(i).getAnswer())){
            opt1.setBackgroundColor(0x487CFC00);
            opt2.setBackgroundColor(0x48FF0000);
            opt3.setBackgroundColor(0x48FF0000);
        }else if(opt2.getText().equals(qList.get(i).getAnswer())) {
            opt1.setBackgroundColor(0x48FF0000);
            opt2.setBackgroundColor(0x487CFC00);
            opt3.setBackgroundColor(0x48FF0000);
        }else if(opt3.getText().equals(qList.get(i).getAnswer())){
            opt1.setBackgroundColor(0x48FF0000);
            opt2.setBackgroundColor(0x48FF0000);
            opt3.setBackgroundColor(0x487CFC00);
        }
    }
    public void setEnableFalse(){
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Result.class);
        intent.putExtra("correct", numberCorrectQuestion);
        intent.putExtra("total", numberQuestion);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}
