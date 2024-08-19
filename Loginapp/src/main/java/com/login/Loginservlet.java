package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/loginservlet")
public class Loginservlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		
		
		req.getRequestDispatcher("link.jsp").include(req, resp);
		
		
		String empid = req.getParameter("empid");
		String password = req.getParameter("password");
		
//		if(password.equals("admin")) {
//			writer.write("<h1>welcome back !! " + name + "</h1>");
//			Cookie ck = new Cookie("name",name);
//			resp.addCookie(ck);
//		}
//		else {
//			writer.write("invalid username or password");
//			req.getRequestDispatcher("Login.jsp").include(req, resp);
//		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mayank";
			String user = "root";
			String pas = "12345";
			Connection con = DriverManager.getConnection(url,user,pas);
			
			System.out.println("DB CONNECTED !!");
			String query = "SELECT * FROM employee2 WHERE empid =?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, empid);
			ResultSet set = stmt.executeQuery();
			if(!set.next()) {
				writer.print("no employee with this empid exist try again");
				req.getRequestDispatcher("Login.jsp").include(req, resp);
			}
			else {
				if(set.getString(2).equals(password)) {
					String name = set.getString(1);
					writer.write("<h1>welcome back !! " + name + "</h1>");
					Cookie ck = new Cookie("name",empid);
					resp.addCookie(ck);
				}
				else {
					writer.print("invalid username or password try again");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
}
