package com.LoginWeb;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LogoutLibrarian - Handles librarian logout
 */
@WebServlet("/LogoutLibrarian")
public class LogoutLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("✅ Librarian logout - Removing session");
			session.removeAttribute("librarianEmail");
			session.removeAttribute("librarianUsername");
			session.removeAttribute("userType");
			session.invalidate();
		}
		
		System.out.println("✅ Librarian logged out successfully");
		response.sendRedirect("index.html");
	}
}
