package cn.crabapples.timepicker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final TimePickerChangeListener timePickerChangeListener = new TimePickerChangeListener();
    private final getTimeClickListener getTimeClickListener = new getTimeClickListener();
    private final getDateClickListener getDateClickListener = new getDateClickListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimePicker timePicker = findViewById(R.id.timePicker);
        Button getTime = findViewById(R.id.getTime);
        Button getDate = findViewById(R.id.getDate);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(timePickerChangeListener);
        getTime.setOnClickListener(getTimeClickListener);
        getDate.setOnClickListener(getDateClickListener);
    }

    public class getTimeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TimePicker timePicker = findViewById(R.id.timePicker);
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();
            String time = "时间:" + hour + ":" + minute;
            System.err.println(time);
            Toast.makeText(MainActivity.this, time, Toast.LENGTH_LONG).show();
        }
    }

    public class getDateClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            DatePicker datePicker = findViewById(R.id.datePicker);
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();
            String date = "日期:" + year + ":" + month + ":" + day;
            System.err.println(date);
            Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
        }
    }

}
