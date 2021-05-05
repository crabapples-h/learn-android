package cn.crabapples.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class CheckBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        showToast(content, 5000);
    }

    public void back(View view) {
    }


    public void showToast(String content, int time) {
        Toast.makeText(CheckBoxActivity.this, content, time).show();
    }


    public void checkBoxClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = checkBox.isChecked();
        String text = checkBox.getText().toString() + ":" + checked;
        showToast(text, 1000);
        CheckBox eatCheckBox = findViewById(R.id.eat);
        CheckBox sleepCheckBox = findViewById(R.id.sleep);
        CheckBox happyCheckBox = findViewById(R.id.happy);
        CheckBox selectAllCheckBox = findViewById(R.id.selectAll);
        if (eatCheckBox.isChecked() && sleepCheckBox.isChecked() && happyCheckBox.isChecked()) {
            selectAllCheckBox.setChecked(true);
        } else {
            selectAllCheckBox.setChecked(false);
        }
    }

    public void selectAll(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = checkBox.isChecked();
        String text = checkBox.getText().toString() + ":" + checked;
        showToast(text, 1000);
        CheckBox eatCheckBox = findViewById(R.id.eat);
        CheckBox sleepCheckBox = findViewById(R.id.sleep);
        CheckBox happyCheckBox = findViewById(R.id.happy);
        if (eatCheckBox.isChecked() && sleepCheckBox.isChecked() && happyCheckBox.isChecked()) {
            eatCheckBox.setChecked(false);
            sleepCheckBox.setChecked(false);
            happyCheckBox.setChecked(false);
        } else {
            eatCheckBox.setChecked(true);
            sleepCheckBox.setChecked(true);
            happyCheckBox.setChecked(true);
        }

    }
}
