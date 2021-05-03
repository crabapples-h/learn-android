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
        setContentView(R.layout.alert_dialog);
    }

    public void showDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(AlertDialogActivity.this)
                .setIcon(R.drawable.icon_menus)
                .setTitle("提示~~~")
                .setMessage("这个是一段提示")
                .setNegativeButton("左边按钮", (dialog, which) -> {

                })
                .setNeutralButton("中间按钮", null)
                .setPositiveButton("右边按钮", null)
                .create();
        alertDialog.show();
    }

    public void showToast(String content) {
        Toast.makeText(AlertDialogActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
    }

}
