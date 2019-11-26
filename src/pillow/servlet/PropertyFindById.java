package pillow.servlet;

import pillow.dal.*;
import pillow.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findproperty")
public class PropertyFindById extends HttpServlet {

  protected PropertiesDao propertiesDao;

  @Override
  public void init() throws ServletException {
    propertiesDao = PropertiesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    
    Properties property = null;
    
    // Retrieve and validate name.
    String propertyId = req.getParameter("propertyid");
    if (propertyId == null || propertyId.trim().isEmpty()) {
      messages.put("result", "Invalid PropertyId");
      messages.put("disableSubmit", "true");
    } else {
      // Retrieve property and store as a message.
      try {
        property = propertiesDao.getPropertiesById(Integer.parseInt(propertyId));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + propertyId);
    }
    req.setAttribute("property", property);
    req.getRequestDispatcher("/PropertyFindById.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    
    Properties property = null;
    
    // Retrieve and validate name.
    String propertyId = req.getParameter("propertyid");
    if (propertyId == null || propertyId.trim().isEmpty()) {
      messages.put("result", "Invalid PropertyId");
      messages.put("disableSubmit", "true");
    } else {
      // Retrieve property and store as a message.
      try {
        property = propertiesDao.getPropertiesById(Integer.parseInt(propertyId));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + propertyId);
    }
    req.setAttribute("property", property);
    req.getRequestDispatcher("/PropertyFindById.jsp").forward(req, resp);
  }
}


