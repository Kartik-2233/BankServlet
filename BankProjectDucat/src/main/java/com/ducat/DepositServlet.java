package com.ducat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {

			HttpSession session = req.getSession(false);

			if (session == null) {
				resp.sendRedirect("login.html");
				return;
			}

			String username = (String) session.getAttribute("username");

			double amount = Double.parseDouble(req.getParameter("amount"));

			Connection con = DBConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement ps = con.prepareStatement(
					"UPDATE account SET balance=balance+? WHERE accountnumber=(SELECT accountnumber FROM usertable WHERE username=?)");

			ps.setDouble(1, amount);
			ps.setString(2, username);

			ps.executeUpdate();

			PreparedStatement log = con
					.prepareStatement("INSERT INTO transactions(description,amount,type) VALUES(?,?,?)");

			log.setString(1, "Deposit");
			log.setDouble(2, amount);
			log.setString(3, "CREDIT");

			log.executeUpdate();

			con.commit();
//RequestDisptacher rd = req.getRequestDispatcher("dashboard.html");
// rd.forward(req,resp);
			resp.sendRedirect("dashboard.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}