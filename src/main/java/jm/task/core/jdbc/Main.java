package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        dao.saveUser("Igor","Ivanov", (byte) 45);
        dao.saveUser("Aleksander","Petrov", (byte) 33);
        dao.saveUser("Lilia","Iva", (byte) 30);
        dao.saveUser("Olga","Ivan", (byte) 48);
    }
}
