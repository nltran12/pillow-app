package pillow.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pillow.dal.TenantsDao;
import pillow.model.Tenants;

@WebServlet("/updatebackgroundcheck")
public class UpdateBackgroundCheck extends HttpServlet {

  protected TenantsDao tenantsDao;

  @Override
  public void init() throws ServletException {
    tenantsDao = tenantsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/UpdateBackgroundCheck.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve user and validate.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Please enter a valid UserName.");
    } else {
      try {
        Tenants tenant = tenantsDao.getTenantsFromUserName(userName);
        if(tenant == null) {
          messages.put("success", "UserName does not exist. No update to perform.");
        } else {
          String creditScore = req.getParameter("creditscore");
          if (creditScore == null || creditScore.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Credit Score.");
          } else {

            tenant = tenantsDao.updateBackgroundCheckInfo(tenant, true, Integer.parseInt(creditScore));
            messages.put("success", "Successfully updated " + userName);
          }
        }
        req.setAttribute("tenant", tenant);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/UpdateBackgroundCheck.jsp").forward(req, resp);
  }

}
