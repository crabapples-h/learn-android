package cn.crabapples.application.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;
import cn.crabapples.application.checkbox.CheckBoxListener;

public class CheckBoxActivity extends AppCompatActivity {
    private final CheckBoxListener.Click CHECK_BOX_CLICK = new CheckBoxListener.Click();
    private final CheckBoxListener.Change CHECK_BOX_CHANGE = new CheckBoxListener.Change();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_box);
    }

    public void showToast() {
        Toast.makeText(CheckBoxActivity.this, "喜欢你", Toast.LENGTH_SHORT).show();
    }


}
