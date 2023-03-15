package cn.crabapples.lock.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.crabapples.lock.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        Log.i("lock", "add: " + view.toString());
        Toast.makeText(this, "lock", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction("");
        startActivity(intent);

    }
}
