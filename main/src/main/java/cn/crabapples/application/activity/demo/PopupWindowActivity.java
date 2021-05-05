package cn.crabapples.application.activity.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class PopupWindowActivity extends AppCompatActivity {
    private final String TAG = "PopupWindowActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);
    }

    public void showPopup(View view) {
        PopupWindow popupWindow = new PopupWindow(PopupWindowActivity.this);
    }

    public void showToast(String content) {
        Toast.makeText(PopupWindowActivity.this, content, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
    }

}
