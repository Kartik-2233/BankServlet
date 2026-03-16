package com.ducat;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        String type = req.getParameter("type");
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        
       
        if (type == null || user == null || password == null) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Please fill all fields');");
            out.println("location='login.html';");
            out.println("</script>");
            return;
        }

        if (type.equals("admin")) {
            if (user.equals("Admin") && password.equals("Admin123")) {
                HttpSession session = req.getSession();
                session.setAttribute("username", user);
                res.sendRedirect("admin.html");
            } else {
                
                out.println("<script type='text/javascript'>");
                out.println("alert('Incorrect Admin ID or Password');");
                out.println("location='login.html';");
                out.println("</script>");
            }
        } else if (type.equals("user")) {
            boolean found = false;
            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement("select username from usertable where username=? and password=?")) {
                
                ps.setString(1, user);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    found = true;
                    HttpSession session = req.getSession();
                    session.setAttribute("username", user);
                    res.sendRedirect("dashboard.html");
                }

                if (!found) {

                    out.println("<script type='text/javascript'>");
                    out.println("alert('Invalid Username or Password');");
                    out.println("location='login.html';");
                    out.println("</script>");
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.print("Database Error: " + e.getMessage());
            }
        }
    }
}