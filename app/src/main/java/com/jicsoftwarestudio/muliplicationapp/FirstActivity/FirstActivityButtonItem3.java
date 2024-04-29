package com.jicsoftwarestudio.muliplicationapp.FirstActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class FirstActivityButtonItem3 extends AppCompatActivity {

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
        setContentView(R.layout.activity_first_button_item3);
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
        answerButtons.add((Button) findViewById(R.id.button_clear));
        answerButtons.add((Button) findViewById(R.id.button_ok));

        generatedQuestions = new ArrayList<>();
        usedAnswers = new HashSet<>();
        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        String question;
        do {
            int num1 = number;
            int num2 = random.nextInt(10) + 1;
            correctAnswer = num1 * num2;
            question = num1 + " x " + num2 + " = ?";
        } while (generatedQuestions.contains(question));

        generatedQuestions.add(question);
        questionTextView.setText(question);

        usedAnswers.clear();
        usedAnswers.add(correctAnswer);

        for (Button button : answerButtons) {
            if (button.getId() == R.id.button_ok || button.getId() == R.id.button_clear) {
                continue;
            }

            // Bỏ qua việc thiết lập giá trị mới cho các nút khác button0 - button9

            // Đừng bỏ qua việc thiết lập giá trị mới cho các nút button0 - button9
            // Thiết lập giá trị cho các nút này ở nơi khác, không cần thiết lập ở đây
        }

        progressBar.setProgress(correctCount * 11);
    }


    public void onAnswerSelected(View view) {
        Button selectedButton = (Button) view;
        String selectedButtonText = selectedButton.getText().toString();

        if (selectedButtonText.equals(String.valueOf(correctAnswer))) {
            Toast.makeText(this, "Đúng", Toast.LENGTH_SHORT).show();
            if (correctCount == 0) {
                startTimeOfFirstQuestion = System.currentTimeMillis();
            }
            correctCount++;
            if (correctCount == 9) {
                endTimeOfQuestion9 = System.currentTimeMillis();
                Toast.makeText(this, "Kết thúc trò chơi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResultActivity.class);
                long timeTakenForAllQuestions = endTimeOfQuestion9 - startTimeOfFirstQuestion;
                intent.putExtra("timeTakenForAllQuestions", timeTakenForAllQuestions);
                startActivity(intent);
                finish();
            }
            generateQuestion();
        } else if (selectedButtonText.equals("C")) {
            // Xóa input nếu nhấn nút "Xóa"
            String currentQuestion = questionTextView.getText().toString();
            if (!currentQuestion.isEmpty()) {
                questionTextView.setText(currentQuestion.substring(0, currentQuestion.length() - 1));
            }
        } else if (selectedButtonText.equals("OK")) {
            // Kiểm tra và xử lý kết quả nếu nhấn nút "OK"
            String userAnswerString = questionTextView.getText().toString().replaceAll("[^\\d]", ""); // Loại bỏ ký tự không phải số
            int userAnswer = userAnswerString.isEmpty() ? 0 : Integer.parseInt(userAnswerString); // Chuyển đổi sang số nguyên
            if (userAnswer == correctAnswer) {
                Toast.makeText(this, "Đúng", Toast.LENGTH_SHORT).show();
                correctCount++;
                if (correctCount == 9) {
                    endTimeOfQuestion9 = System.currentTimeMillis();
                    Toast.makeText(this, "Kết thúc trò chơi", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, ResultActivity.class);
                    long timeTakenForAllQuestions = endTimeOfQuestion9 - startTimeOfFirstQuestion;
                    intent.putExtra("timeTakenForAllQuestions", timeTakenForAllQuestions);
                    startActivity(intent);
                    finish();
                }
                generateQuestion();
            } else {
                Toast.makeText(this, "Sai", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Thêm ký tự vào input nếu nhấn các nút số
            if (questionTextView.getText().length() < 3) {
                questionTextView.append(selectedButtonText);
            }
        }

        // Thay thế dấu ? phía trên bằng giá trị của nút đã được bấm
        String currentQuestion = questionTextView.getText().toString();
        if (currentQuestion.contains("?")) {
            questionTextView.setText(currentQuestion.replaceFirst("\\?", selectedButtonText));
        }
    }
}