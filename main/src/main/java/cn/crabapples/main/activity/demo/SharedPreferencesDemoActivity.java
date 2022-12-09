package cn.crabapples.main.activity.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class SharedPreferencesDemoActivity extends AppCompatActivity {
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
//        preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> {
//            printLog("触发监听:" + key);
//            String data = sharedPreferences.getString(key, null);
//            printLog(data);
//        });
    }

    public void setShare(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        String username = "user1";
        String password = "pwd1";
        boolean rememberMe = true;
        long time = System.currentTimeMillis() / 1000;
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("rememberMe", rememberMe);
        editor.putLong("time", time);
        editor.apply();
        String content = "username:" + username + ",password:" + password + ",rememberMe:" + rememberMe + ",time:" + time;
        showToast(content);
        printLog(content);
    }

    public void getShare(View view) {
        String username = preferences.getString("username", "undefine");
        String password = preferences.getString("password", "undefine");
        boolean rememberMe = preferences.getBoolean("rememberMe", false);
        long time = preferences.getLong("time", 0);
        String content = "username:" + username + ",password:" + password + ",rememberMe:" + rememberMe + ",time:" + time;
        showToast(content);
        printLog(content);
    }


    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        String TAG = "crabapples";
        Log.i(TAG, content);
    }

    public void back(View view) {
    }


}
