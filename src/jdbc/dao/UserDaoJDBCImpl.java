package jdbc.dao;

import jdbc.model.User;
import jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
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
        String sql = "DROP TABLE IF EXISTS users";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("✅ Таблица 'users' успешно удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка при удалении таблицы!");
        }
    }


    public void saveUser(User user) {
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setByte(3, user.getAge());

            statement.executeUpdate();
            System.out.println("✅ Пользователь " + user.getName() + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка при добавлении пользователя!");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Пользователь с ID " + id + " удалён.");
            } else {
                System.out.println("⚠ Пользователь с ID " + id + " не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка при удалении пользователя!");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка при получении списка пользователей!");
        }

        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("✅ Таблица 'users' очищена.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Ошибка при очистке таблицы!");
        }
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