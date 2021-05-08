package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class AlertDialogActivity extends AppCompatActivity {
    private final String TAG = "AlertDialogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }

    public void showDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(AlertDialogActivity.this)
                .setIcon(R.drawable.icon_menus)
                .setTitle("提示~~~")
                .setMessage("这个是一段提示")
                .setNegativeButton("左边按钮", (dialog, which) -> Log.i(TAG, "左边按钮-->" + which))
                .setNeutralButton("中间按钮", (dialog, which) -> Log.i(TAG, "中间按钮-->" + which))
                .setPositiveButton("右边按钮", (dialog, which) -> Log.i(TAG, "右边按钮-->" + which))
                .setView(R.layout.module_dialog)
                .create();
        alertDialog.show();
    }

    public void showToast(String content) {
        Toast.makeText(AlertDialogActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
    }

}
