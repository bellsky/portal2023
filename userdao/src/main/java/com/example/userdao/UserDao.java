package com.example.userdao;

import com.example.userdao.User;

import java.sql.*;

public class UserDao {
    public User findById(Long id) throws ClassNotFoundException, SQLException {


        Class.forName("org.mariadb.jdbc.Driver");


        Connection connection = DriverManager.getConnection
                ("jdbc:mariadb://138.2.32.29:3306/USERINFO", "bellsky", "test1234");

        PreparedStatement preparedStatement = connection.prepareStatement
                ("select id, name, password from userinfo where ?");
        preparedStatement.setLong(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }
}