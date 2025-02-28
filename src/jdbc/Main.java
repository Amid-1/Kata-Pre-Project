package jdbc;

import jdbc.util.Util;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Попытка подключения к базе данных...");

        try (Connection connection = Util.getConnection()) {
            System.out.println("✅ Подключение успешно!");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка подключения к БД!");
            e.printStackTrace();
        }
    }
}
