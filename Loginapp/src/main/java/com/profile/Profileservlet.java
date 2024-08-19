package com.profile;

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

@WebServlet("/Profile")
public class Profileservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter OUT = resp.getWriter();
        resp.setContentType("text/html");

        req.getRequestDispatcher("link.jsp").include(req, resp);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String empid = null;
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    empid = cookie.getValue();
                    break;
                }
            }

            if (empid != null && !empid.isEmpty()) {
            	System.out.println(empid);
            	try {
                	
                    Class.forName("com.mysql.cj.jdbc.Driver");
        			String url = "jdbc:mysql://localhost:3306/mayank";
        			String user = "root";
        			String pas = "12345";
        			Connection con = DriverManager.getConnection(url,user,pas);
        			System.out.println("DB CONNECTED !!");
        			String query = "SELECT * from employee2 where empid=? ";
        			PreparedStatement stmt = con.prepareStatement(query);
        			stmt.setString(1, empid);
        			ResultSet set = stmt.executeQuery();
        			if (set.next()) { 
        		        OUT.write("Welcome to profile<br>");
        		        System.out.println(set.getString(1));
        		        OUT.write("Mr " + set.getString(1));
        		        OUT.write("Your secret message is " + set.getString(3));
        		    } else {
        		        OUT.write("No records found for empid: " + empid);
        		    }
                }catch(Exception e ) {
                	System.out.println(e.getMessage());
                }
            } else {
                OUT.write("LOGIN FIRST!");
                req.getRequestDispatcher("Login.jsp").include(req, resp);
            }
        } else {
            OUT.write("LOGIN FIRST!");
            req.getRequestDispatcher("Login.jsp").include(req, resp);
        }

        OUT.close();
    }
}
