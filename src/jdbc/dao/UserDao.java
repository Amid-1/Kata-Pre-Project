package jdbc.dao;

import jdbc.model.User;

import java.util.List;

public interface UserDao {

    void createUsersTable();

    void dropUsersTable();

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
