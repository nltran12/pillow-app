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
import pillow.dal.LandlordReviewsDao;
import pillow.dal.LandlordsDao;
import pillow.dal.TenantReviewsDao;
import pillow.dal.TenantsDao;
import pillow.model.LandlordReviews;
import pillow.model.Landlords;
import pillow.model.TenantReviews;
import pillow.model.Tenants;

@WebServlet("/landlordposttenantreview")
public class LandlordPostTenantReview extends HttpServlet {
  protected TenantsDao tenantsDao;
  protected LandlordsDao landlordsDao;
  protected TenantReviewsDao tenantReviewsDao;

  @Override
  public void init() throws ServletException {
    tenantsDao = TenantsDao.getInstance();
    landlordsDao = LandlordsDao.getInstance();
    tenantReviewsDao = TenantReviewsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<TenantReviews> tenantReviews = new ArrayList<>();

    String tenantUsername = req.getParameter("tenantUsername");
    if (tenantUsername == null || tenantUsername.trim().isEmpty()) {
      messages.put("result", "Please enter a valid landlord username.");
    } else {
      // Retrieve landlord Reviews and store as a message.
      try {
        tenantReviews = tenantReviewsDao.getTenantReviewsFromUserName(tenantUsername);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + tenantUsername);

      // Save the previous search term, so it can be used as the default
      // in the input box when rendering FindUsers.jsp.
      messages.put("previousUsername", tenantUsername);
    }
    req.setAttribute("reviews", tenantUsername);

    // Just render the JSP.
    req.getRequestDispatcher("/LandlordPostTenantReview.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String reviewerUsername = req.getParameter("reviewerUsername");
    String tenantUsername = req.getParameter("tenantUsername");
    if (reviewerUsername == null || reviewerUsername.trim().isEmpty()) {
      messages.put("success", "Invalid reviewer UserName");
    } else if (tenantUsername == null || tenantUsername.trim().isEmpty()) {
      messages.put("success", "Invalid landlord UserName");
    } else {
      // Create reviewer and landlord.
      Landlords reviewer;
      Tenants tenant;
      try {
        reviewer = landlordsDao.getLandlordsFromUserName(reviewerUsername);
        tenant = tenantsDao.getTenantsFromUserName(tenantUsername);
      } catch (SQLException ex) {
        messages.put("success", "Reviewer or tenant username does not exist");
        ex.printStackTrace();
        throw new IOException(ex);
      }

      // Create the Review.
      float rating = Float.valueOf(req.getParameter("rating"));
      String content = req.getParameter("content");

      try {
        Date date = new Date();
        TenantReviews tenantReview = new TenantReviews(new Timestamp(date.getTime()), rating,
            content, reviewer, tenant);
        tenantReview = tenantReviewsDao.create(tenantReview);
        messages.put("success",
            "Successfully created review for " + tenantUsername + " for " + reviewerUsername);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/LandlordPostTenantReview.jsp").forward(req, resp);
  }
}
