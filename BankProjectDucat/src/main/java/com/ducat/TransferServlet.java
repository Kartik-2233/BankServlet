package com.ducat;

import java.io.IOException;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {

  try{

   HttpSession session=req.getSession(false);

   if(session==null){
    resp.sendRedirect("login.html");
    return;
   }

   String username=(String)session.getAttribute("username");

   String receiver=req.getParameter("recipientAccount");

   double amount=Double.parseDouble(req.getParameter("amount"));

   Connection con=DBConnection.getConnection();

   con.setAutoCommit(false);

   PreparedStatement ps1=con.prepareStatement(
   "SELECT balance FROM account WHERE accountnumber=(SELECT accountnumber FROM usertable WHERE username=?)");

   ps1.setString(1,username);

   ResultSet rs=ps1.executeQuery();

   if(!rs.next()){
    resp.getWriter().println("Account not found");
    return;
   }

   double balance=rs.getDouble("balance");

   if(balance<amount){
    resp.getWriter().println("Insufficient balance");
    return;
   }

   PreparedStatement ps2=con.prepareStatement(
   "UPDATE account SET balance=balance-? WHERE accountnumber=(SELECT accountnumber FROM usertable WHERE username=?)");

   ps2.setDouble(1,amount);
   ps2.setString(2,username);

   ps2.executeUpdate();

   PreparedStatement ps3=con.prepareStatement(
   "UPDATE account SET balance=balance+? WHERE accountnumber=?");

   ps3.setDouble(1,amount);
   ps3.setString(2,receiver);

   ps3.executeUpdate();

   con.commit();

   resp.sendRedirect("dashboard.html");

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}