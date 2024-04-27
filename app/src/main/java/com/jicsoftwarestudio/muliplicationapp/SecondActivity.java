package com.jicsoftwarestudio.muliplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        // Mặc định hiển thị bảng cửu chương số 2
        showMultiplicationTable(2);
    }

    public void showMultiplicationTable(int number) {
        StringBuilder table = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            int result = number * i;
            table.append(number).append(" x ").append(i).append(" = ").append(result).append("\n");
        }
        textView.setText(table.toString());
    }

    // Phương thức xử lý khi nhấn vào nút
    public void showMultiplicationTable(View view) {
        // Lấy số từ ID của nút
        int number = Integer.parseInt(((Button) view).getText().toString());
        // Hiển thị bảng cửu chương tương ứng
        showMultiplicationTable(number);
    }
}
