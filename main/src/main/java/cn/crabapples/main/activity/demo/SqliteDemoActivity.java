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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteDemoActivity extends AppCompatActivity {
    private final String TAG = "crabapples";
    private SQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);
        helper = new SQLiteOpener(this, "demo.db", null, 4);
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
        private final String SQL_CREATE_TABLE = "create table demo_table1(_id integer primary key AUTOINCREMENT,name varchar(32),score varchar(2))";
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

        /**
         * 当数据库升级的时候会调用onUpgrade方法
         * 可以在这里做表结构的修改等
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "oldVersion：" + oldVersion + ",newVersion：" + newVersion);
            String sql = "alter table demo_table1 add id_card_1 varchar(18)";
            db.execSQL(sql);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "oldVersion：" + oldVersion + ",newVersion：" + newVersion);
        }
    }

    public void sqlGetData(View view) {
        selectCase1(2);
        selectCase2(3);
    }

    void selectCase1(int id) {
        String sql = "select * from demo_table1 where _id > ?";
        SQLiteDatabase database = helper.getReadableDatabase();
        /*
         * 使用rawQuery方法进行查询，rawQuery方法接收两个参数
         * rawQuery有两种用法
         * 1.直接在第一个参数传入完整的sql，第二个参数为null
         * 如 ：select * from demo_table1 where _id > 9
         * 2.在第一个参数传入一条带有占位符的sql(和jdbc的预编译sql类似)，第二个参数传入一个用于替换占位符字符串数组
         * 如：select * from demo_table1 where _id > ?
         * 查询后返回一个Cursor对象，用法和JDBC的ResultSet类似
         */
        Cursor cursor = database.rawQuery(sql, new String[]{String.valueOf(id)});
        List<Map<String, Object>> datas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, Object> data = new HashMap<>();
            data.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
            data.put("name", cursor.getString(cursor.getColumnIndex("name")));
            data.put("score", cursor.getDouble(cursor.getColumnIndex("score")));
            datas.add(data);
        }
        cursor.close();
        database.close();
        Log.d(TAG, datas.toString());
    }

    void selectCase2(int id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        /*
         * 使用query()方法进行查询，query方法接收多个参数
         * 分别为：table,columns,selection,selectionArgs,groupBy,having,orderBy,limit
         * 其中table为需要执行查询的表名，columns为需要查询的字段，selectionArgs为
         * selection为查询参数，即where条件，传入格式为：age > 10，或：age > ? and name like ?
         * selectionArgs为用于替换对应占位符的字符串数组
         * groupBy，having，orderBy分别对应sql中的各种操作
         * 特别的，最后一个参数limit为可选参数，如不需要则可不传
         * 查询后返回一个Cursor对象，用法和JDBC的ResultSet类似
         */
        Cursor cursor = database.query("demo_table1", new String[]{"_id", "name", "score"}, "_id % 2 = cast(? as int)", new String[]{"0"}, null, null, null);
        List<Map<String, Object>> datas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, Object> data = new HashMap<>();
            data.put("_id", cursor.getInt(cursor.getColumnIndex("_id")));
            data.put("name", cursor.getString(cursor.getColumnIndex("name")));
            data.put("score", cursor.getDouble(cursor.getColumnIndex("score")));
            datas.add(data);
        }
        cursor.close();
        database.close();
        Log.d(TAG, datas.toString());
    }

    public void sqlAddData(View view) {
        addCase1();
        addCase2();
    }

    void addCase1() {
        String sql = "insert into demo_table1 (name,score)values('add1','add1')";
        Log.d(TAG, sql);
        SQLiteDatabase database = helper.getReadableDatabase();
        database.execSQL(sql);
        database.close();
        Log.d(TAG, "addCase1()");
    }

    void addCase2() {
        SQLiteDatabase database = helper.getReadableDatabase();
        /*
         * 使用insert()方法进行添加，返回值为插入的最后一条数据的ID，如果操作失败则返回-1
         * insert方法接收三个参数，分别为：table,nullColumnHack,values
         * 第一个参数为表名，第三个参数为需要插入的数据，格式为 Key:Value的形式
         * 第二个参数为一种容错机制
         * 当第三个参数为null或者size<0时，sql会变成类似insert into tableName()values()的形式，这是不被允许的
         * 当传入第二个参数后，如果第三个参数为null或者size<0时，会将第二参数作为字段名，插入一个null。例如：
         * insert into tableName (nullColumnHack)values(null)
         */
        ContentValues values = new ContentValues();
        values.put("name", "add2");
        values.put("score", "add2");
        long result = database.insert("demo_table1", "nullColumnHack", values);
        database.close();
        Log.d(TAG, "addCase1():" + result);
    }

    public void sqlUpdateData(View view) {
        updateCase1(18);
        updateCase2(22);
    }

    void updateCase1(int id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "update demo_table1 set name = 'update1' where _id = " + id;
        Log.d(TAG, "updateCase1: " + sql);
        database.execSQL(sql);
        database.close();
        Log.d(TAG, "updateCase1()");
    }

    /*
     * 使用execSQL()方法可以执行任意sql，无返回值
     */
    void updateCase2(int id) {
        SQLiteDatabase database = helper.getReadableDatabase();
        /*
         * 使用update()方法进行修改，返回值为受影响的数据条数
         * update方法接收四个参数，分别为：table,values,whereClause,whereArgs
         * 第一个参数为表名
         * 第二个参数为需要修改的字段，格式为 Key:Value的形式
         * 第三个参数为where条件，传入格式为：age > 10，或：age > ? and name like ?
         * 当第三个参数未包含占位符时，第四个参数需为null
         * 当第三个参数传入包含占位符的的字符串时，第四个参数需传入用于替换对应占位符的字符串数组
         * 注意 ：使用预编译的sql时，占位符替换的值都是字符串，如果需要对其他数据类型进行匹配，则需要进行类型转换
         */
        ContentValues values = new ContentValues();
        values.put("name", "update2");
        values.put("score", "99.99");
        int row = database.update("demo_table1", values, "_id > ?", new String[]{String.valueOf(id)});
        database.close();
        Log.d(TAG, "updateCase2():" + row);
    }

    public void sqlDelData(View view) {
        delCase1();
        delCase2();
    }

    void delCase1() {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "delete from demo_table1  where _id % 2 = 1";
        Log.d(TAG, "delCase1: " + sql);
        database.execSQL(sql);
        database.close();
        Log.d(TAG, "delCase1()");
    }

    void delCase2() {
        SQLiteDatabase database = helper.getReadableDatabase();
        /*
         * 使用delete()方法进行修改，返回值为受影响的数据条数
         * delete方法接收三个参数，分别为：table,whereClause,whereArgs
         * 第一个参数为表名
         * 第二个参数为where条件，传入格式为：age > 10，或：age > ? and name like ?，如果需要删除所有数据，则传入一个1
         * 当第二个参数未包含占位符时，第三个参数需为null
         * 当第二个参数传入包含占位符的的字符串时，第三个参数需传入用于替换对应占位符的字符串数组
         * 注意 ：使用预编译的sql时，占位符替换的值都是字符串，如果需要对其他数据类型进行匹配，则需要进行类型转换
         */
        long result = database.delete("demo_table1", "_id % 2 = cast(? as int)", new String[]{"0"});
        database.close();
        Log.d(TAG, "delCase2():" + result);
    }

    public void sqlDelAll(View view) {
        SQLiteDatabase database = helper.getReadableDatabase();
        String sql = "delete from demo_table1";
        Log.d(TAG, "sqlDelAll: " + sql);
        database.execSQL(sql);
        database.close();
        Log.d(TAG, "sqlDelAll()");
    }

    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    public void printLog(String content) {
        Log.i(TAG, content);
    }

    public void back(View view) {
    }

}
