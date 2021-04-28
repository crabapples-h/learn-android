package cn.crabapples.application.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;
import cn.crabapples.application.radio.RadioListener;

public class RadioActivity extends AppCompatActivity {
    private final RadioListener.Click RADIO_CLICK = new RadioListener.Click();
    private final RadioListener.Change RADIO_CHANGE = new RadioListener.Change();
    private final RadioListener.GroupChange RADIO_GROUP_CHANGE = new RadioListener.GroupChange();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radio);
    }

    public void showToast() {
        Toast.makeText(RadioActivity.this, "喜欢你", Toast.LENGTH_SHORT).show();
    }


}
