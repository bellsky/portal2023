package com.example.userdao;


import java.sql.*;


public class HallaConnectionMaker implements ConnectionMaker {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //데이터 어딨어? mysql
        //mysql 클래스 로딩
        Class.forName("org.mariadb.jdbc.Driver");

        //Connection 맺고
        return DriverManager.getConnection
                ("jdbc:mariadb://138.2.32.29:3306/USERINFO", "bellsky", "test1234");
    }
}