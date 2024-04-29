package com.jicsoftwarestudio.muliplicationapp.FourthActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jicsoftwarestudio.muliplicationapp.R;

public class FourthActivityPage extends AppCompatActivity {
    private TextView textView, textView2;
    private Button btn;
    int i =11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_page);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        btn = findViewById(R.id.buttonItem1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivityPage.this, FourthActivityButtonItem1.class);
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
        // Reset màu nền của tất cả các nút về màu ban đầu
        Button button11 = findViewById(R.id.button11);
        Button button12 = findViewById(R.id.button12);
        Button button13 = findViewById(R.id.button13);
        Button button14 = findViewById(R.id.button14);
        Button button15 = findViewById(R.id.button15);
        Button button16 = findViewById(R.id.button16);
        Button button17 = findViewById(R.id.button17);
        Button button18 = findViewById(R.id.button18);
        Button button19 = findViewById(R.id.button19);
        Button button20 = findViewById(R.id.button20);
        button11.setBackgroundResource(R.drawable.blue_button);
        button12.setBackgroundResource(R.drawable.blue_button);
        button13.setBackgroundResource(R.drawable.blue_button);
        button14.setBackgroundResource(R.drawable.blue_button);
        button15.setBackgroundResource(R.drawable.blue_button);
        button16.setBackgroundResource(R.drawable.blue_button);
        button17.setBackgroundResource(R.drawable.blue_button);
        button18.setBackgroundResource(R.drawable.blue_button);
        button19.setBackgroundResource(R.drawable.blue_button);
        button20.setBackgroundResource(R.drawable.blue_button);

        // Đặt màu nền mới cho nút được chọn
        view.setBackgroundResource(R.drawable.green_button);
    }
}