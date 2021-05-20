package cn.crabapples.receiver_demo;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {
    private final String PACKAGE_NAME = "cn.cranapples";
    private final String[] PERMISSIONS = {Manifest.permission.ANSWER_PHONE_CALLS,
            Manifest.permission.PROCESS_OUTGOING_CALLS};
    private EditText numberView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(PERMISSIONS, 1);
        numberView = findViewById(R.id.number);
    }

    public void save(View view) {
        String number = numberView.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("number", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number", number);
        editor.apply();

    }
}