package cn.crabapples.main.activity.demo;

import android.content.ContentValues;
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
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteDemo1Activity extends AppCompatActivity {
    private SQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo1);
        helper = new SQLiteOpener(this, "demo1.db", null, 1);
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

        private final String TAG = "SQLiteOpener";
        private final String SQL_CREATE_TABLE = "create table demo_table(_id integer primary key AUTOINCREMENT,name varchar(32),money double(5))";
        private final String SQL_INSERT_DATA1 = "insert into demo_table (name,money)values ('zhangsan',1000)";
        private final String SQL_INSERT_DATA2 = "insert into demo_table (name,money)values ('lisi',1000)";

        /**
         * 数据库在第一次创建的时候会调用onCreate方法
         * 可以在这里做表结构的创建和数据的初始化等
         * 只会调用一次
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
            db.execSQL(SQL_INSERT_DATA1);
            db.execSQL(SQL_INSERT_DATA2);
        }

        /**
         * 当数据库升级的时候会调用onUpgrade方法
         * 可以在这里做表结构的修改等
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "oldVersion：" + oldVersion + ",newVersion：" + newVersion);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "oldVersion：" + oldVersion + ",newVersion：" + newVersion);
        }
    }

    public void getMoney(View view) {
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor = database.query("demo_table", new String[]{"_id", "name", "money"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", cursor.getInt(cursor.getColumnIndex("_id")));
            jsonObject.put("name", cursor.getInt(cursor.getColumnIndex("name")));
            jsonObject.put("money", cursor.getInt(cursor.getColumnIndex("money")));
            showToast(jsonObject.toJSONString());
            printLog(jsonObject.toJSONString());
        }
        cursor.close();
        database.close();
    }

    public void trans(View view) {
        showToast("发起转账");
        printLog("发起转账");
        SQLiteDatabase database = helper.getReadableDatabase();
        database.beginTransaction();
        try {
            database.execSQL("update demo_table set money = money - 100 where name = 'zhangsan'");
            database.execSQL("update demo_table set money = money + 100 where name = 'lisi'");
            database.setTransactionSuccessful();
            showToast("转账完成");
            printLog("转账完成");
        } catch (Exception e) {
            showToast("转账失败");
            printLog("转账失败");
        } finally {
            database.endTransaction();
            database.close();
        }
    }

    public void resetData(View view) {
        SQLiteDatabase database = helper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("money", 1000.00);
        database.update("demo_table", values, null, null);
        showToast("重置数据");
        printLog("重置数据");
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        String TAG = "crabapples";
        Log.i(TAG, content);
    }

    public void back(View view) {
    }

}
