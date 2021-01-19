package cn.crabapples.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox eat = findViewById(R.id.eatId);
        CheckBox sleep = findViewById(R.id.sleepId);
        CheckBox hh = findViewById(R.id.hh);
        CheckBox all = findViewById(R.id.all);
        OnClickListener click = new OnClickListener();
        ClickChangeListener change = new ClickChangeListener();
        all.setOnCheckedChangeListener(change);
//        all.setOnClickListener(click);
    }

    public class ClickChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Toast.makeText(MainActivity.this, "测试，测试", Toast.LENGTH_SHORT).show();
            CheckBox box = findViewById(R.id.eatId);
            if(b){
                box.setChecked(true);
            }else {
                box.setChecked(false);
            }
        }
    }
}