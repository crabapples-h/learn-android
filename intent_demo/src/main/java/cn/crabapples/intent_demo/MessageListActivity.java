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

public class MessageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ListView listView = findViewById(R.id.listView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView messageView = (TextView) view;
            String message = messageView.getText().toString();
            Intent intent = getIntent();
            intent.putExtra("message", message);
            setResult(777, intent);
            finish();
        });
    }

    class MyAdapter extends BaseAdapter {
        List<String> data = new ArrayList<>();
        LayoutInflater inflater;

        MyAdapter() {
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < 50; i++) {
                data.add(String.format("这是第%s条数据", i));
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
                convertView = inflater.inflate(R.layout.module_listview, null);
            }
            TextView view = (TextView) convertView;
            view.setText(data.get(position));
            return view;
        }
    }
}