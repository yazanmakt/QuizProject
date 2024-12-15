package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end);

        Intent intent = getIntent();

        int finalScore = intent.getIntExtra("points", 0);

        List<QuestionAndAnswer> questions = (List<QuestionAndAnswer>) intent.getSerializableExtra("questions");

        TextView finalScoreTextView = findViewById(R.id.final_score);

        finalScoreTextView.setText("Final Score: " + finalScore);

        LinearLayout questionsContainer = findViewById(R.id.questions_answers_container);

        for (int i = 0; i < questions.size(); i++) {
            QuestionAndAnswer question = questions.get(i);

            TextView questionNumber = new TextView(this);

            questionNumber.setText("Q" + (i + 1) + ": " + question.question);

            questionNumber.setTextSize(24);

            questionNumber.setTextColor(getResources().getColor(android.R.color.black));


            TextView correctAnswer = new TextView(this);

            correctAnswer.setText("Correct Answer: " + question.options[question.correctAnswer]);

            correctAnswer.setTextSize(20);

            correctAnswer.setTextColor(getResources().getColor(R.color.dark_grey_custom));


            questionsContainer.addView(questionNumber);
            questionsContainer.addView(correctAnswer);
        }
    }
}
