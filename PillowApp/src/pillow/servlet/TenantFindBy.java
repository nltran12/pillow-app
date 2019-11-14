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

@WebServlet("/findtenantsby")
public class TenantFindBy extends HttpServlet {
  
  protected TenantsDao tenantsDao;

  @Override
  public void init() throws ServletException {
    tenantsDao = TenantsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    req.getRequestDispatcher("/TenantsFindBy.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Tenants> tenants = new ArrayList<Tenants>();

    int creditScore = -1;
    boolean bgCheck = true;
    int income = -1;
    try {
      creditScore = Integer.parseInt(req.getParameter("creditscore"));
      bgCheck = Boolean.parseBoolean(req.getParameter("bgcheck"));
      income = Integer.parseInt(req.getParameter("income"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    
    if (creditScore == -1) {
      creditScore = 0;
    }
    
    if (income < 0) {
      income = 0;
    }
    
    // Retrieve properties and store as a message.
    try {
      tenants = tenantsDao.getTenantsWithRestraints(creditScore, bgCheck, income);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    messages.put("result", "Displaying results ");
    
    req.setAttribute("tenants", tenants);

    req.getRequestDispatcher("/TenantsFindBy.jsp").forward(req, resp);
  }
}
