package cn.crabapples.main.activity.demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewDemo1Activity extends AppCompatActivity {
    private final String TAG = "ListViewDemo1Activity";
    private SQLiteOpenHelper openHelper;
    private final ArrayList<Map<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo1);
        openHelper = new SQLiteOpener(this, "listViewDemo.db", null, 1);
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.module_listview1, null);
            }
            TextView textView1 = convertView.findViewById(R.id.listview_text1);
            TextView textView2 = convertView.findViewById(R.id.listview_text2);
            TextView textView3 = convertView.findViewById(R.id.listview_text3);
            String id = String.valueOf(data.get(position).get("id"));
            String name = String.valueOf(data.get(position).get("name"));
            String age = String.valueOf(data.get(position).get("age"));
            Log.d(TAG, "getView: " + position);
            Log.d(TAG, "getView: id:" + id + ",name:" + name + ",age:" + age);
            textView1.setText(id);
            textView2.setText(name);
            textView3.setText(age);
            return convertView;
        }
    }

    static class SQLiteOpener extends SQLiteOpenHelper {

        public SQLiteOpener(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String create = "create table table1 (id INTEGER primary key AUTOINCREMENT,name varchar(64),age INTEGER)";
            db.execSQL(create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void addData(View view) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", "姓名:" + (int) (Math.random() * 100));
        values.put("age", (int) (Math.random() * 100));
        database.insert("table1", null, values);
        database.close();
    }

    public void resetData(View view) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        database.delete("table1", "", null);
    }

    public void getData(View view) {
        SQLiteDatabase database = openHelper.getReadableDatabase();
        Cursor cursor = database.query("table1", new String[]{"id", "name", "age"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", cursor.getInt(cursor.getColumnIndex("id")));
            item.put("name", cursor.getString(cursor.getColumnIndex("name")));
            item.put("age", cursor.getInt(cursor.getColumnIndex("age")));
            data.add(item);
        }
        cursor.close();
        database.close();
        Log.d(TAG, "showData(): " + data);
    }

    public void showData(View view) {
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
    }


}
