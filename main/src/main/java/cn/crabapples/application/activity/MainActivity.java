package cn.crabapples.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;
import cn.crabapples.application.activity.demo.ChangeUIDemoActivity;
import cn.crabapples.application.activity.demo.HandlerDemoActivity;
import cn.crabapples.application.activity.demo.IntentDemoActivity;
import cn.crabapples.application.activity.demo.LiveDemoActivity;

public class MainActivity extends AppCompatActivity {
    /**
     * 在Activity第一次创建时被调用
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLiveDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LiveDemoActivity.class);
        startActivity(intent);
    }

    public void toCheckBox(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CheckBoxActivity.class);
        startActivity(intent);
    }

    public void toImage(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ImageActivity.class);
        startActivity(intent);
    }

    public void toProgressBar(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ProgressBarActivity.class);
        startActivity(intent);
    }

    public void toRadio(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RadioActivity.class);
        startActivity(intent);
    }

    public void toTimePicker(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, TimePickerActivity.class);
        startActivity(intent);
    }

    public void toChangeUI(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ChangeUIDemoActivity.class);
        startActivity(intent);
    }

    public void intentDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, IntentDemoActivity.class);
        intent.putExtra("content", "来自MainActivity的爱");
        startActivity(intent);
    }

    public void toButton(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ButtonActivity.class);
        startActivity(intent);
    }

    public void toNotice(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void showToast(String content, int time) {
        Toast.makeText(MainActivity.this, content, time).show();
    }


    public void toHandler(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HandlerDemoActivity.class);
        startActivity(intent);
    }
}
