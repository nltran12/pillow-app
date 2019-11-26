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


@WebServlet("/tenantaccountinfo")
public class GetTenantAccountInfo extends HttpServlet {

  protected ReservationsDao reservationsDao;

  @Override
  public void init() throws ServletException {
    reservationsDao = ReservationsDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Reservations> reservations = new ArrayList<>();

    String username = req.getParameter("username");
    if (username == null || username.trim().isEmpty()) {
      messages.put("result", "Please enter a valid tenant.");
    } else {
      // Retrieve properties and store as a message.
      try {
        reservations = reservationsDao.getReservationsByUserName(username);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + username);
    }
    req.setAttribute("tenantReservations", reservations);
    req.getRequestDispatcher("/TenantAccountOverview.jsp").forward(req, resp);
  }
}



