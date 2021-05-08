package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class ChangeUIDemoActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ui);
        textView = findViewById(R.id.text1);
    }

    public void changeUI(View view) {
        new MyThread().start();
        runAbleThread();
    }

    private void runAbleThread() {
        new Thread(() -> {
            textView.setText("来自runAbleThread的修改");
        }).start();
    }

    public void back(View view) {
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            textView.setText("来自MyThread的修改");
        }
    }
}
