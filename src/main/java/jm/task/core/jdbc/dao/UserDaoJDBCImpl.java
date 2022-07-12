package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE user_db (" +
                "id BIGSERIAL PRIMARY KEY ," +
                "name VARCHAR(128) NOT NULL ," +
                "lastname VARCHAR(128) NOT NULL ," +
                "age SMALLINT NOT NULL );";

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(SQL);

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE user_db";

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(SQL);

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO user_db(name, lastname, age) " +
                "VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("User " + name + " " + lastName + " successfully added to database.");
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM user_db " +
                "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            connection.commit();

            System.out.println("User with id = " + id + " successfully deleted from database.");
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

        String SQL = "SELECT * FROM user_db";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String SQL = "DELETE FROM user_db";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
