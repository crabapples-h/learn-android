package cn.crabapples.application.checkbox;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

public class CheckBoxListener {
    public static class Click implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i("checkBox","checkListener:触发了onClick()");
        }
    }

    public static class Change implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            Log.i("checkBox","checkListener:触发了onCheckedChanged()");
//            Toast.makeText(MainActivity.this, "测试，测试", Toast.LENGTH_SHORT).show();
//            CheckBox box = findViewById(R.id.eatId);
//            if(b){
//                box.setChecked(true);
//            }else {
//                box.setChecked(false);
//            }
        }
    }
}
