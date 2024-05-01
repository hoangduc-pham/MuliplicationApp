package com.jicsoftwarestudio.muliplicationapp.ThirdActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jicsoftwarestudio.muliplicationapp.R;

import java.util.HashMap;
import java.util.Map;

public class ThirdActivityPage extends AppCompatActivity {
    int i;
    private Map<Integer, Boolean> buttonStates = new HashMap<>(); // Ánh xạ ID của button và trạng thái của nó


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);
    }
    // Phương thức xử lý khi nhấn vào nút
    public void showMultiplicationTable(View view) {
        // Lấy số từ ID của nút
        int number = Integer.parseInt(((Button) view).getText().toString());
        i=number;
        Button button = (Button) view;
        // Đặt màu nền mới cho nút được chọn
        int buttonId = button.getId();

        // Kiểm tra xem buttonId đã được thêm vào map chưa
        if (!buttonStates.containsKey(buttonId)) {
            buttonStates.put(buttonId, true); // Mặc định là true (gray_button)
        }

        // Lấy trạng thái hiện tại của button
        boolean isBlue = buttonStates.get(buttonId);

        // Đặt màu nền mới cho nút dựa trên trạng thái hiện tại
        if (isBlue) {
            button.setBackgroundResource(R.drawable.gray_button);
            buttonStates.put(buttonId, false); // Cập nhật trạng thái là false (green_button)
        } else {
            button.setBackgroundResource(R.drawable.blue_button);
            buttonStates.put(buttonId, true); // Cập nhật trạng thái là true (gray_button)
        }

    }
}