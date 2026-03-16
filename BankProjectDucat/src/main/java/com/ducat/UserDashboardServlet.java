package com.ducat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/userDashboard")
public class UserDashboardServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {

  resp.setContentType("application/json");

  try{

   HttpSession session=req.getSession(false);

   if(session==null){
    resp.setStatus(401);
    return;
   }

   String username=(String)session.getAttribute("username");

   Connection con=DBConnection.getConnection();

   PreparedStatement ps=con.prepareStatement(
   "SELECT fullname,accountnumber FROM usertable WHERE username=?");

   ps.setString(1,username);

   ResultSet rs=ps.executeQuery();

   if(rs.next()){

    String acc=rs.getString("accountnumber");

    PreparedStatement ps2=con.prepareStatement(
    "SELECT balance FROM account WHERE accountnumber=?");

    ps2.setString(1,acc);

    ResultSet rs2=ps2.executeQuery();

    rs2.next();

    double balance=rs2.getDouble("balance");

    resp.getWriter().print("{");
    resp.getWriter().print("\"fullName\":\""+rs.getString("fullname")+"\",");
    resp.getWriter().print("\"accountNumber\":\""+acc+"\",");
    resp.getWriter().print("\"balance\":\""+balance+"\"");
    resp.getWriter().print("}");
   }

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}