package com.ducat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/GetProfile")
public class GetProfileServlet extends HttpServlet {

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
   "SELECT accountnumber,fullname,email,phone,accountType FROM usertable WHERE username=?");

   ps.setString(1,username);

   ResultSet rs=ps.executeQuery();

   PrintWriter out=resp.getWriter();

   if(rs.next()){

    out.print("{");
    out.print("\"accountNumber\":\""+rs.getString("accountnumber")+"\",");
    out.print("\"fullName\":\""+rs.getString("fullname")+"\",");
    out.print("\"email\":\""+rs.getString("email")+"\",");
    out.print("\"phone\":\""+rs.getString("phone")+"\",");
    out.print("\"accountType\":\""+rs.getString("accountType")+"\"");
    out.print("}");
   }

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}