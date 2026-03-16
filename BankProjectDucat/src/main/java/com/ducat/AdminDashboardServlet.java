package com.ducat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("application/json");

		try (Connection con = DBConnection.getConnection()) {

			Statement st = con.createStatement();

			ResultSet rs1 = st.executeQuery("SELECT COUNT(*) FROM usertable");
			rs1.next();
			int users = rs1.getInt(1);

			ResultSet rs2 = st.executeQuery("SELECT COUNT(*) FROM transactions");
			rs2.next();
			int transactions = rs2.getInt(1);

			ResultSet rs3 = st.executeQuery("SELECT SUM(balance) FROM account");
			rs3.next();
			double balance = rs3.getDouble(1);

			ResultSet rs4 = st.executeQuery("SELECT COUNT(*) FROM account WHERE balance>=0");
			rs4.next();
			int active = rs4.getInt(1);

			PrintWriter out = resp.getWriter();

			out.print("{");
			out.print("\"totalUsers\":" + users + ",");
			out.print("\"totalTransactions\":" + transactions + ",");
			out.print("\"totalBalance\":" + balance + ",");
			out.print("\"activeAccounts\":" + active + ",");
			out.print("\"recentTransactions\":[]");
			out.print("}");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}