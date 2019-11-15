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


@WebServlet("/landlorddelete")
public class LandlordDelete extends HttpServlet {
  
  protected LandlordsDao landlordsDao;
  
  @Override
  public void init() throws ServletException {
    landlordsDao = LandlordsDao.getInstance();
  }
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Landlord");        
        req.getRequestDispatcher("/LandlordDelete.jsp").forward(req, resp);
  }
  
  @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String landlordUserName = req.getParameter("username");
        if (landlordUserName == null || landlordUserName.trim().isEmpty()) {
            messages.put("title", "Invalid landlord");
            messages.put("disableSubmit", "true");
        } else {
          // Delete the Tenant.
          try {
            Landlords landlord = landlordsDao.getLandlordsFromUserName(landlordUserName);
            landlord = landlordsDao.delete(landlord);
            // Update the message.
            if (landlord == null) {
                messages.put("title", "Successfully deleted " + landlordUserName);
                messages.put("disableSubmit", "true");
            } else {
              messages.put("title", "Failed to delete " + landlordUserName);
              messages.put("disableSubmit", "false");
            }
          } catch (SQLException e) {
              e.printStackTrace();
              throw new IOException(e);
          }
        }
        req.getRequestDispatcher("/LandlordDelete.jsp").forward(req, resp);
    }
}
