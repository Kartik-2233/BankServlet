package com.ducat;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

  String fullname = req.getParameter("fullName");
  String email = req.getParameter("email");
  String phone = req.getParameter("phone");
  String username = req.getParameter("username");
  String password = req.getParameter("password");
  String accountType = req.getParameter("accountType");

  Connection con = null;

  try {

   con = DBConnection.getConnection();
   con.setAutoCommit(false);

   /* -----------------------------
      1️⃣ Check duplicate username
   ----------------------------- */
   PreparedStatement checkUser = con.prepareStatement(
   "SELECT username FROM usertable WHERE username=?");

   checkUser.setString(1, username);

   ResultSet rsCheck = checkUser.executeQuery();

   if (rsCheck.next()) {

    resp.getWriter().println("Username already exists");
    return;

   }

   /* -----------------------------
      2️⃣ Create account row
   ----------------------------- */

   PreparedStatement psAccount = con.prepareStatement(
   "INSERT INTO account(balance) VALUES(0)",
   Statement.RETURN_GENERATED_KEYS);

   psAccount.executeUpdate();

   ResultSet rs = psAccount.getGeneratedKeys();

   int id = 0;

   if (rs.next()) {
    id = rs.getInt(1);
   }

   /* -----------------------------
      3️⃣ Generate account number
   ----------------------------- */

   String accountNumber = "SBI" + String.format("%09d", id);

   /* -----------------------------
      4️⃣ Update account table
   ----------------------------- */

   PreparedStatement updateAcc = con.prepareStatement(
   "UPDATE account SET accountnumber=? WHERE id=?");

   updateAcc.setString(1, accountNumber);
   updateAcc.setInt(2, id);

   updateAcc.executeUpdate();

   /* -----------------------------
      5️⃣ Insert user
   ----------------------------- */

   PreparedStatement psUser = con.prepareStatement(
   "INSERT INTO usertable(fullname,email,phone,username,password,accountType,accountnumber) VALUES(?,?,?,?,?,?,?)");

   psUser.setString(1, fullname);
   psUser.setString(2, email);
   psUser.setString(3, phone);
   psUser.setString(4, username);
   psUser.setString(5, password);
   psUser.setString(6, accountType);
   psUser.setString(7, accountNumber);

   psUser.executeUpdate();

   /* -----------------------------
      6️⃣ Commit transaction
   ----------------------------- */

   con.commit();

   resp.sendRedirect("login.html");

  } catch (Exception e) {

   try {

    if (con != null) {
     con.rollback();
    }

   } catch (Exception ex) {
    ex.printStackTrace();
   }

   e.printStackTrace();

  } finally {

   try {

    if (con != null) {
     con.setAutoCommit(true);
     con.close();
    }

   } catch (Exception e) {
    e.printStackTrace();
   }

  }

 }
}