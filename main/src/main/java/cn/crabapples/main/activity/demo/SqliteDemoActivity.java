package cn.crabapples.main.activity.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteDemoActivity extends AppCompatActivity {
    private final String TAG = "SqliteDemoActivity";
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);
        SQLiteOpenHelper helper = new SQLiteOpener(this, "demo.db", null, 1);
        database = helper.getWritableDatabase();
    }


    static class SQLiteOpener extends SQLiteOpenHelper {
        /**
         * 构造方法
         *
         * @param context 上下文信息
         * @param name    数据库名称，如果传入null，则会在ram中创建一个数据库，在ram中创建的数据库会在断电或app退出时被释放
         * @param factory 游标工厂，通常情况下传入null，表示使用系统内置的游标工厂
         * @param version 数据库版本，从1开始，用来控制数据库的升级和降级
         */
        public SQLiteOpener(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        private final String SQL_CREATE_TABLE = "create table demo_table1(_id integer primary key,name varchar(32),score varchar(2))";
        private final String SQL_INSERT_DATA = "insert into demo_table1 values (1,'zhangsan',99.9)";

        /**
         * 数据库在第一次创建的时候会调用onCreate方法
         * 可以在这里做表结构的创建和数据的初始化等
         * 只会调用一次
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
            db.execSQL(SQL_INSERT_DATA);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }


    public void sqlGetData(View view) {
        selectCase1(2);
//        selectCase2(3);
    }

    void selectCase1(int id) {
        String sql = "select * from sqlite_demo" + "where id >" + id;
        String sql1 = "select * from demo_table1";
        Cursor resultSet = database.rawQuery(sql1, null);
        List<Map<String, Object>> datas = new ArrayList<>();
        while (resultSet.moveToNext()) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", resultSet.getInt(0));
            data.put("name", resultSet.getString(1));
            data.put("score", resultSet.getDouble(2));
            datas.add(data);
        }
        System.err.println(datas);
    }

//    void selectCase2(int id) {
//        try {
//            String sql = "select * from table1 where id > ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Map<String, Object>> datas = new ArrayList<>();
//            while (resultSet.next()) {
//                Map<String, Object> data = new HashMap<>();
//                data.put("id", resultSet.getInt("id"));
//                data.put("name", resultSet.getInt("name"));
//                data.put("username", resultSet.getInt("username"));
//                data.put("status", resultSet.getInt("status"));
//                data.put("scope", resultSet.getInt("scope"));
//                datas.add(data);
//            }
//            System.err.println(datas);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void sqlUpdateData(View view) {
//        updateCase1(2);
//        updateCase2(3);
    }

//    void updateCase1(int id) {
//        try {
//            String sql = "update table1 set status = 1 " + "where id >" + id;
//            Statement statement = connection.createStatement();
//            int execute = statement.executeUpdate(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    void updateCase2(int id) {
//        try {
//            String sql = "update table1 set status = 1 where id > ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            int execute = preparedStatement.executeUpdate(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void sqlDelData(View view) {
//        delCase1(7);
//        delCase2(8);
    }

//    void delCase1(int id) {
//        try {
//            String sql = "delete from table1 " + "where id >" + id;
//            Statement statement = connection.createStatement();
//            boolean execute = statement.execute(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    void delCase2(int id) {
//        try {
//            String sql = "delete from table1 where id > ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            boolean execute = preparedStatement.execute(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void sqlAddData(View view) {
//        addCase1();
//        addCase2();
    }

//    void addCase1() {
//        try {
//            String sql = "insert into table1 (name,username,status,scope) values('jdbc','jdbc',0,77)";
//            Statement statement = connection.createStatement();
//            boolean execute = statement.execute(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    void addCase2() {
//        try {
//            String sql = "insert into table1 (name,username,status,scope) values(?,?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, "jdbc2");
//            preparedStatement.setString(2, "jdbc2");
//            preparedStatement.setInt(3, 1);
//            preparedStatement.setInt(4, 77);
//            boolean execute = preparedStatement.execute(sql);
//            System.err.println(execute);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

    public void back(View view) {
    }


}
