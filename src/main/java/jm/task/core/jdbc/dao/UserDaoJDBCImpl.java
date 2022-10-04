package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement prs = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users(\n" +
                     "    id int primary key auto_increment,\n" +
                     "    name varchar(45) not null,\n" +
                     "    lastname varchar(45) not null,\n" +
                     "    age int \n" +
                     ")")) {
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement prs = connection.prepareStatement("drop table users")) {
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prs = connection.prepareStatement("insert into users(name,lastname,age) value (?,?,?)")) {
            prs.setString(1, name);
            prs.setString(2, lastName);
            prs.setInt(3, age);
            prs.executeUpdate();
            System.out.println("Пользователь " + name + " сохранен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prs = connection.prepareStatement("delete from users where id = ?")) {
            prs.setLong(1, id);
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = Util.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getByte("age"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                }
            }
            return listUser;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             PreparedStatement prs = connection.prepareStatement("truncate table users")) {
            prs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
