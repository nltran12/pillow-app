package pillow.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pillow.dal.LandlordsDao;
import pillow.dal.PropertiesDao;
import pillow.dal.PropertiesReviewsDao;
import pillow.dal.TenantReviewsDao;
import pillow.dal.TenantsDao;
import pillow.model.Landlords;
import pillow.model.Properties;
import pillow.model.PropertyReviews;
import pillow.model.TenantReviews;
import pillow.model.Tenants;

@WebServlet("/tenantpostpropertyreview")
public class TenantPostPropertyReview extends HttpServlet {
  protected TenantsDao tenantsDao;
  protected PropertiesDao propertiesDao;
  protected PropertiesReviewsDao propertiesReviewsDao;

  @Override
  public void init() throws ServletException {
    tenantsDao = TenantsDao.getInstance();
    propertiesDao = PropertiesDao.getInstance();
    propertiesReviewsDao = PropertiesReviewsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<PropertyReviews> propertyReviews = new ArrayList<>();

    String propertyId = req.getParameter("propertyId");
    if (propertyId == null || propertyId.trim().isEmpty()) {
      messages.put("result", "Please enter a valid landlord username.");
    } else {
      // Retrieve landlord Reviews and store as a message.
      try {
        propertyReviews = propertiesReviewsDao.getPropertyReviewsByPropertyId(Integer.valueOf(propertyId));
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + propertyId);

      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousUsername", propertyId);
    }
    req.setAttribute("reviews", propertyId);

    // Just render the JSP.
    req.getRequestDispatcher("/TenantPostPropertyReview.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve info and validate (may change depending on how UI goes).
    String propertyId = req.getParameter("propertyId");
    String tenantUsername = req.getParameter("tenantUsername");
    if (propertyId == null || propertyId.trim().isEmpty()) {
      messages.put("success", "Invalid propertyId");
    } else if (tenantUsername == null || tenantUsername.trim().isEmpty()) {
      messages.put("success", "Invalid landlord UserName");
    } else {
      // Create reviewer and landlord.
      Properties property;
      Tenants tenant;
      try {
        property = propertiesDao.getPropertiesById(Integer.valueOf(propertyId));
        tenant = tenantsDao.getTenantsFromUserName(tenantUsername);
      } catch (SQLException ex) {
        messages.put("success", "Property or tenant username does not exist");
        ex.printStackTrace();
        throw new IOException(ex);
      }

      // Create the Review.
      float rating = Float.valueOf(req.getParameter("rating"));
      String content = req.getParameter("content");

      try {
        Date date = new Date();
        PropertyReviews propertyReview = new PropertyReviews(new Timestamp(date.getTime()), rating,
            content, property, tenant);
        propertyReview = propertiesReviewsDao.create(propertyReview);
        messages.put("success",
            "Successfully created review for property " + propertyId + " by " + tenantUsername);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/TenantPostPropertyReview.jsp").forward(req, resp);
  }
}
