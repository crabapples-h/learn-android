package cn.crabapples.toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickListener click = new clickListener();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(click);
    }
    class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "喜欢你",Toast.LENGTH_SHORT).show();
        }
    }
}
