package com.ducat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactusServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String fullname = req.getParameter("contactName");
        String email = req.getParameter("contactEmail");
        String phone = req.getParameter("contactPhone");
        String subject = req.getParameter("contactSubject");
        String message = req.getParameter("contactMessage");

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO feedback (fullname, email, phone, subject, message) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, fullname);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, subject);
                ps.setString(5, message);

                int rowCount = ps.executeUpdate();
                if (rowCount > 0) {
                   
                    resp.sendRedirect("contact.html?status=success");
                } else {
                    out.println("<h3>Sorry, something went wrong. Please try again.</h3>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            out.println("<h3>Database Error: " + e.getMessage() + "</h3>");
        }
    }
}