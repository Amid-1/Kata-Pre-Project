package jdbc;

import jdbc.dao.UserDaoJDBCImpl;
import jdbc.model.User;
import jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        System.out.println("Попытка подключения к базе данных...");

        UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        User user = new User("Vasya", "Ivanov", (byte) 25);

        userDao.saveUser(user);
        System.out.println("✅ Данные успешно добавлены в таблицу!");
        
        userDao.closeConnection();
    }
}







