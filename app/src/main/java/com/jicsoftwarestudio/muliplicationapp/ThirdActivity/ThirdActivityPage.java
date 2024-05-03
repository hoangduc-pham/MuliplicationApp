package com.jicsoftwarestudio.muliplicationapp.ThirdActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jicsoftwarestudio.muliplicationapp.R;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ThirdActivityPage extends AppCompatActivity {
    Button btn;
    int i;
    private final Map<Integer, Boolean> buttonStates = new HashMap<>(); // Ánh xạ ID của button và trạng thái của nó
    private Set<Integer> selectedNumbers = new HashSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
        btn = findViewById(R.id.buttonItem1);
        // Mặc định chọn các số từ 2 đến 20
        for (int num = 2; num <= 20; num++) {
            selectedNumbers.add(num);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivityPage.this, ThirdActivityButtonItem1.class);

                // Tạo mảng để chứa số
                int[] numbersArray = new int[selectedNumbers.size()];
                int index = 0;
                for (int num : selectedNumbers) {
                    numbersArray[index++] = num;
                }

                // Đính kèm dữ liệu vào Intent
                intent.putExtra("number", numbersArray);
                startActivity(intent);
            }
        });
    }
    // Phương thức xử lý khi nhấn vào nút
    public void showMultiplicationTable(View view) {
        // Lấy số từ ID của nút
        Button button = (Button) view;
        int buttonId = button.getId();
        // Lấy trạng thái hiện tại của button
        boolean isBlue = buttonStates.containsKey(buttonId) ? buttonStates.get(buttonId) : false;
        // Nếu button chưa có trong buttonStates, mặc định là blue
        if (!buttonStates.containsKey(buttonId)) {
            buttonStates.put(buttonId, true);
            isBlue = true;
        }
        // Đặt màu nền mới cho nút dựa trên trạng thái hiện tại
        if (isBlue) {
            button.setBackgroundResource(R.drawable.gray_button);
            buttonStates.put(buttonId, false);

            // Xóa số khỏi selectedNumbers nếu nút đã được chọn trước đó
            int numberToRemove = Integer.parseInt(button.getText().toString());
            selectedNumbers.remove(numberToRemove);
        } else {
            button.setBackgroundResource(R.drawable.blue_button);
            buttonStates.put(buttonId, true); // Cập nhật trạng thái là true (gray_button)
            // Thêm số vào selectedNumbers nếu nút chưa được chọn trước đó
            int numberToAdd = Integer.parseInt(button.getText().toString());
            selectedNumbers.add(numberToAdd);
        }
    }
}