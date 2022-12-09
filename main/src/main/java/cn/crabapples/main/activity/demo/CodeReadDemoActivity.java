package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CodeReadDemoActivity extends AppCompatActivity {
    private final String TAG = "crabapples";
    private EditText editText;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_read_demo);
        editText = findViewById(R.id.url);
        handler = new Handler(Looper.myLooper(), (message) -> {
            TextView textView = findViewById(R.id.code);
            textView.setText(message.obj.toString());
            return true;
        });
    }


    public void showCode(View view) {
        String url = String.valueOf(editText.getText());
        new Thread(() -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                char[] data = new char[1024];
                StringBuilder code = new StringBuilder();
                for (int i = inputStream.read(data); i != -1; i = inputStream.read(data)) {
                    code.append(String.valueOf(data));
                }
                Log.d(TAG, "showCode: " + code);
                Message message = handler.obtainMessage();
                message.obj = code.toString();
                handler.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }


    public void back(View view) {
    }

}
