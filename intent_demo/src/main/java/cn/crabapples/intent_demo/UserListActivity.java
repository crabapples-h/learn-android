package cn.crabapples.intent_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import cn.crabapples.intent_demo.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ListView listView = findViewById(R.id.listView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView nameView = view.findViewById(R.id.name);
            TextView phoneView = view.findViewById(R.id.phone);
            User user = new User();
            user.name = nameView.getText().toString();
            user.phone = phoneView.getText().toString();
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtra("user", bundle);
            setResult(777, intent);
            finish();
        });
    }


    class MyAdapter extends BaseAdapter {
        List<User> data = new ArrayList<>();
        LayoutInflater inflater;

        MyAdapter() {
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            User user1 = new User();
            user1.name = "测试";
            user1.phone = "13765710705";
            data.add(user1);
            for (int i = 0; i < 50; i++) {
                User user = new User();
                user.name = String.format("小明%s", i);
                user.phone = String.format("133333%s", i);
                data.add(user);
            }
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.module_userlist, null);
            }
            TextView name = convertView.findViewById(R.id.name);
            TextView phone = convertView.findViewById(R.id.phone);
            name.setText(data.get(position).name);
            phone.setText(data.get(position).phone);
            return convertView;
        }
    }
}