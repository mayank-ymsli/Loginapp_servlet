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
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String mssg = req.getParameter("secret");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mayank";
			String username = "root";
			String pas = "12345";
			Connection con = DriverManager.getConnection(url,username,pas);
			System.out.println("DB CONNECTED !!");
			String query="Insert into employee2 (username,password,mssg) values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, mssg);
			stmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
