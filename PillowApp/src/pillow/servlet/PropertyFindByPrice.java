package pillow.servlet;

import pillow.dal.*;
import pillow.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findpropertiesbyprice")
public class PropertyFindByPrice extends HttpServlet {

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

    List<Properties> properties = new ArrayList<Properties>();

    String price = req.getParameter("price");
    if (price == null || price.trim().isEmpty()) {
      messages.put("result", "Please enter a valid price.");
    } else {
      // Retrieve properties and store as a message.
      try {
        properties = propertiesDao.getPropertiesByMaxRent(Float.parseFloat(price));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + price);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousPrice", price);
    }
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertyFindByPrice.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Properties> properties = new ArrayList<Properties>();

    String price = req.getParameter("price");
    if (price == null || price.trim().isEmpty()) {
      messages.put("result", "Please enter a valid price.");
    } else {
      // Retrieve properties and store as a message.
      try {
        properties = propertiesDao.getPropertiesByMaxRent(Float.parseFloat(price));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + price);
    }
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertyFindByPrice.jsp").forward(req, resp);
  }
}
