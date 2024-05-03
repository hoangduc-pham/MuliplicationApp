package com.jicsoftwarestudio.muliplicationapp.ThirdActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.jicsoftwarestudio.muliplicationapp.R;
import com.jicsoftwarestudio.muliplicationapp.ResultActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ThirdActivityButtonItem1 extends AppCompatActivity {

    private TextView questionTextView;
    private Button answerButton1;
    private Button answerButton2;
    private Button answerButton3;
    private Button answerButton4;
    private int randomNumber;
    private int correctCount = 0; // Số câu trả lời đúng
    private int correctAnswer;
    private ProgressBar progressBar;
    private List<String> generatedQuestions; // Danh sách các câu hỏi đã được tạo ra
    private Set<Integer> usedAnswers; // Danh sách các đáp án đã được sử dụng
    // Khai báo biến để lưu thời gian bắt đầu từ câu hỏi đầu tiên
    private long startTimeOfFirstQuestion = 0;
    // Khai báo biến để lưu thời gian hoàn thành câu thứ 10
    private long endTimeOfQuestion10 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_button_item1);
        Intent intent = getIntent();
        // Trích xuất dữ liệu từ Intent
        int[] numbers = intent.getIntArrayExtra("number");


        questionTextView = findViewById(R.id.questionTextView);
        answerButton1 = findViewById(R.id.answerButton1);
        answerButton2 = findViewById(R.id.answerButton2);
        answerButton3 = findViewById(R.id.answerButton3);
        answerButton4 = findViewById(R.id.answerButton4);
        progressBar = findViewById(R.id.progressBar);
        // Khởi tạo danh sách câu hỏi và danh sách đáp án đã sử dụng
        generatedQuestions = new ArrayList<>();
        usedAnswers = new HashSet<>();
        generateQuestion();
    }

    private void generateQuestion() {
        Intent intent = getIntent();
        // Trích xuất dữ liệu từ Intent
        int[] numbers = intent.getIntArrayExtra("number");

        // Kiểm tra xem tập hợp số có tồn tại và không rỗng
        if (numbers != null && numbers.length > 0) {
            // Tạo một đối tượng Random
            Random random = new Random();

            // Trích xuất một số ngẫu nhiên từ tập hợp numbers
            int randomNumberIndex = random.nextInt(numbers.length);

            // Sử dụng số ngẫu nhiên để truy xuất phần tử tương ứng trong tập hợp numbers
            randomNumber = numbers[randomNumberIndex];

            // Sử dụng số ngẫu nhiên được trích xuất
            // Ví dụ:
            Toast.makeText(this, "Số ngẫu nhiên: " + randomNumber, Toast.LENGTH_SHORT).show();
        } else {
            // Xử lý khi không có dữ liệu được truyền qua Intent hoặc tập hợp số rỗng
            Toast.makeText(this, "Không có dữ liệu số được truyền qua Intent", Toast.LENGTH_SHORT).show();
        }

        Random random = new Random();
        String question;
        // Tạo câu hỏi mới không trùng với các câu hỏi đã được tạo trước đó
        do {
            int num1 = randomNumber; // Random số từ 1 đến 10
            int num2;
            if (randomNumber >= 2 && randomNumber <= 10) {
                num2 = random.nextInt(11) + 1; // Random số từ 1 đến 10
            } else {
                num2 = random.nextInt(randomNumber) + 1; // Random số từ 1 cho đến randomNumber
            }
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
        answerButton1.setBackgroundResource(R.drawable.blue_button);
        answerButton2.setBackgroundResource(R.drawable.blue_button);
        answerButton3.setBackgroundResource(R.drawable.blue_button);
        answerButton4.setBackgroundResource(R.drawable.blue_button);
    }

    private int generateWrongAnswer() {
        Random random = new Random();
        // Tạo một giá trị ngẫu nhiên nhỏ từ -5 đến 5
        int randomOffset = random.nextInt(11) - 5;
        // Thêm hoặc trừ giá trị ngẫu nhiên này từ câu trả lời đúng
        int wrongAnswer = correctAnswer + randomOffset;
        // Đảm bảo rằng giá trị của wrongAnswer không bằng correctAnswer
        while (wrongAnswer == correctAnswer) {
            // Nếu giá trị sai vẫn bằng giá trị đúng, thử tạo giá trị ngẫu nhiên mới
            randomOffset = random.nextInt(11) - 5;
            wrongAnswer = correctAnswer + randomOffset;
        }
        return wrongAnswer;
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
            // Xử lý khi câu trả lời sai
            // Đặt màu nền cho button là đỏ
            selectedButton.setBackgroundResource(R.drawable.red_button);
        }
    }
}
