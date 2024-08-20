package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		req.getRequestDispatcher("link.jsp").include(req, resp);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String mssg = req.getParameter("secret");
		String empid = req.getParameter("empid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mayank";
			String user = "root";
			String pas = "12345";
			Connection con = DriverManager.getConnection(url,user,pas);
			System.out.println("DB CONNECTED !!");
			String query="Insert into employee2 (username,password,mssg,empid) values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, mssg);
			stmt.setString(4, empid);
			int result=stmt.executeUpdate();
			System.out.println(result);
			if(result>=1) {
				out.write("<h1>you are successfully registered</h1>");
				req.getRequestDispatcher("Login.jsp").include(req, resp);
			}
			else {
				
				out.write("sorry try again LATER");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
