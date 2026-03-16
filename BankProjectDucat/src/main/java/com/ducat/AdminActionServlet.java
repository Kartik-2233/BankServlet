package com.ducat;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/adminAction")
public class AdminActionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String account = req.getParameter("accountNumber");
		double amount = Double.parseDouble(req.getParameter("amount"));
		String action = req.getParameter("actionType");

		try (Connection con = DBConnection.getConnection()) {

			if (action.equals("addMoney")) {

				PreparedStatement ps = con
						.prepareStatement("UPDATE account SET balance=balance+? WHERE accountnumber=?");

				ps.setDouble(1, amount);
				ps.setString(2, account);

				ps.executeUpdate();

			} else if (action.equals("deductMoney")) {

				PreparedStatement ps = con
						.prepareStatement("UPDATE account SET balance=balance-? WHERE accountnumber=?");

				ps.setDouble(1, amount);
				ps.setString(2, account);

				ps.executeUpdate();
			}
			// RequestDispatcher rd = req.getRequestDispatcher("admin.html");
			// rd.forward(req, resp);
			resp.sendRedirect("admin.html");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}