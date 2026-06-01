package com.LoginWeb;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddLibrarian
 */
@WebServlet("/AddLibrarian")
public class AddLibrarian extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLibrarian() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Librarian Registration</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		try {
			// Get parameters from form
			String name = request.getParameter("name");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String mobileStr = request.getParameter("mobile");
			
			// Debug: Print received parameters
			System.out.println("================================");
			System.out.println("📝 Librarian Registration Request Received");
			System.out.println("Name: " + name);
			System.out.println("Username: " + username);
			System.out.println("Email: " + email);
			System.out.println("Mobile: " + mobileStr);
			System.out.println("================================");
			
			// Validate inputs
			if(name == null || name.trim().isEmpty()){
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Name is required</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			if(username == null || username.trim().isEmpty()){
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Username is required</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			if(email == null || email.trim().isEmpty()){
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Email is required</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			if(password == null || password.trim().isEmpty()){
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Password is required</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			if(mobileStr == null || mobileStr.trim().isEmpty()){
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Mobile number is required</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			// Convert mobile to long
			long mobile = 0;
			try {
				mobile = Long.parseLong(mobileStr);
				if(mobileStr.length() != 10) {
					out.println("<div class='container'>");
					out.println("<h3><center><font color='red'>❌ Mobile number must be 10 digits</font></center></h3>");
					out.println("</div>");
					request.getRequestDispatcher("footer.html").include(request, response);
					out.close();
					return;
				}
			} catch (NumberFormatException e) {
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Mobile number must be numeric</font></center></h3>");
				out.println("</div>");
				request.getRequestDispatcher("footer.html").include(request, response);
				out.close();
				return;
			}
			
			System.out.println("✅ Registering librarian - Name: " + name + ", Email: " + email + ", Username: " + username);
			
			// Create LibrarianBean and save
			LibrarianBean bean = new LibrarianBean(name, email, password, mobile);
			bean.setEmail(email);
			int status = LibrarianDao.save(bean, username);
			
			if(status > 0){
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container'>");
				out.println("<h3><center><font color='green'>✅ Librarian Registered Successfully!</font></center></h3>");
				out.println("<h4><center>You can now <a href='index.html'>login here</a></center></h4>");
				out.println("</div>");
			} else {
				request.getRequestDispatcher("index.html").include(request, response);
				out.println("<div class='container'>");
				out.println("<h3><center><font color='red'>❌ Registration Failed - Please try again later</font></center></h3>");
				out.println("</div>");
			}
			
		} catch (Exception e) {
			System.out.println("❌ Error during registration: " + e);
			e.printStackTrace(System.out);
			out.println("<div class='container'>");
			out.println("<h3><center><font color='red'>❌ An error occurred during registration</font></center></h3>");
			out.println("<p>" + e.getMessage() + "</p>");
			out.println("</div>");
		} finally {
			request.getRequestDispatcher("footer.html").include(request, response);
			out.close();
		}
	}

}
