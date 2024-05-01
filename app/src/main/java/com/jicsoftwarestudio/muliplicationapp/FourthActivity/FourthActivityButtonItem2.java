package com.jicsoftwarestudio.muliplicationapp.FourthActivity;

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

public class FourthActivityButtonItem2 extends AppCompatActivity {
    private TextView questionTextView;
    private List<Button> answerButtons;
    private int number;
    private int correctCount = 0; // Số câu trả lời đúng
    private int correctAnswer;
    private ProgressBar progressBar;
    private List<String> generatedQuestions; // Danh sách các câu hỏi đã được tạo ra
    private Set<Integer> usedAnswers; // Danh sách các đáp án đã được sử dụng
    private long startTimeOfFirstQuestion = 0;
    private long endTimeOfQuestion9 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_button_item2);
        Intent intent = getIntent();
        String numberstring = intent.getStringExtra("number");
        number = Integer.parseInt(numberstring);

        questionTextView = findViewById(R.id.questionTextView);
        progressBar = findViewById(R.id.progressBar);

        answerButtons = new ArrayList<>();
        answerButtons.add((Button) findViewById(R.id.button0));
        answerButtons.add((Button) findViewById(R.id.button1));
        answerButtons.add((Button) findViewById(R.id.button2));
        answerButtons.add((Button) findViewById(R.id.button3));
        answerButtons.add((Button) findViewById(R.id.button4));
        answerButtons.add((Button) findViewById(R.id.button5));
        answerButtons.add((Button) findViewById(R.id.button6));
        answerButtons.add((Button) findViewById(R.id.button7));
        answerButtons.add((Button) findViewById(R.id.button8));
        answerButtons.add((Button) findViewById(R.id.button9));
        generatedQuestions = new ArrayList<>();
        usedAnswers = new HashSet<>();
        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        String question;
        do {
            int num1 = number;
            int num2 = random.nextInt(number) + 1;
            correctAnswer = num1 * num2;
            question = num1 + " x " + num2 + " = ?";
        } while (generatedQuestions.contains(question));

        generatedQuestions.add(question);
        questionTextView.setText(question);

        usedAnswers.clear();
        usedAnswers.add(correctAnswer);
        progressBar.setProgress(correctCount * 11);
    }


    public void onAnswerSelected(View view) {
        Button selectedButton = (Button) view;
        String selectedButtonText = selectedButton.getText().toString();
        TextView answerTextView = findViewById(R.id.answerTextView);

        // Lấy nội dung hiện tại của TextView hiển thị kết quả
        String currentAnswer = answerTextView.getText().toString();
        if (currentAnswer.length() >= 3 && Character.isDigit(selectedButtonText.charAt(0))) {
            return;
        }

        if (Character.isDigit(selectedButtonText.charAt(0))) {
            // Thêm số đã chọn vào TextView
            currentAnswer += selectedButtonText;
            answerTextView.setText(currentAnswer);
        } else if (selectedButtonText.equals("XÓA")) {
            // Xóa ký tự cuối cùng khỏi TextView nếu nhấn nút "Xóa"
            if (!currentAnswer.isEmpty()) {
                currentAnswer = currentAnswer.substring(0, currentAnswer.length() - 1);
                answerTextView.setText(currentAnswer);
                answerTextView.setTextColor(Color.BLACK);
            }
        } else if (selectedButtonText.equals("OK")) {
            // Kiểm tra xem câu trả lời có chính xác không
            if (!currentAnswer.isEmpty()) {
                if (Integer.parseInt(currentAnswer) == correctAnswer) {
                    correctCount++;
                    if (correctCount == 9) {
                        endTimeOfQuestion9 = System.currentTimeMillis();
                        Toast.makeText(this, "Kết thúc trò chơi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, ResultActivity.class);
                        long timeTakenForAllQuestions = endTimeOfQuestion9 - startTimeOfFirstQuestion;
                        intent.putExtra("timeTakenForAllQuestions", timeTakenForAllQuestions);
                        startActivity(intent);
                        finish();
                    } else {
                        generateQuestion();
                        answerTextView.setText(""); // Xóa nội dung trong TextView sau khi câu hỏi được làm mới
                    }
                } else {
                    answerTextView.setTextColor(Color.RED);
                }
            }
        }
    }
}