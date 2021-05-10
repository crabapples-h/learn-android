package cn.crabapples.main.activity.demo;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcDemoActivity extends AppCompatActivity {
    private final String TAG = "JdbcDemoActivity";
    private final String url = "jdbc:mysql://192.168.1.152:3307/demo";
    private final String url1 = "jdbc:mysql://39.108.172.202:3306/demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowPublicKeyRetrieval=true";
    private final String username = "crabapples";
    private final String password = "crabapples";
    private Connection connection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdbc_demo);
        requestPermissions(new String[]{Manifest.permission.INTERNET}, 0);
        conn();
    }

    public void conn() {
        if (null == connection) {
            new Thread(() -> {
                for (int i = 0; i < 10 && connection == null; i++) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(url, username, password);
                        System.err.println(connection);
                        Thread.sleep(5000);
                    } catch (ClassNotFoundException | SQLException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    printLog(null == connection ? "连接失败" : "连接成功");
                }
            }).start();
        }
    }

    public void openSqlConnection(View view) {

    }

    public void sqlGetData(View view) {
        new Thread(() -> selectCase1(2)).start();
        new Thread(() -> selectCase2(3)).start();
    }

    void selectCase1(int id) {
        try {
            String sql = "select * from table1" + " where id >" + id;
            System.err.println(sql);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Map<String, Object>> datas = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("id", resultSet.getInt("id"));
                data.put("name", resultSet.getString("name"));
                data.put("username", resultSet.getString("username"));
                data.put("status", resultSet.getInt("status"));
                data.put("scope", resultSet.getInt("scope"));
                datas.add(data);
            }
            System.out.println("selectCase1()");
            System.out.println(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void selectCase2(int id) {
        try {
            String sql = "select * from table1 where id > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<String, Object>> datas = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("id", resultSet.getInt("id"));
                data.put("name", resultSet.getString("name"));
                data.put("username", resultSet.getString("username"));
                data.put("status", resultSet.getInt("status"));
                data.put("scope", resultSet.getInt("scope"));
                datas.add(data);
            }
            System.out.println("selectCase2()");
            System.out.println(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sqlUpdateData(View view) {
        updateCase1(2);
        updateCase2(3);
    }

    void updateCase1(int id) {
        try {
            String sql = "update table1 set status = 1 " + "where id >" + id;
            Statement statement = connection.createStatement();
            int execute = statement.executeUpdate(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateCase2(int id) {
        try {
            String sql = "update table1 set status = 1 where id > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int execute = preparedStatement.executeUpdate(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sqlDelData(View view) {
        delCase1(7);
        delCase2(8);
    }

    void delCase1(int id) {
        try {
            String sql = "delete from table1 " + "where id >" + id;
            Statement statement = connection.createStatement();
            boolean execute = statement.execute(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void delCase2(int id) {
        try {
            String sql = "delete from table1 where id > ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            boolean execute = preparedStatement.execute(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sqlAddData(View view) {
        addCase1();
        addCase2();
    }

    void addCase1() {
        try {
            String sql = "insert into table1 (name,username,status,scope) values('jdbc','jdbc',0,77)";
            Statement statement = connection.createStatement();
            boolean execute = statement.execute(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addCase2() {
        try {
            String sql = "insert into table1 (name,username,status,scope) values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "jdbc2");
            preparedStatement.setString(2, "jdbc2");
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 77);
            boolean execute = preparedStatement.execute(sql);
            System.err.println(execute);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
