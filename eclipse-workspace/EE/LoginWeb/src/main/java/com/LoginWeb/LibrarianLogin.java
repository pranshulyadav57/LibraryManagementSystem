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
 * LibrarianLogin Servlet
 * Handles librarian authentication and session management
 */
@WebServlet("/LibrarianLogin")
public class LibrarianLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// Get form parameters - expecting 'uname' and 'pwd'
			String uname = request.getParameter("uname");
			String pwd = request.getParameter("pwd");

			// Debug: Print received parameters
			System.out.println("================================");
			System.out.println("📝 Librarian Login Request Received");
			System.out.println("Username: " + (uname != null ? uname : "null"));
			System.out.println("Password: " + (pwd != null && !pwd.isEmpty() ? "***" : "null"));
			System.out.println("================================");

			// Server-side validation
			if (uname == null || uname.trim().isEmpty()) {
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Username is required</font></h3>");
				out.println("</div>");
				return;
			}

			if (pwd == null || pwd.trim().isEmpty()) {
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Password is required</font></h3>");
				out.println("</div>");
				return;
			}

			// Authenticate librarian using LibrarianDao
			if (LibrarianDao.authenticate(uname.trim(), pwd)) {
				
				// Create session
				HttpSession session = request.getSession();
				session.setAttribute("librarianEmail", uname.trim());
				session.setAttribute("librarianUsername", uname.trim());
				session.setAttribute("userType", "librarian");
				session.setMaxInactiveInterval(30 * 60); // 30 minutes timeout
				
				System.out.println("✅ Librarian login successful!");
				System.out.println("Librarian Username: " + uname);
				
				// Redirect to librarian dashboard
				response.sendRedirect("librarianhome.html");
				
			} else {
				// Authentication failed
				System.out.println("❌ Librarian login failed - Invalid credentials");
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container' style='margin-top: 20px;'>");
				out.println("<h3><font color='red'>❌ Invalid username or password</font></h3>");
				out.println("</div>");
			}

		} catch (Exception e) {
			System.out.println("❌ Error during librarian login: " + e.getMessage());
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

