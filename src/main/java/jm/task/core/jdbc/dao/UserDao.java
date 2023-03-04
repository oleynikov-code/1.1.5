package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    @Transactional
    void createUsersTable() throws SQLException;

    @Transactional
    void dropUsersTable() throws SQLException;

    @Transactional
    void saveUser(String name, String lastName, byte age) throws SQLException;

    @Transactional
    void removeUserById(long id) throws SQLException;

    @Transactional
    List<User> getAllUsers() throws SQLException;

    @Transactional
    void cleanUsersTable() throws SQLException;
}
