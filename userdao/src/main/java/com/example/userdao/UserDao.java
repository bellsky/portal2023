package com.example.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User findById(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement
                    ("select id, name, password from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
//    public User findById(Long id) throws ClassNotFoundException, SQLException {
//
//
//        Connection connection = dataSource.getConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement
//                ("select id, name, password from userinfo where id = ?");
//        preparedStatement.setLong(1, id);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//        resultSet.next();
//
//        User user = new User();
//        user.setId(resultSet.getLong("id"));
//        user.setName(resultSet.getString("name"));
//        user.setPassword(resultSet.getString("password"));
//
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//
//        return user;
//    }
//
//    private static Connection getConnection() {
//        //데이터 어딨어? mysql
//        //mysql 클래스 로딩
//        Class.forName("org.mariadb.jdbc.Driver");
//
//        //Connection 맺고
//        Connection connection = DriverManager.getConnection
//                ("jdbc:mariadb://138.2.32.29:3306/USERINFO", "bellsky", "test1234");
//        return connection;
//    }
//
//    public void insert(User user) throws ClassNotFoundException, SQLException {
//
//        Connection connection = dataSource.getConnection();
//        //쿼리 만들고
//        PreparedStatement preparedStatement = connection.prepareStatement
//                ("insert into userinfo (name, password) values ( ?, ? )"
//                        , Statement.RETURN_GENERATED_KEYS);
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getPassword());
//        //쿼리 실행하고
//        preparedStatement.executeUpdate();
//        ResultSet resultSet = preparedStatement.getGeneratedKeys();
//        resultSet.next();
//        user.setId(resultSet.getLong(1));
//        //자원해지
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            //쿼리 만들고
            preparedStatement = connection.prepareStatement
                    ("insert into userinfo (name, password) values ( ?, ? )"
                            , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            //쿼리 실행하고
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getLong(1));
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}