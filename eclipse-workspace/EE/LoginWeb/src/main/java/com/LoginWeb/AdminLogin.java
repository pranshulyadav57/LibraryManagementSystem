package com.LoginWeb;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * AdminLogin Servlet
 * Handles admin authentication and session management
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// Get form parameters
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Debug: Print received parameters
			System.out.println("================================");
			System.out.println("📝 Admin Login Request Received");
			System.out.println("Email: " + (email != null ? email : "null"));
			System.out.println("Password: " + (password != null && !password.isEmpty() ? "***" : "null"));
			System.out.println("================================");

			// Server-side validation
			if (email == null || email.trim().isEmpty()) {
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Email is required</font></h3>");
				out.println("</div>");
				return;
			}

			if (password == null || password.trim().isEmpty()) {
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Password is required</font></h3>");
				out.println("</div>");
				return;
			}

			// Authenticate admin using AdminDao
			if (AdminDao.authenticate(email.trim(), password)) {
				// Get admin details
				AdminBean admin = AdminDao.getAdminByEmail(email.trim());
				
				// Create session
				HttpSession session = request.getSession();
				session.setAttribute("adminEmail", email.trim());
				session.setAttribute("adminName", admin != null ? admin.getName() : "Admin");
				session.setAttribute("userType", "admin");
				session.setMaxInactiveInterval(30 * 60); // 30 minutes timeout
				
				System.out.println("✅ Admin login successful!");
				System.out.println("Admin Email: " + email);
				
				// Redirect to admin dashboard
				response.sendRedirect("navadmin.html");
				
			} else {
				// Authentication failed
				System.out.println("❌ Admin login failed - Invalid credentials");
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Invalid email or password</font></h3>");
				out.println("</div>");
			}

		} catch (Exception e) {
			System.out.println("❌ Error during admin login: " + e.getMessage());
			e.printStackTrace(System.out);
			
			request.getRequestDispatcher("index.html").include(request, response);
			out.println("<div class='container' style='margin-top: 20px;'>");
			out.println("<h3><font color='red'>❌ An error occurred during login</font></h3>");
			out.println("<p>Error: " + e.getMessage() + "</p>");
			out.println("</div>");
		} finally {
			out.close();
		}
	}
}

