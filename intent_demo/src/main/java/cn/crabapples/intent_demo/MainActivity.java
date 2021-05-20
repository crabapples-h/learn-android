package cn.crabapples.intent_demo;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra("sms_body", "喜欢你");
            startActivity(intent);
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