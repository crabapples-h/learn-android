package cn.crabapples.main.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;
import cn.crabapples.main.utils.FileUtils;
import com.alibaba.fastjson.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private final String LOGIN_FILE = "/loginInfo";
    private String PATH;
    //    private String OUTER_PATH;
    private CheckBox rememberView;
    private EditText usernameView;
    private EditText passwordView;
    private boolean isRemember;
    private String username;
    private String password;

    private void showToast(String content) {
        Toast.makeText(LoginActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    private void back(View view) {
    }

    private void printLog(String content) {
        Log.i(TAG, content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        rememberView = findViewById(R.id.rememberMe);
        usernameView = findViewById(R.id.username);
        passwordView = findViewById(R.id.password);
        rememberView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isRemember = rememberView.isChecked();
        });
        initData();
    }

    private void initData() {
        /**
         * 判断SD卡是否正确挂载
         */
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String externalStoragePath = getExternalFilesDir(null).getAbsolutePath();
            PATH = externalStoragePath + LOGIN_FILE;
        } else {
            PATH = getFilesDir().getAbsolutePath() + LOGIN_FILE;
        }
        JSONObject loginInfo = FileUtils.getJson2File(PATH);
        if (loginInfo != null) {
            username = loginInfo.getString("username");
            password = loginInfo.getString("password");
            isRemember = Boolean.parseBoolean(loginInfo.getString("isRemember"));
            usernameView.setText(username);
            passwordView.setText(password);
            rememberView.setChecked(isRemember);
        }
    }

    public void submit(View view) {
        username = usernameView.getText().toString();
        password = passwordView.getText().toString();
        if (TextUtils.isEmpty(username)) {
            showToast("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        showToast("用户名:" + username + ",密码:" + password + "，记住密码:" + isRemember);
        saveLoginInfo2Storage();
    }

    private void saveLoginInfo2Storage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        if (isRemember) {
            jsonObject.put("password", password);
            jsonObject.put("isRemember", isRemember);
        }
        PackageManager packageManager = getPackageManager();
        int status = packageManager.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, "cn.crabapples");
        if (status == PackageManager.PERMISSION_GRANTED) {
            FileUtils.saveFile2Json(PATH, jsonObject);
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "授权结束");
        Log.i(TAG, "" + requestCode);
        Log.i(TAG, permissions.toString());
        Log.i(TAG, grantResults.toString());
    }
}