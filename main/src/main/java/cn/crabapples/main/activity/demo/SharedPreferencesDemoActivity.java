package cn.crabapples.main.activity.demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class SharedPreferencesDemoActivity extends AppCompatActivity {
    private final String TAG = "PreferencesDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
    }

    public void setShare(View view) {
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", "user1");
        editor.putString("password", "pwd1");
        editor.putBoolean("rememberMe", true);
        editor.putLong("time", System.currentTimeMillis() / 1000);
        editor.apply();
    }

    public void getShare(View view) {
        SharedPreferences preferences = getSharedPreferences("userinfo", MODE_PRIVATE);
        String username = preferences.getString("username", "undefine");
        String password = preferences.getString("password", "undefine");
        boolean rememberMe = preferences.getBoolean("rememberMe", false);
        long time = preferences.getLong("time", 0);
        showToast("username:" + username + ",password:" + password + ",rememberMe:" + rememberMe + ",time:" + time);
    }


    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

    public void back(View view) {
    }


}
