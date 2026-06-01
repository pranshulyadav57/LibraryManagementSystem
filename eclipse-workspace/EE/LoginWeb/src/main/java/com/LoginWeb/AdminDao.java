package com.LoginWeb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * AdminDao - Data Access Object for Admin authentication
 * Handles admin login and authentication logic
 */
public class AdminDao {
	
	/**
	 * Authenticate admin with email and password
	 * @param email - Admin email
	 * @param password - Admin password (plain text for now)
	 * @return true if authentication successful, false otherwise
	 */
	public static boolean authenticate(String email, String password) {
		boolean status = false;
		
		if (email == null || email.trim().isEmpty() || 
			password == null || password.trim().isEmpty()) {
			System.out.println("❌ Email or password is empty");
			return false;
		}
		
		try {
			Connection con = DB.getCon();
			if (con == null) {
				System.out.println("❌ Database connection failed");
				return false;
			}
			
			// Query the Admin table
			String query = "SELECT * FROM Admin WHERE email = ? AND password = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			System.out.println("🔍 Authenticating admin with email: " + email);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				status = true;
				System.out.println("✅ Admin authenticated successfully! Email: " + email);
			} else {
				System.out.println("❌ Admin authentication failed - Invalid email or password");
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("❌ Authentication error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * Get admin details by email
	 * @param email - Admin email
	 * @return Admin details or null if not found
	 */
	public static AdminBean getAdminByEmail(String email) {
		AdminBean admin = null;
		
		try {
			Connection con = DB.getCon();
			if (con == null) {
				System.out.println("❌ Database connection failed");
				return null;
			}
			
			String query = "SELECT * FROM Admin WHERE email = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				admin = new AdminBean();
				admin.setId(rs.getInt("id"));
				admin.setEmail(rs.getString("email"));
				admin.setName(rs.getString("name"));
				System.out.println("✅ Admin details found for: " + email);
			}
			
			rs.close();
			ps.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println("❌ Error fetching admin details: " + e.getMessage());
			e.printStackTrace();
		}
		
		return admin;
	}
}
