package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

//        UserDao userDao = new UserDaoJDBCImpl();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();
//        userDao.dropUsersTable();

        userDao.saveUser("Ivan", "Ivanov", (byte) 18);
        userDao.saveUser("Petr", "Petrov", (byte) 23);
        userDao.saveUser("Semen", "Semenov", (byte) 18);
        userDao.saveUser("Dua", "Lipa", (byte) 31);
        userDao.saveUser("Ana", "De Armas", (byte) 27);

//        userDao.removeUserById(3);
        System.out.println(userDao.getAllUsers());
//
        userDao.cleanUsersTable();


    }
}
