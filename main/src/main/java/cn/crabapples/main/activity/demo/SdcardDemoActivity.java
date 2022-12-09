package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.io.File;

public class SdcardDemoActivity extends AppCompatActivity {
    private final String TAG = "crabapples";
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
        File dir = getExternalFilesDir(null);
        long freeSpace = dir.getFreeSpace();
        long useSpace = dir.getUsableSpace();
        long totalSpace = dir.getTotalSpace();
        System.err.println("freeSpace:" + freeSpace / 1024.0 / 1024 / 1024);
        System.err.println("useSpace:" + useSpace / 1024.0 / 1024 / 1024);
        System.err.println("totalSpace:" + totalSpace / 1024.0 / 1024 / 1024);
        String freeSpaceString = "freeSpace:" + Formatter.formatFileSize(this, freeSpace);
        String useSpaceString = "freeSpace:" + Formatter.formatFileSize(this, freeSpace);
        String totalSpaceString = "freeSpace:" + Formatter.formatFileSize(this, freeSpace);
        System.err.println(freeSpaceString);
        System.err.println(useSpaceString);
        System.err.println(totalSpaceString);
        showToast(freeSpaceString);
        showToast(useSpaceString);
        showToast(totalSpaceString);
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

}
