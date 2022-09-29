package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
//        UserDao dao = new UserDaoJDBCImpl();
//        dao.createUsersTable();
//        dao.saveUser("Lilia", "Per", (byte) 30);
//        dao.saveUser("Olga", "Iva", (byte) 48);
//        dao.saveUser("Aleksander", "Petrov", (byte) 48);
//        dao.saveUser("Igor", "Ivanov", (byte) 48);
//        dao.getAllUsers().forEach(System.out::println);
//        dao.cleanUsersTable();
//        dao.dropUsersTable();


        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("LILIA", "IVAN", (byte) 33);
        userDao.saveUser("ALEKSANDER", "MARK", (byte) 45);
        userDao.saveUser("PETR", "OLGA", (byte) 23);
        userDao.saveUser("IVAN", "IGOR", (byte) 18);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
