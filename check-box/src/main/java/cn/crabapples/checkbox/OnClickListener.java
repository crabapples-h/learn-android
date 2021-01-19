package cn.crabapples.checkbox;

import android.view.View;
import android.widget.CheckBox;

public class OnClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        CheckBox box = (CheckBox) view;
        System.out.println("测试，测试");
        System.out.println(box.isChecked());
    }
}
