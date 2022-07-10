package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.Main.connection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE user_db (" +
                    "id BIGSERIAL PRIMARY KEY ," +
                    "name VARCHAR(128) NOT NULL ," +
                    "lastname VARCHAR(128) NOT NULL ," +
                    "age SMALLINT NOT NULL );";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {

        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE user_db";

            statement.executeUpdate(SQL);
        } catch (SQLException e) {

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO user_db(name, lastname, age) " +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + ")";
            statement.executeUpdate(SQL);

            System.out.println("User " + name + " successfully added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM user_db " +
                    "WHERE id = " + id;
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM user_db";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM user_db";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
