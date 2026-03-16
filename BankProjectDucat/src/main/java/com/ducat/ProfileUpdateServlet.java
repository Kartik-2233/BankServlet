package com.ducat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//underdevelopment
public class ProfileUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fullname = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phone");

        HttpSession session = req.getSession(false);

        if(session == null || session.getAttribute("username") == null){
            resp.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE usertable SET fullname=?, email=?, phone=? WHERE username=?")) {

            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phoneNumber);
            ps.setString(4, username);

            ps.executeUpdate();

            resp.sendRedirect("profile.html");

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("Error: " + e.getMessage());
        }
    }
}