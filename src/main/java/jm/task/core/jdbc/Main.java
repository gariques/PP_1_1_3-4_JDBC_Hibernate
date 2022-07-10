package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();
//        userDaoJDBC.dropUsersTable();

        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 18);
        userDaoJDBC.saveUser("Petr", "Petrov", (byte) 23);
        userDaoJDBC.saveUser("Semen", "Semenov", (byte) 18);
        userDaoJDBC.saveUser("Dua", "Lipa", (byte) 31);
        userDaoJDBC.saveUser("Ana", "De Armas", (byte) 27);

        userDaoJDBC.removeUserById(2);
        System.out.println(userDaoJDBC.getAllUsers());
//
        userDaoJDBC.cleanUsersTable();
    }
}
