package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2401";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("✅ Подключение успешно!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка подключения к БД!");
        }
        return connection;
    }
}

