package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private Util() {
    }

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "astazkin94";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static SessionFactory sessionFactory;
    private static Util instance = null;


    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            if (sessionFactory == null) {
                sessionFactory = new Configuration()
                        .setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                        .setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest")
                        .setProperty(Environment.USER, "root")
                        .setProperty(Environment.PASS, "astazkin94")
                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .setProperty(Environment.SHOW_SQL, "true")
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
