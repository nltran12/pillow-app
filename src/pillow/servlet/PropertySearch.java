package pillow.servlet;

import pillow.dal.*;
import pillow.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/propertysearch")
public class PropertySearch extends HttpServlet {

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
    // Just render the JSP.   
    req.getRequestDispatcher("/PropertySearch.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    req.setAttribute("city", req.getParameter("city"));
    req.setAttribute("neighborhood", req.getParameter("neighborhood"));
    req.setAttribute("price", req.getParameter("price"));
    req.setAttribute("rating", req.getParameter("rating"));
    req.setAttribute("bedrooms", req.getParameter("bedrooms"));


    // List of results
    List<Properties> properties = new ArrayList<Properties>();
    
    // Parameters
    String city = req.getParameter("city") == null ? "%" : req.getParameter("city").split(",")[0];
    String neighborhood = validateNeighborhood(req.getParameter("neighborhood"));
    float price = validatePrice(req.getParameter("price"));
    int rating = req.getParameter("rating") == null? 0 : Integer.parseInt(req.getParameter("rating").substring(0, 1));
    int bedrooms = req.getParameter("bedrooms") == null ? 0 : Integer.parseInt(req.getParameter("bedrooms").substring(0, 1));
    
    // Retrieve properties and store as a message.
    try {
      properties = propertiesDao.getProperties(city, neighborhood, price, rating, bedrooms);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }
    messages.put("result", "Displaying results for " + neighborhood + ", " + price);
    
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertySearch.jsp").forward(req, resp);
  }
  
  private String validateNeighborhood(String param) {
    if (param == null || param.equals("")) {
      return "%";
    }
    return param;
  }
  
  private float validatePrice(String param) {
    if (param == null || param.equals("")) {
      return 1000000f;
    }
    return Float.parseFloat(param);
  }
  
}
