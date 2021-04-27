package cn.crabapples.radio;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

public class MainActivity extends AppCompatActivity {
    RadioGroup group1;
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        group1 = findViewById(R.id.group1);
        radio1 = findViewById(R.id.radio1);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);
        GroupChangeListener listener = new GroupChangeListener();
        group1.setOnCheckedChangeListener(listener);

    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

        }
    }

    class GroupChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (radio1.getId() == checkedId) {
                radio3.setChecked(radio1.isChecked());
            } else if (radio2.getId() == checkedId) {
                radio4.setChecked(radio2.isChecked());
            }
        }
    }

    class ChangeListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            System.out.println("选中");
        }
    }
}
