package com.LoginWeb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.LoginWeb.LibrarianBean;

/**
 * LibrarianDao - Data Access Object for Librarian operations
 */
public class LibrarianDao {

	/**
	 * Save a new librarian with username
	 */
	public static int save(LibrarianBean bean, String username){
		int status=0;
		try{
			Connection con=DB.getCon();
			if(con == null){
				System.out.println("❌ Database connection failed");
				return 0;
			}
			PreparedStatement ps=con.prepareStatement("INSERT INTO Librarian(Name, email, pwd, mob, username) VALUES(?,?,?,?,?)");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			ps.setLong(4, bean.getMobile());
			ps.setString(5, username);
			
			System.out.println("🔍 Executing INSERT query for librarian: " + bean.getEmail());
			
			status=ps.executeUpdate();
			if(status > 0){
				System.out.println("✅ Librarian registered successfully! Username: " + username);
			}else{
				System.out.println("❌ Registration failed");
			}
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Registration error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}
	
	/**
	 * Save a librarian (legacy without username)
	 */
	public static int save(LibrarianBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			if(con == null){
				System.out.println("❌ Database connection failed");
				return 0;
			}
			PreparedStatement ps=con.prepareStatement("INSERT INTO Librarian(Name, email, pwd, mob) VALUES(?,?,?,?)");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			ps.setLong(4, bean.getMobile());
			status=ps.executeUpdate();
			if(status > 0){
				System.out.println("✅ Librarian registered successfully!");
			}else{
				System.out.println("❌ Registration failed");
			}
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Registration error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static int update(LibrarianBean bean){
		int status=0;
		try{
			Connection con=DB.getCon();
			if(con == null) return 0;
			PreparedStatement ps=con.prepareStatement("UPDATE Librarian SET Name=?, email=?, pwd=?, mob=? WHERE Empid=?");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			ps.setLong(4, bean.getMobile());
			ps.setInt(5, bean.getId());
			status=ps.executeUpdate();
			if(status > 0){
				System.out.println("✅ Librarian updated successfully!");
			}
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Update error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static List<LibrarianBean> view(){
		List<LibrarianBean> list=new ArrayList<LibrarianBean>();
		try{
			Connection con=DB.getCon();
			if(con == null) return list;
			PreparedStatement ps=con.prepareStatement("SELECT * FROM Librarian");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibrarianBean bean=new LibrarianBean();
				bean.setId(rs.getInt("Empid"));
				bean.setName(rs.getString("Name"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("pwd"));
				bean.setMobile(rs.getLong("mob"));
				list.add(bean);
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Error retrieving librarian list: " + e.getMessage());
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static LibrarianBean viewById(int id){
		LibrarianBean bean=new LibrarianBean();
		try{
			Connection con=DB.getCon();
			if(con == null) return bean;
			PreparedStatement ps=con.prepareStatement("SELECT * FROM Librarian WHERE Empid=?");
			ps.setInt(1,id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				bean.setId(rs.getInt("Empid"));
				bean.setName(rs.getString("Name"));
				bean.setPassword(rs.getString("pwd"));
				bean.setEmail(rs.getString("email"));
				bean.setMobile(rs.getLong("mob"));
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Error retrieving librarian: " + e.getMessage());
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public static int delete(int id){
		int status=0;
		try{
			Connection con=DB.getCon();
			if(con == null) return 0;
			PreparedStatement ps=con.prepareStatement("DELETE FROM Librarian WHERE Empid=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			if(status > 0){
				System.out.println("✅ Librarian deleted successfully!");
			}
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Delete error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return status;
	}

	/**
	 * Authenticate librarian by username and password
	 */
	public static boolean authenticate(String username, String pwd){
		boolean status=false;
		try{
			Connection con=DB.getCon();
			if(con == null){
				System.out.println("❌ Database connection failed");
				return false;
			}
			
			// Query by username
			String query = "SELECT * FROM Librarian WHERE username=? AND pwd=?";
			System.out.println("🔍 Authenticating librarian: " + username);
			
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				status=true;
				System.out.println("✅ Librarian authenticated successfully! Username: " + username);
			}else{
				System.out.println("❌ Authentication failed - Invalid username or password");
			}
			rs.close();
			ps.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("❌ Authentication error: " + e.getMessage());
			e.printStackTrace();
		}
		return status;
	}
}
