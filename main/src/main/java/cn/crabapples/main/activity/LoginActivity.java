package cn.crabapples.main.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;
import cn.crabapples.main.utils.FileUtils;
import com.alibaba.fastjson.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LoginActivity";
    private final String LOGIN_FILE = "data/data/cn.crabapples/loginInfo";
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
        JSONObject loginInfo = FileUtils.getJson2File(LOGIN_FILE);
        if (loginInfo != null) {
            System.err.println(loginInfo);
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
        saveLoginInfo();
    }

    private void saveLoginInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        if (isRemember) {
            jsonObject.put("password", password);
            jsonObject.put("isRemember", isRemember);
        }
        FileUtils.saveFile2Json(LOGIN_FILE, jsonObject);
    }
}