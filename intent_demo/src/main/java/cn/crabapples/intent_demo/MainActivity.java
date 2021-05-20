package cn.crabapples.intent_demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.crabapples.intent_demo.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final int SELECT_USER = 0x00001;
    private final int SELECT_MESSAGE = 0x00002;
    private EditText nameView;
    private EditText phoneView;
    private EditText messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameView = findViewById(R.id.name);
        phoneView = findViewById(R.id.phone);
        messageView = findViewById(R.id.message);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult()");
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case SELECT_USER:
                User user = (User) data.getBundleExtra("user").get("user");
                nameView.setText(user.name);
                phoneView.setText(user.phone);
                break;
            case SELECT_MESSAGE:
                String message = data.getStringExtra("message");
                messageView.setText(message);
                break;
        }


    }

    public void selectUser(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.crabapples.intentDemo.userList");
        startActivityForResult(intent, SELECT_USER);
    }

    public void selectMessage(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.crabapples.intentDemo.messageList");
        startActivityForResult(intent, SELECT_MESSAGE);
    }

    public void toSend(View view) {
        String phone = phoneView.getText().toString();
        String message = messageView.getText().toString();
        if (phone.length() > 0 && message.length() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra("sms_body", message);
            startActivity(intent);
        }
    }

    public void send(View view) {
        String phone = phoneView.getText().toString();
        String message = messageView.getText().toString();
        if (phone.length() > 0 && message.length() > 0) {
            SmsManager smsManager = SmsManager.getDefault();
            /*
             * 第一个参数:接收短信的号码
             * 第二个参数:短信中心号码，默认为null
             * 第三个参数:短信内容
             * 第四个参数:短信发送回执
             * 第五个参数:短信接收回执
             */
            PackageManager packageManager = getPackageManager();
            int result = packageManager.checkPermission(Manifest.permission.SEND_SMS, "cn.crabapples");
            if (PackageManager.PERMISSION_GRANTED != result) {
                requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE}, 1);
            }
            Log.d(TAG, "电话号码:" + phone + ",短信内容:" + message);
            smsManager.sendTextMessage(phone, null, message, null, null);
        }
    }
}