package com.tudi.tudiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText emailLogin;
    EditText passLogin;
    DatabaseReference databaseUser;
    private ArrayList<User> uList = new ArrayList<>();
    private int numberUser = 0;
    static User userLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseUser = FirebaseDatabase.getInstance().getReference("User");
        emailLogin = findViewById(R.id.emailLogin);
        passLogin = findViewById(R.id.passLogin);

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot quizSnapshot : dataSnapshot.getChildren()){
                    User user = quizSnapshot.getValue(User.class);
                    uList.add(user);
                    numberUser++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void signUp(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void loginClick(View view){
        String email = emailLogin.getText().toString();
        String passWord = passLogin.getText().toString();

        boolean status = false;
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passWord)){
            Toast.makeText(this, "Fill everything please", Toast.LENGTH_SHORT).show();
        }else{
            for(int i = 0; i < numberUser; i++){
                if(email.equals(uList.get(i).getEmail()) && passWord.equals(uList.get(i).getPassword())){
                    status = true;
                    //String id = uList.get(i).getUserID();
                    userLogin = uList.get(i);
                    Intent intent = new Intent(this, QuizOption.class);
                    //intent.putExtra("i", i);
                    startActivity(intent);
                }
            }

            if(status == false){
                Toast.makeText(this, "Something wrong, please check", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
