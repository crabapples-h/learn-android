package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class LiveDemoActivity extends AppCompatActivity {
    private final String TAG = "crabapples";

    /**
     * 在Activity第一次创建时被调用
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_demo);
    }

    public void back(View view) {
        showToast("back()", 2000);
    }

    /**
     * 在Activity变得可见时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        showToast("onStart()", 1000);
    }

    /**
     * 在Activity被再次启动前将会被调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        showToast("onRestart()", 1000);
    }

    /**
     * 在Activity开始准备与用户交互时被调用
     */
    @Override
    protected void onResume() {
        super.onResume();
        showToast("onResume()", 1000);
    }


    /**
     * 在启动另一个Activity时将被调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        showToast("onPause()", 1000);
    }


    /**
     * 在Activity变得不可见时被调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        showToast("onStop()", 1000);
    }

    /**
     * 在Activity被销毁前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        showToast("onDestroy()", 1000);
    }


    public void showToast(String content, int time) {
        Toast.makeText(LiveDemoActivity.this, content, time).show();
    }
}
