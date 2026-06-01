package com.LoginWeb;

import java.sql.*;

public class Database {

    public boolean check(String uname, String pwd) {
        String sql = "SELECT * FROM Login WHERE uname = ? AND pwd = ?";
        String url = "jdbc:mysql://localhost:3306/Servlet"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "12345"; // Replace with your database password

        try (
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = con.prepareStatement(sql)
        ) {
            // Load MySQL JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Set query parameters
            stmt.setString(1, uname);
            stmt.setString(2, pwd);

            // Execute query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        return false;
    }
}
