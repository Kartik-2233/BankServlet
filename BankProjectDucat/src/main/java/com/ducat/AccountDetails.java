package com.ducat;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/profile")
public class AccountDetails extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {

			HttpSession session = req.getSession(false);

			if (session == null || session.getAttribute("username") == null) {
				resp.sendRedirect("login.html");
				return;
			}

			String username = (String) session.getAttribute("username");
			resp.getWriter().println(username);
			String name = req.getParameter("fullName");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");

			Connection con = DBConnection.getConnection();

			String query = "UPDATE usertable SET fullname=?,email=?,phone=? WHERE username=?";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, username);

			ps.executeUpdate();

			// RequestDispatcher rd = req.getRequestDispatcher("profile.html");
			// rd.forward(req, resp);
			resp.sendRedirect("profile.html");
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}