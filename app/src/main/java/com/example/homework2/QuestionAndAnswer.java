package com.example.homework2;

import java.io.Serializable;
import java.util.List;

public class QuestionAndAnswer implements Serializable {
    String question;
    String[] options;
    int correctAnswer;

    public QuestionAndAnswer(String Question, String[] options, int CorrectAnswer){
        this.question = Question;
        this.options = options;
        this.correctAnswer = CorrectAnswer;
    }
}

