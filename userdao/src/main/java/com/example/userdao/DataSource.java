package com.example.userdao;


import java.sql.*;


public interface DataSource {

    public Connection getConnection() throws ClassNotFoundException, SQLException ;

}