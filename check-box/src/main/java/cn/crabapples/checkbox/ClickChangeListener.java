package cn.crabapples.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import cn.crabapples.checkbox.R;

public class ClickChangeListener extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        System.out.println(R.id.eatId);
        System.out.println(findViewById(R.id.eatId));
//        CheckBox box = findViewById(R.id.eatId);
//        if(b){
//            box.setChecked(true);
//        }else {
//            box.setChecked(false);
//        }
    }
}
