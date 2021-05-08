package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.io.File;

public class SdcardActivity extends AppCompatActivity {
    private final String TAG = "SdcardActivity";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        textView = findViewById(R.id.sdcard);
    }

    public void back(View view) {
    }

    public void getSize(View view) {
        textView.setText("");
        File dir = getExternalFilesDir(null);
        long freeSpace = dir.getFreeSpace();
        long useSpace = dir.getUsableSpace();
        long totalSpace = dir.getTotalSpace();
        System.err.println("freeSpace:" + freeSpace);
        System.err.println("useSpace:" + useSpace);
        System.err.println("totalSpace:" + totalSpace);
    }
}
