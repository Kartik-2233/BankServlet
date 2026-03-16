package com.ducat;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

 protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws IOException {

  HttpSession session=req.getSession(false);

  if(session!=null){
   session.invalidate();
  }

  resp.sendRedirect("login.html");
 }
}