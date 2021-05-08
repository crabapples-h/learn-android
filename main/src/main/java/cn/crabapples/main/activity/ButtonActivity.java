package cn.crabapples.main.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class ButtonActivity extends AppCompatActivity {
    private final String TAG = "ButtonActive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        Button touchButton = findViewById(R.id.touch);
        Button longClickButton = findViewById(R.id.longClick);
        Button clickButton = findViewById(R.id.click);
        /*点击*/
        clickButton.setOnClickListener(new ClickListener());
        longClickButton.setOnClickListener(new ClickListener());
        touchButton.setOnClickListener(new ClickListener());
        /*长按*/
        longClickButton.setOnLongClickListener(new OnlyLongClickListener());
        touchButton.setOnLongClickListener(new LongClickListener());
        /*触摸*/
        touchButton.setOnTouchListener(new OnlyTouchListener());
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            showToast("click", 1000);
        }
    }

    class LongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            showToast("longClick", 1000);
            return false;
        }
    }


    class OnlyLongClickListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            showToast("longClick", 1000);
            return true;
        }
    }

    class OnlyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            showToast("touch", 1000);
            return true;
        }
    }

    public void showToast(String content, int time) {
        Toast.makeText(ButtonActivity.this, content, time).show();
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }
}
