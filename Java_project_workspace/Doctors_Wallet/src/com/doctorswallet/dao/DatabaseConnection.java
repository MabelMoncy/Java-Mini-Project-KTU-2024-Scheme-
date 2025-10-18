package com.doctorswallet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database URL, username, password
            String url = "jdbc:mysql://localhost:3306/doctors_wallet";
            String user = "root";                // your MySQL username
            String password = "Mysql2025@cek";   // your MySQL password

            // Establish connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Test the connection
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed!");
        }
    }
}
