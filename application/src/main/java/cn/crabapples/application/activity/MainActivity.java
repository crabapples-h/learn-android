package cn.crabapples.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new ButtonClickListener());
    }

    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, CheckBoxActivity.class);
            startActivity(intent);
        }
    }
}
