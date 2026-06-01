package com.LoginWeb;

import java.sql.*;

public class DB {
	// Database configuration
	private static final String DB_URL = "jdbc:mysql://localhost:3306/Library";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "12345";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static Connection getCon(){
		Connection con = null;
		try{
			Class.forName(DB_DRIVER);
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("✅ Database Connected Successfully!");
			
		}catch(ClassNotFoundException e){
			System.out.println("❌ JDBC Driver not found: " + e.getMessage());
			e.printStackTrace();
		}catch(SQLException e){
			System.out.println("❌ Database Connection Failed: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("❌ Unexpected error: " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
}
