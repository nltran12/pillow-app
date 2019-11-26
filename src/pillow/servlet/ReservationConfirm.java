package pillow.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pillow.dal.LandlordsDao;
import pillow.dal.PropertiesDao;
import pillow.dal.ReservationsDao;
import pillow.dal.TenantsDao;
import pillow.model.Landlords;
import pillow.model.Properties;
import pillow.model.Reservations;
import pillow.model.Tenants;
import pillow.model.Users;

@WebServlet("/reservationconfirm")
public class ReservationConfirm extends HttpServlet {

  protected PropertiesDao propertiesDao;
  protected TenantsDao tenantsDao;
  protected ReservationsDao reservationsDao;

  @Override
  public void init() throws ServletException {
    propertiesDao = PropertiesDao.getInstance();
    tenantsDao = TenantsDao.getInstance();
    reservationsDao = ReservationsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    // Just render the JSP.
    req.getRequestDispatcher("/ReservationConfirmation.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate parameters.
    Reservations reservation = null;
    float total = 0;
    float monthlyTotal = 0;
    HttpSession session = req.getSession(true);
    Users user = (Users) session.getAttribute("currentUser");
    String tenantUsername = user.getUserName();
    String propertyId = req.getParameter("propertyId");
    if (user == null || tenantUsername.trim().isEmpty()) {
      messages.put("success", "Invalid tenant UserName");
    } else if (propertyId == null || propertyId.trim().isEmpty()) {
      messages.put("success", "Invalid property id");
    } else {

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
      // Create the Reservation.
      int numOccupants = Integer.valueOf(req.getParameter("numOccupants"));

      // Start and end date must be in the format yyyy-mm-dd.
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String stringStart = req.getParameter("startDate");
      String stringEnd = req.getParameter("endDate");
      Date start = new Date();
      Date end = new Date();

      try {
        start = dateFormat.parse(stringStart);
        end = dateFormat.parse(stringEnd);
      } catch (Exception e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      
      try {
        reservation = new Reservations(property, tenant, start, end, numOccupants); 
        reservation = reservationsDao.create(reservation);
        messages.put("success", "Successfully created reservation for " + tenantUsername);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }      
    }
    req.setAttribute("reservation", reservation);
    req.getRequestDispatcher("/ReservationCompleted.jsp").forward(req, resp);
  }
}
