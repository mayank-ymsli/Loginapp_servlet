package com.profile;

import java.io.IOException;
import java.io.PrintWriter;

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
            String name = null;
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    name = cookie.getValue();
                    break;
                }
            }

            if (name != null && !name.isEmpty()) {
                OUT.write("Welcome to profile<br>");
                OUT.write("Mr " + name);
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
