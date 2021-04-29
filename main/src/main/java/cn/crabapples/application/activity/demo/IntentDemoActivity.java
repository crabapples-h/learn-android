package cn.crabapples.application.activity.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class IntentDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        showToast(content);
    }

    public void showToast(String content) {
        Toast.makeText(IntentDemoActivity.this, content, Toast.LENGTH_SHORT).show();
    }


}
