package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

public class ListViewDemoActivity extends AppCompatActivity {
    private final String TAG = "ListViewDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
//        ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(new MyAdapter());
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(new MyAdapter1());
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 100;
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
            convertView = convertView == null ? new TextView(ListViewDemoActivity.this) : convertView;
            TextView textView = (TextView) convertView;
            Log.d(TAG, ((TextView) convertView).getText().toString());
            textView.setText("这是第" + position + "条数据");
            return textView;
        }
    }

    class MyAdapter1 extends BaseAdapter {
        /**
         * 设置ListView中显示数据的总条目
         * 注意：数量过大可能导致内存溢出
         */
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 设置listView中显示的数据
         *
         * @param position    当前是第几条数据
         * @param convertView 旧的View对象，可对此对象进行重用，以节约资源避免造成内存溢出(使用前需先判断是否为NULL)
         * @param parent
         * @return 需要显示的view
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "getView()");
            if (convertView == null) {
                /*
                 * 将layout布局文件转换成View对象
                 * 有很多种方式，此处列举四种
                 * 1.使用Activity中的getLayoutInflater()获取LayoutInflater对象，然后使用inflate()方法进行转换
                 * 2.使用LayoutInflater的静态方法获取LayoutInflater对象，然后使用inflate()方法进行转换
                 * 3.使用View的静态方法直接进行转换
                 * 4.使用getSystemService()方法获取LayoutInflater对象，然后使用inflate()方法进行转换
                 * 【在Google的代码中使用这种方式获取LayoutInflater对象】
                 * 以上列举的四种方式会用到三个参数: context,resource,root
                 * 第一个参数context为上下文对象，第二个参数resource为需要转换的布局文件资源ID(R.layout.xxxxxxx)
                 * 第三个参数root为一个ViewGroup，当传入ViewGroup时那么通过这个方法创建出来的View对象会作为传入对象的子元素
                 * 第四种方法中getSystemService()的参数为需要获取的服务实例的名称
                 */
//                convertView = ListViewDemoActivity.this.getLayoutInflater().inflate(R.layout.module_listview, null);
//                convertView = LayoutInflater.from(ListViewDemoActivity.this).inflate(R.layout.module_listview, null);
//                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//                convertView = layoutInflater.inflate(R.layout.module_listview, null);
                convertView = View.inflate(ListViewDemoActivity.this, R.layout.module_listview, null);
            }
            TextView textView = convertView.findViewById(R.id.listview_text);
            textView.setText(String.format("这是第%s条数据", position));
            Log.d(TAG, textView.getText().toString());
            return convertView;
        }
    }


    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
    }
}
