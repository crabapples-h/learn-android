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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        textView = findViewById(R.id.content);
    }

    public void send(View view) {
        Handler.Callback callback = msg -> {
            printLog(msg.obj.toString());
            textView.setText(msg.obj.toString());
            return false;
        };
        Handler handler = new Handler(Looper.myLooper(), callback);
        Message message = handler.obtainMessage();
        String content = "handler测试";
        message.what = 1;
        message.obj = content;
        handler.sendMessage(message);

        printLog(content);
    }

    public void back(View view) {
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

}