package cn.crabapples.application.activity;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;
import cn.crabapples.application.timepicker.TimePickerListener;

public class TimePickerActivity extends AppCompatActivity {
    private final TimePickerListener.Change TIME_PICKER_CHANGE = new TimePickerListener.Change();
    private final TimePickerListener.GetDate TIME_PICKER_GET_DATE = new TimePickerListener.GetDate();
    private final TimePickerListener.GetTime TIME_PICKER_GET_TIME = new TimePickerListener.GetTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker);
    }

    public void showToast() {
        Toast.makeText(TimePickerActivity.this, "喜欢你", Toast.LENGTH_SHORT).show();
    }


}
