package com.jicsoftwarestudio.muliplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jicsoftwarestudio.muliplicationapp.R;

import java.util.Random;

public class tinh2 extends AppCompatActivity {

    private TextView questionTextView;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private int number;
    private int correctAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh2);
        Intent intent = getIntent();
        // Trích xuất dữ liệu từ Intent
        String numberstring = intent.getStringExtra("number");
        number = Integer.parseInt(numberstring);

        questionTextView = findViewById(R.id.questionTextView);
        answerButton1 = findViewById(R.id.tinh1);
        answerButton2 = findViewById(R.id.tinh2);
        answerButton3 = findViewById(R.id.tinh3);
        answerButton4 = findViewById(R.id.tinh4);

        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        int num1 = number; // Random số từ 1 đến 10
        int num2 = random.nextInt(number) + 1; // Random số từ 1 đến 10

        correctAnswer = num1 * num2;

        questionTextView.setText(num1 + " x " + num2 + " = ?");

        // Random vị trí đặt đáp án đúng
        int correctButton = random.nextInt(4) + 1;

        // Đặt câu trả lời đúng vào một trong các nút
        switch (correctButton) {
            case 1:
                answerButton1.setText(String.valueOf(correctAnswer));
                break;
            case 2:
                answerButton2.setText(String.valueOf(correctAnswer));
                break;
            case 3:
                answerButton3.setText(String.valueOf(correctAnswer));
                break;
            case 4:
                answerButton4.setText(String.valueOf(correctAnswer));
                break;
        }

        // Đặt các câu trả lời sai vào các nút khác
        for (int i = 1; i <= 4; i++) {
            if (i != correctButton) {
                int wrongAnswer = generateWrongAnswer();
                switch (i) {
                    case 1:
                        answerButton1.setText(String.valueOf(wrongAnswer));
                        break;
                    case 2:
                        answerButton2.setText(String.valueOf(wrongAnswer));
                        break;
                    case 3:
                        answerButton3.setText(String.valueOf(wrongAnswer));
                        break;
                    case 4:
                        answerButton4.setText(String.valueOf(wrongAnswer));
                        break;
                }
            }
        }
    }

    private int generateWrongAnswer() {
        Random random = new Random();
        return random.nextInt(100) + 1; // Tạo một số ngẫu nhiên khác với câu trả lời đúng
    }

    public void onAnswerSelected(View view) {
        Button selectedButton = (Button) view;
        int selectedAnswer = Integer.parseInt(selectedButton.getText().toString());

        if (selectedAnswer == correctAnswer) {
            Toast.makeText(this, "Đúng", Toast.LENGTH_SHORT).show();
            generateQuestion();
        } else {
            Toast.makeText(this, "Sai bét", Toast.LENGTH_SHORT).show();
            // Xử lý khi câu trả lời sai
            // Ví dụ: Hiển thị thông báo "Incorrect!"
        }

         // Tạo câu hỏi mới sau khi trả lời
    }
}
