package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() throws SQLException {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS USERTABLE(ID BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " NAME VARCHAR (255), LASTNAME VARCHAR (255), AGE BIGINT);");
            statement.close();
            connection.commit();
        } catch (Exception e){
            if(connection != null){
                connection.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() throws SQLException {

        Connection connection = null;
        try{
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS USERTABLE;");
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = null;
        try{
            connection = Util.getConnection();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO USERTABLE (NAME, LASTNAME, AGE) VALUES (?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERTABLE WHERE ID = ?;");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = null;
        ArrayList<User> result = new ArrayList<>();
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERTABLE;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                result.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE USERTABLE;");
            statement.close();
            connection.commit();
        } catch (SQLException e) {
            if(connection != null){
                connection.rollback();
            }
            throw new RuntimeException(e);
        }
    }
}
