package com.jicsoftwarestudio.muliplicationapp.FirstActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jicsoftwarestudio.muliplicationapp.R;

public class FirstActivityPage extends AppCompatActivity {
    private Button btn, btn2;
    private TextView textView;
    int i =2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        textView = findViewById(R.id.textView);

        // Mặc định hiển thị bảng cửu chương số 2
        showMultiplicationTable(2);
        btn = findViewById(R.id.buttonItem2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivityPage.this, FirstActivityButtonItem2.class);
                String k = Integer.toString(i);

                // Đính kèm dữ liệu vào Intent
                intent.putExtra("number", k);
                startActivity(intent);
            }
        });
        btn2 = findViewById(R.id.buttonItem3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivityPage.this, FirstActivityButtonItem3.class);
                String k = Integer.toString(i);

                // Đính kèm dữ liệu vào Intent
                intent.putExtra("number", k);
                startActivity(intent);
            }
        });
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
        i=number;
        // Hiển thị bảng cửu chương tương ứng
        showMultiplicationTable(number);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        button2.setBackgroundResource(R.drawable.blue_button);
        button3.setBackgroundResource(R.drawable.blue_button);
        button4.setBackgroundResource(R.drawable.blue_button);
        button5.setBackgroundResource(R.drawable.blue_button);
        button6.setBackgroundResource(R.drawable.blue_button);
        button7.setBackgroundResource(R.drawable.blue_button);
        button8.setBackgroundResource(R.drawable.blue_button);
        button9.setBackgroundResource(R.drawable.blue_button);

        view.setBackgroundResource(R.drawable.green_button);
    }
}
