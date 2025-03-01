package jdbc;

import jdbc.model.User;
import jdbc.service.UserService;
import jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Запуск программы...");

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 30);
        System.out.println(" User с именем Иван Иванов добавлен в базу данных");

        userService.saveUser("Вероника", "Сидорова", (byte) 25);
        System.out.println(" User с именем Вероника Сидорова добавлен в базу данных");

        userService.saveUser("Петр", "Николаев", (byte) 40);
        System.out.println(" User с именем Петр Николаев добавлен в базу данных");

        userService.saveUser("Алена", "Козлова", (byte) 35);
        System.out.println(" User с именем Алена Козлова добавлен в базу данных");

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







