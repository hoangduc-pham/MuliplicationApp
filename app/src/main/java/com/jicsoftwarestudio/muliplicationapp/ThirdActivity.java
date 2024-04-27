package com.jicsoftwarestudio.muliplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private TextView textView, textView2;
    private Button btn;
    int i =11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        btn = findViewById(R.id.tinh1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this,tinh2.class);
                String k = Integer.toString(i);

                // Đính kèm dữ liệu vào Intent
                intent.putExtra("number", k);
                startActivity(intent);
            }
        });
        // Mặc định hiển thị bảng cửu chương số 11
        showMultiplicationTable(11);
    }
    public void showMultiplicationTable(int number) {
        StringBuilder table = new StringBuilder();
        StringBuilder table2 = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            int result = number * i;
            if (i <= 10) {
                table.append(number).append(" x ").append(i).append(" = ").append(result).append("\n");
            } else {
                table2.append(number).append(" x ").append(i).append(" = ").append(result).append("\n");
            }
        }
        textView.setText(table.toString());
        textView2.setText(table2.toString());
    }


    // Phương thức xử lý khi nhấn vào nút
    public void showMultiplicationTable(View view) {
        // Lấy số từ ID của nút
        int number = Integer.parseInt(((Button) view).getText().toString());
        i=number;
        // Hiển thị bảng cửu chương tương ứng
        showMultiplicationTable(number);
    }
}