package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "ID BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "NAME VARCHAR(50) NOT NULL, " +
                "LASTNAME VARCHAR(50) NOT NULL, " +
                "AGE TINYINT NOT NULL" +
                ");";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("Таблица 'users' успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при создании таблицы!");
        }
    }


    public void dropUsersTable() {

    }


    public void saveUser(User user) {
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setByte(3, user.getAge());

            statement.executeUpdate();
            System.out.println("Пользователь " + user.getName() + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка при добавлении пользователя!");
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("🔌 Соединение с БД закрыто.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("⚠ Ошибка при закрытии соединения!");
        }
    }
}