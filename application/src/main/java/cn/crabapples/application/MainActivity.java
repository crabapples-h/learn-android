package cn.crabapples.application;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.application.checkbox.CheckBoxListener;
import cn.crabapples.application.radio.RadioListener;
import cn.crabapples.application.timepicker.TimePickerListener;

public class MainActivity extends AppCompatActivity {
    private final int LAYOUT_APPLICATION = R.layout.application;
    private final int LAYOUT_CHECK_BOX = R.layout.check_box;
    private final int LAYOUT_IMAGE_VIEW = R.layout.image_view;
    private final int LAYOUT_RADIO = R.layout.radio;
    private final int LAYOUT_TIME_PICKER = R.layout.time_picker;
    private final CheckBoxListener.Click CHECK_BOX_CLICK = new CheckBoxListener.Click();
    private final CheckBoxListener.Change CHECK_BOX_CHANGE = new CheckBoxListener.Change();
    private final RadioListener.Click RADIO_CLICK = new RadioListener.Click();
    private final RadioListener.Change RADIO_CHANGE = new RadioListener.Change();
    private final RadioListener.GroupChange RADIO_GROUP_CHANGE = new RadioListener.GroupChange();
    private final TimePickerListener.Change TIME_PICKER_CHANGE = new TimePickerListener.Change();
    private final TimePickerListener.GetDate TIME_PICKER_GET_DATE = new TimePickerListener.GetDate();
    private final TimePickerListener.GetTime TIME_PICKER_GET_TIME = new TimePickerListener.GetTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_CHECK_BOX);
    }

    public void showToast() {
        Toast.makeText(MainActivity.this, "喜欢你", Toast.LENGTH_SHORT).show();
    }


}
