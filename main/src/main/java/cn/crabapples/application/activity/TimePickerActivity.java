package cn.crabapples.application.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.R;

public class TimePickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_picker);
        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener((view, hourOfDay, minute) -> {
            String time = "时间:" + hourOfDay + ":" + minute;
            Toast.makeText(TimePickerActivity.this, time, Toast.LENGTH_LONG).show();
        });
    }


    public void getTime(View view) {
        TimePicker timePicker = findViewById(R.id.timePicker);
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String time = "时间:" + hour + ":" + minute;
        Toast.makeText(TimePickerActivity.this, time, Toast.LENGTH_LONG).show();
    }

    public void getDate(View view) {
        DatePicker datePicker = findViewById(R.id.datePicker);
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        String date = "日期:" + year + ":" + month + ":" + day;
        Toast.makeText(TimePickerActivity.this, date, Toast.LENGTH_LONG).show();
    }

    public void back(View view) {
    }
}
