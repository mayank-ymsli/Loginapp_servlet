package com.logout;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Logout")
public class Logoutservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		req.getRequestDispatcher("link.jsp").include(req, resp);
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		
		Cookie ck = new Cookie("name","");
		ck.setMaxAge(0);
		resp.addCookie(ck);
		writer.write("you have succesfully logged out !!");
		req.getRequestDispatcher("Login.jsp").include(req, resp);
	}
	
}
