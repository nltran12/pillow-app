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


@WebServlet("/findpropertiesmulti")
public class PropertyFindByMulti extends HttpServlet {

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
    req.getRequestDispatcher("/PropertyFindByMulti.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // List of results
    List<Properties> properties = new ArrayList<Properties>();

    // Parameters
    String neighborhood = req.getParameter("neighborhood");
    float rating = Float.parseFloat(req.getParameter("rating"));
    
    if (neighborhood == null || neighborhood.trim().isEmpty()) {
      messages.put("result", "Please enter a valid neighborhood.");
    } else {
      // Retrieve properties and store as a message.
      try {
        properties = propertiesDao.getPropertiesByNeighborhoodAndMinRating(neighborhood, rating);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + neighborhood + ", " + rating);
    }
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertyFindByMulti.jsp").forward(req, resp);
  }
}
