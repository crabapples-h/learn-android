package cn.crabapples.application.radio;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class RadioListener extends AppCompatActivity {

    public static class Click implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i("radio", "radioListener:触发了onClick()");
        }
    }

    public static class Change implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.i("radio", "radioListener:触发了onCheckedChanged()");
        }
    }

    public static class GroupChange implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Log.i("radioGroup", "radioListener:触发了onCheckedChanged()");
        }
    }


}
