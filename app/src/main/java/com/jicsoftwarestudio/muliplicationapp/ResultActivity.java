package com.jicsoftwarestudio.muliplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        long timeInMillis = intent.getLongExtra("timeTakenForAllQuestions", 0);
        int seconds = (int) (timeInMillis / 1000); // Chuyển từ mili giây sang giây
        int milliseconds = (int) (timeInMillis % 1000); // Lấy phần mili giây

        String timeFormatted = String.format("%02d,%03d", seconds, milliseconds);
        TextView correctTextView = findViewById(R.id.time);
        correctTextView.setText("Thời gian hoàn thành: " + timeFormatted + "giây");
    }
}