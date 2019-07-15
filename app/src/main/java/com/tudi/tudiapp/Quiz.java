package com.tudi.tudiapp;

public class Quiz {
    private String quizID;
    private String question;
    private String choice1;
    private String choice2;
    private String choice3;
    private String answer;

    public Quiz(){

    }

    public Quiz(String quizID, String question, String answer, String choice1, String choice2, String choice3) {
        this.quizID = quizID;
        this.question = question;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.answer = answer;
    }

    public String getQuizID() {
        return quizID;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoice1() {
        return choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public String getAnswer() {
        return answer;
    }
}
