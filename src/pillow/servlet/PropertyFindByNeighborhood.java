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


@WebServlet("/findpropertiesbyneighborhood")
public class PropertyFindByNeighborhood extends HttpServlet {

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

    String neighborhood = req.getParameter("neighborhood");
    if (neighborhood == null || neighborhood.trim().isEmpty()) {
      messages.put("result", "Please enter a valid neighborhood.");
    } else {
      // Retrieve properties and store as a message.
      try {
        properties = propertiesDao.getPropertiesByNeighborhood(neighborhood);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + neighborhood);
      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousFirstName", neighborhood);
    }
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertyFindByNeighborhood.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Properties> properties = new ArrayList<Properties>();

    String neighborhood = req.getParameter("neighborhood");
    if (neighborhood == null || neighborhood.trim().isEmpty()) {
      messages.put("result", "Please enter a valid neighborhood.");
    } else {
      // Retrieve properties and store as a message.
      try {
        properties = propertiesDao.getPropertiesByNeighborhood(neighborhood);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + neighborhood);
    }
    req.setAttribute("properties", properties);

    req.getRequestDispatcher("/PropertyFindByNeighborhood.jsp").forward(req, resp);
  }
}
