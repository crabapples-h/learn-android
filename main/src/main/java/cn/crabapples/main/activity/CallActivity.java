package cn.crabapples.main.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.crabapples.main.R;

public class CallActivity extends AppCompatActivity {
    private EditText telNumberView;
    private final String TAG = "crabapples";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        telNumberView = findViewById(R.id.telNumber);
    }

    private void showToast(String content) {
        Toast.makeText(CallActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    private void back(View view) {
    }

    private void printLog(String content) {
        Log.i(TAG, content);
    }

    public void callTel(View view) {
        String telNumber = telNumberView.getText().toString();
        if (TextUtils.isEmpty(telNumber)) {
            showToast("电话号码为空");
        } else {
            if (checkPermissions(Manifest.permission.CALL_PHONE)) {
                call(telNumber);
            } else {
                showToast("没有打电话的权限!");
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
    }

    void call(String telNumber) {
        showToast("打电话给:" + telNumber);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + telNumber);
        intent.setData(uri);
        startActivity(intent);
    }

    private boolean checkPermissions(String permission) {
        PackageManager packageManager = getPackageManager();
        return PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(permission, "cn.crabapples");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "授权结束");
        Log.i(TAG, "" + requestCode);
        Log.i(TAG, permissions.toString());
        Log.i(TAG, grantResults.toString());
        if (requestCode == 1) {
//            call();
        }
    }
}
