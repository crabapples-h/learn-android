package cn.crabapples.main.activity.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;
import cn.crabapples.main.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewDemoActivity extends AppCompatActivity {
    private final String TAG = "ListViewDemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(new MyAdapter());
        ListView listView1 = findViewById(R.id.listView1);
        listView1.setAdapter(new MyAdapter1());
        /*
         * 创建ArrayAdapter时用三个参数的构造方法
         * 第一个参数为上下文对象
         * 第二个参数为布局文件资源ID (且布局文件中只能包含一个TextView)
         * 第三个参数为需要渲染的数据 (Array或List)
         */
        ListView listView2 = findViewById(R.id.listView2);
        listView2.setAdapter(new ArrayAdapter<>(this, R.layout.module_listview_array, initData()));
        /*
         * 创建ArrayAdapter时用四个参数的构造方法
         * 第一个参数为上下文对象
         * 第二个参数为布局文件资源ID
         * 第三个参数为布局资源文件中TextView的ID
         * 第四个参数为需要渲染的数据 (Array或List)
         */
        ListView listView3 = findViewById(R.id.listView3);
        listView3.setAdapter(new ArrayAdapter<>(this, R.layout.module_listview_array1, R.id.listview_text1, initData()));
        /*
         * 使用SimpleAdapter渲染数据
         * 创建SimpleAdapter需要五个参数，分别为：context,data,resource,from,to
         * 第一个参数context为上下文对象
         * 第二个参数data是一个List<Map>，内容为需要渲染的数据
         * 第三个参数resource为布局文件资源ID
         * 第四个参数from是一个字符串数组，内容为第二个参数中Map的key，且顺序与第五个参数组件的Id顺序对应
         * 第五个参数to是一个字符串数组，内容为布局文件中需要渲染的组件的id，且顺序与第四个参数组件的顺序对应
         *
         * 原理为：
         * 1.通过from中传入的字符串从data中分别取出对应的内容
         * 2.通过to中对应传入的id从resource中找到对应的组件
         * 3.将第1步中取出的内容按照from的顺序渲染到第2步中找到的组件上
         * 例如：
         * 1.通过from中传入的"text1" 从data取出对应的内容为 "这个是text1:"
         * 2.在resource中到to中传入的ID对应的组件，id为listview_text3一个TextView
         * 3.将text1对应的内容填入id为listview_text3的TextView中
         */
        ListView listView4 = findViewById(R.id.listView4);
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, String> item = new HashMap<>();
            item.put("text1", "这个是text1:" + i);
            item.put("text2", "这个是text2:" + i);
            item.put("text3", "这个是text3:" + i);
            data.add(item);
        }
        String[] from = new String[]{"text1", "text2", "text3"};
        int[] to = new int[]{R.id.listview_text3, R.id.listview_text4, R.id.listview_text5};
        listView4.setAdapter(new SimpleAdapter(this, data, R.layout.module_listview_simple, from, to));
    }

    public List<String> initData() {
        ArrayList<String> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.name = "user:" + i;
            user.sex = (byte) (i % 2);
            user.age = i;
            users.add(user.toString());
        }
        return users;
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
                 * 【Google推荐使用这种方式获取LayoutInflater对象】
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
