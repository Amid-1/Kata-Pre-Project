package jdbc;

import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы...");

        // Создаем объект сервиса, который взаимодействует с DAO
        UserService userService = new UserServiceImpl();

        // 1. Создание таблицы
        userService.createUsersTable();

        // 2. Добавление 4 пользователей
        userService.saveUser("Иван", "Иванов", (byte) 30);
        System.out.println(" User с именем Иван добавлен в базу данных");

        userService.saveUser("Вероника", "Сидорова", (byte) 25);
        System.out.println(" User с именем Вероника добавлен в базу данных");

        userService.saveUser("Петр", "Николаев", (byte) 40);
        System.out.println(" User с именем Петр добавлен в базу данных");

        userService.saveUser("Алена", "Козлова", (byte) 35);
        System.out.println(" User с именем Алена добавлен в базу данных");

        System.out.println("\n Список всех пользователей:");
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        System.out.println("\n🗑 Таблица пользователей очищена");

        userService.dropUsersTable();
        System.out.println("🗑 Таблица пользователей удалена");

        System.out.println("\n Программа завершена.");
    }
}







