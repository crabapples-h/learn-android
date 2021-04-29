package cn.crabapples.application.activity.demo;

import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class HandlerDemoActivity extends AppCompatActivity {
    private final String TAG = "HandlerDemoActivity";
    private TextView textView;
    Handler.Callback callback;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        textView = findViewById(R.id.content);
        callback = msg -> {
            try {
                Thread.sleep(2000);
                printLog(msg.obj.toString() + Thread.currentThread().getName());
                textView.setText(msg.obj.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        };
        handler = new Handler(Looper.myLooper(), callback);
    }

    public void send(View view) {
        new Thread(() -> {
            Message message = handler.obtainMessage();
            String content = "handler测试";
            message.what = 1;
            message.obj = content;
            handler.sendMessage(message);
            printLog(content + Thread.currentThread().getName());
        }).start();
    }
    class MyHandler extends Handler{}

    public void back(View view) {
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

}