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
import pillow.dal.TenantsDao;
import pillow.model.LandlordReviews;
import pillow.model.Landlords;
import pillow.model.Tenants;

@WebServlet("/tenantpostlandlordreview")
public class TenantPostLandlordReview extends HttpServlet {
  protected TenantsDao tenantsDao;
  protected LandlordsDao landlordsDao;
  protected LandlordReviewsDao landlordReviewsDao;

  @Override
  public void init() throws ServletException {
    tenantsDao = TenantsDao.getInstance();
    landlordsDao = LandlordsDao.getInstance();
    landlordReviewsDao = LandlordReviewsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/TenantPostLandlordReview.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String reviewerUsername = req.getParameter("reviewerUsername");
    String landlordUsername = req.getParameter("landlordUsername");
    if (reviewerUsername == null || reviewerUsername.trim().isEmpty()) {
      messages.put("success", "Invalid reviewer UserName");
    } else if (landlordUsername == null || landlordUsername.trim().isEmpty()) {
      messages.put("success", "Invalid landlord UserName");
    } else {
      // Create reviewer and landlord.
      Tenants reviewer;
      Landlords landlord;
      try {
        reviewer = tenantsDao.getTenantsFromUserName(reviewerUsername);
        landlord = landlordsDao.getLandlordsFromUserName(landlordUsername);
      } catch (SQLException ex) {
        messages.put("success", "Reviewer or landlord username does not exist");
        ex.printStackTrace();
        throw new IOException(ex);
      }

      // Create the Review.
      float rating = Float.valueOf(req.getParameter("rating"));
      String content = req.getParameter("content");

      try {
        Date date = new Date();
        LandlordReviews landlordReview = new LandlordReviews(new Timestamp(date.getTime()), rating,
            content, reviewer, landlord);
        landlordReview = landlordReviewsDao.create(landlordReview);
        messages.put("success",
            "Successfully created review for " + landlordUsername + " for " + reviewerUsername);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/TenantPostLandlordReview.jsp").forward(req, resp);
  }

}
