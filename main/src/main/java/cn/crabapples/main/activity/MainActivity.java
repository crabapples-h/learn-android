package cn.crabapples.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cn.crabapples.main.R;
import cn.crabapples.main.activity.demo.*;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(null);
        toolbar.setNavigationOnClickListener(view -> {
            printLog("点击了icon");
        });
    }

    public void showToast(String content, int time) {
        Toast.makeText(MainActivity.this, content, time).show();
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

    public void back(View view) {
        printLog("点击了导航栏");
    }

    // 隐式意图
    public void toLiveDemo(View view) {
        Intent intent = new Intent();
        intent.setAction("cn.crabapples.liveDemo");
//        intent.setData(Uri.parse("crabapples:" + 110));
        startActivity(intent);
    }

    // 显式意图
    public void toCheckBox(View view) {
        Intent intent = new Intent();
        intent.setClassName("cn.cranapples", "CheckBoxActivity");
        startActivity(intent);
    }

    public void toImage(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ImageActivity.class);
        startActivity(intent);
    }

    public void toProgressBar(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ProgressBarActivity.class);
        startActivity(intent);
    }

    public void toRadio(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RadioActivity.class);
        startActivity(intent);
    }

    public void toTimePicker(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, TimePickerActivity.class);
        startActivity(intent);
    }

    public void toChangeUI(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ChangeUIDemoActivity.class);
        startActivity(intent);
    }

    // intent传递数据
    public void intentDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, IntentDemoActivity.class);
        intent.putExtra("content", "来自MainActivity的爱");
        startActivity(intent);
    }

    public void toButton(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ButtonActivity.class);
        startActivity(intent);
    }

    public void toNotice(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void toHandler(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, HandlerDemoActivity.class);
        startActivity(intent);
    }

    public void toDialog(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AlertDialogActivity.class);
        startActivity(intent);
    }

    public void toPopupWindow(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, PopupWindowDemoActivity.class);
        startActivity(intent);
    }

    public void toCall(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CallActivity.class);
        startActivity(intent);
    }

    public void toLogin(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void toSdcard(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SdcardDemoActivity.class);
        startActivity(intent);
    }

    public void toSharedPreferences(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SharedPreferencesDemoActivity.class);
        startActivity(intent);
    }

    public void toXmlDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, XmlDemoActivity.class);
        startActivity(intent);
    }

    public void toJdbcDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, JdbcDemoActivity.class);
        startActivity(intent);
    }

    public void toSqliteDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SqliteDemoActivity.class);
        startActivity(intent);
    }

    public void toSqliteDemo1(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SqliteDemo1Activity.class);
        startActivity(intent);
    }

    public void toListViewDemo(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ListViewDemoActivity.class);
        startActivity(intent);
    }

    public void toListViewDemo1(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ListViewDemo1Activity.class);
        startActivity(intent);
    }

    public void toCodeRead(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CodeReadDemoActivity.class);
        startActivity(intent);
    }

    public void toDownload(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, DownloadDemoActivity.class);
        startActivity(intent);
    }
}
