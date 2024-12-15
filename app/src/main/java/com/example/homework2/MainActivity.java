package com.example.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.List;
import java.util.Arrays;



public class MainActivity extends AppCompatActivity {

    TextView questionTextView;


    List<QuestionAndAnswer> questions = Arrays.asList(
            new QuestionAndAnswer("who painted the mona lisa" , new String[]{"leonardo da vinci" ,"vincent van gogh" , "michelangelo" , "vermeer"},0),
            new QuestionAndAnswer("which famous artist cut off his ear" , new String[]{"vermeer" , "pablo picasso" , "vincent van gogh" , "salvador dali"}, 2),
            new QuestionAndAnswer("which artist created the sculpture 'David' ", new String[]{"takashi murakami","michelangelo" , "leonardo da vinci" ,"banksy" },1),
            new QuestionAndAnswer("what type of art is banksy famous for" , new String[]{"sculpture" , "collage" , "water colours" , "graffiti"}, 3),
            new QuestionAndAnswer("Leonardo Davinci’s 15th-century mural, The Last Supper, is located in what city?" , new String[]{"Paris,France" , "Milan,Italy" , "Berlin,Germany" , "London,UK"}, 1)
            );

    int currentQuestionIndex = 0;
    Button[] buttonsanswer = new Button[4];

    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.question);

        buttonsanswer[0] = findViewById(R.id.answer_a);
        buttonsanswer[1] = findViewById(R.id.answer_b);
        buttonsanswer[2] = findViewById(R.id.answer_c);
        buttonsanswer[3] = findViewById(R.id.answer_d);

        
        QuizSetup();
    }

    private void QuizSetup() {

        TextView totalQuestionsTextView = findViewById(R.id.total_questions);
        totalQuestionsTextView.setText("Total Questions :" + questions.size());

        loadQuestion(currentQuestionIndex, questionTextView, buttonsanswer);

        for (int i = 0;  i <buttonsanswer.length; i++){
            final int answerIndex = i;
            buttonsanswer[i].setOnClickListener(v -> {

                if (answerIndex == questions.get(currentQuestionIndex).correctAnswer) {
                    points++;
                    Toast.makeText(MainActivity.this, "+1 point", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "Wrong answer !", Toast.LENGTH_SHORT).show();
                }
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questions.size()){
                        loadQuestion(currentQuestionIndex, questionTextView, buttonsanswer);

                }else{
                        // Go to end page
                        Intent intent = new Intent(MainActivity.this, EndActivity.class);
                        intent.putExtra("points", points); // Pass points
                        intent.putExtra("questions", (Serializable) questions); // Pass questions (needs Serializable)
                        startActivity(intent);
                        finish(); // Close MainActivity
                    }
            });
        }
    }

    private void loadQuestion(int questionIndex, TextView questionTextView, Button[] buttonsanswer){
        QuestionAndAnswer currentQuestion = questions.get(questionIndex);
        questionTextView.setText(currentQuestion.question);

        for (int i = 0; i < buttonsanswer.length; i++){
            buttonsanswer[i].setText(currentQuestion.options[i]);
        }
    }
}

