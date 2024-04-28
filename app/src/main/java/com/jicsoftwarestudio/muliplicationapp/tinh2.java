package com.jicsoftwarestudio.muliplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jicsoftwarestudio.muliplicationapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class tinh2 extends AppCompatActivity {

    private TextView questionTextView;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private int number;
    private int correctCount = 0; // Số câu trả lời đúng
    private int correctAnswer;
    // Khai báo tổng số câu hỏi bạn muốn đạt được
    private final int totalQuestions = 2;
    private ProgressBar progressBar;
    private Drawable originalButtonBackground;
    private List<String> generatedQuestions; // Danh sách các câu hỏi đã được tạo ra
    private Set<Integer> usedAnswers; // Danh sách các đáp án đã được sử dụng
    // Khai báo biến để lưu thời gian bắt đầu từ câu hỏi đầu tiên
    private long startTimeOfFirstQuestion = 0;
    // Khai báo biến để lưu thời gian hoàn thành câu thứ 10
    private long endTimeOfQuestion10 = 0;


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
        progressBar = findViewById(R.id.progressBar);
        // Lưu trạng thái màu nền gốc của button
        originalButtonBackground = answerButton1.getBackground();
        // Khởi tạo danh sách câu hỏi và danh sách đáp án đã sử dụng
        generatedQuestions = new ArrayList<>();
        usedAnswers = new HashSet<>();
        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        String question;
        // Tạo câu hỏi mới không trùng với các câu hỏi đã được tạo trước đó
        do {
            int num1 = number; // Random số từ 1 đến 10
            int num2 = random.nextInt(number) + 1; // Random số từ 1 đến 10
            correctAnswer = num1 * num2;
            question = num1 + " x " + num2 + " = ?";
        } while (generatedQuestions.contains(question));

        generatedQuestions.add(question);
        questionTextView.setText(question);

        // Xóa danh sách đáp án đã sử dụng
        usedAnswers.clear();
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
                int wrongAnswer;
                // Tạo đáp án sai mới không trùng với các đáp án đã sử dụng trước đó
                do {
                    wrongAnswer = generateWrongAnswer();
                } while (usedAnswers.contains(wrongAnswer));

                usedAnswers.add(wrongAnswer);

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
        // Reset màu nền của các button về trạng thái gốc
        answerButton1.setBackground(originalButtonBackground);
        answerButton2.setBackground(originalButtonBackground);
        answerButton3.setBackground(originalButtonBackground);
        answerButton4.setBackground(originalButtonBackground);
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
            if (correctCount == 0) { // Nếu đây là câu hỏi đầu tiên, bắt đầu tính thời gian
                startTimeOfFirstQuestion = System.currentTimeMillis();
            }
            correctCount++; // Tăng số câu trả lời đúng
            // Tính toán phần trăm dựa trên số lượng câu trả lời đúng
            int progress = (correctCount * 10);
            // Giá trị tối đa của progress là 100
            progress = Math.min(progress, 100);
            // Cập nhật giá trị của ProgressBar
            progressBar.setProgress(progress);
             // Kiểm tra nếu đã trả lời đúng đủ 10 câu
            if (correctCount == 10) {
                endTimeOfQuestion10 = System.currentTimeMillis();
                // Xử lý khi trò chơi kết thúc sau khi trả lời đúng đủ 10 câu
                Toast.makeText(this, "Kết thúc trò chơi", Toast.LENGTH_SHORT).show();
                // Tạo Intent để chuyển sang Activity thống kê kết quả
                Intent intent = new Intent(this, ResultActivity.class);
                // Tính thời gian hoàn thành từ câu đầu tiên đến câu thứ 10
                long timeTakenForAllQuestions = endTimeOfQuestion10 - startTimeOfFirstQuestion;
                intent.putExtra("timeTakenForAllQuestions", timeTakenForAllQuestions);
                startActivity(intent);
                finish();
                // Kết thúc activity
            }
            generateQuestion();
        } else {
            Toast.makeText(this, "Sai bét", Toast.LENGTH_SHORT).show();
            // Xử lý khi câu trả lời sai
            // Đặt màu nền cho button là đỏ
            selectedButton.setBackgroundResource(R.drawable.red_button_background);
            // Ví dụ: Hiển thị thông báo "Incorrect!"
        }

        // Tạo câu hỏi mới sau khi trả lời
    }
}
