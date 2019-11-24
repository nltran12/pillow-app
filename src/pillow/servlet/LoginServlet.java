package pillow.servlet;

import pillow.dal.*;
import pillow.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  
  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = UsersDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    req.getRequestDispatcher("/LoginPage.jsp").forward(req, resp);
  }
  
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
      Users user = null;
      String userName = req.getParameter("username");
      String password = req.getParameter("password");
      try {
        user = usersDao.getUserByUserName(userName); 
        
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      /*
      req.setAttribute("user", user);
      req.getRequestDispatcher("/LoginPage.jsp").forward(req, resp);
      */
      
      if (user == null || !user.getPassword().equals(password)) {
        // invalid login
        messages.put("success", "Invalid username or password!");
        req.getRequestDispatcher("/LoginPage.jsp").forward(req, resp);
      } else {
        HttpSession session = req.getSession(true);
        session.setAttribute("currentUser", user);
        resp.sendRedirect("LoggedIn.jsp");
      }
  }
}
