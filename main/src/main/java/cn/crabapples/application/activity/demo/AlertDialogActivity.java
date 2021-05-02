package cn.crabapples.application.activity.demo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_demo);
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertDialogActivity.this);
        builder.create();
        builder.setTitle("测试");
        builder.setMessage("这是内容");
        builder.setNegativeButton("按钮1", null);
        builder.setNeutralButton("按钮2",null);
        builder.setPositiveButton("按钮3",null);
        builder.show();
//        Intent intent = getIntent();
//        String content = intent.getStringExtra("content");
//        showToast(content);
    }

    public void showToast(String content) {
        Toast.makeText(AlertDialogActivity.this, content, Toast.LENGTH_SHORT).show();
    }


}
