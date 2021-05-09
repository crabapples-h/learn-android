package cn.crabapples.main;

import java.sql.*;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/5/9 14:45
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class Demo {
    private final static String sql = "select * from table1";
    private final static String url = "jdbc:mysql://192.168.1.152:3306/demo?serverTimezone=Asia/Shanghai";
    private final static String username = "crabapples";
    private final static String password = "crabapples";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            case1(connection);
            case2(connection);
            return;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            System.err.println(111);
        }
    }

    static void case1(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.err.println(resultSet.getInt(1));
        }
    }

    static void case2(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.err.println(resultSet.getInt(1));
        }
    }
}
