package com.ducat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/GetTransactions")
public class GetTransactionsServlet extends HttpServlet {

 protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {

  resp.setContentType("application/json");

  try(Connection con=DBConnection.getConnection()){

   PreparedStatement ps=con.prepareStatement(
   "SELECT * FROM transactions ORDER BY id DESC");

   ResultSet rs=ps.executeQuery();

   PrintWriter out=resp.getWriter();

   out.print("[");

   boolean first=true;

   while(rs.next()){

    if(!first) out.print(",");
    first=false;

    out.print("{");
    out.print("\"dateTime\":\""+rs.getString("created_at")+"\",");
    out.print("\"description\":\""+rs.getString("description")+"\",");
    out.print("\"type\":\""+rs.getString("type")+"\",");
    out.print("\"amount\":\""+rs.getDouble("amount")+"\",");
    out.print("\"balanceAfter\":\"0\",");
    out.print("\"status\":\"Completed\"");
    out.print("}");
   }

   out.print("]");

  }catch(Exception e){
   e.printStackTrace();
  }
 }
}