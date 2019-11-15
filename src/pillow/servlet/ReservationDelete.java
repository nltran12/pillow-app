
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


@WebServlet("/reservationdelete")
public class ReservationDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Reservation");        
        req.getRequestDispatcher("/ReservationDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String reservationId = req.getParameter("reservationId");
        if (reservationId == null || reservationId.trim().isEmpty()) {
            messages.put("title", "Invalid reservation");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Tenant.
	        try {
	        	
	        	int id = Integer.parseInt(reservationId);
	          Reservations reservation = reservationsDao.getReservationsById(id);
	          reservation = reservationsDao.delete(reservation);
	        	// Update the message.
		        if (reservation == null) {
		            messages.put("title", "Successfully deleted " + reservationId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + reservationId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ReservationDelete.jsp").forward(req, resp);
    }
}
