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

import pillow.dal.*;
import pillow.model.*;


@WebServlet("/propertycreate")
public class PropertyCreate extends HttpServlet {
	
	protected PropertiesDao propertiesDao;

	@Override
	public void init() throws ServletException {
		propertiesDao = propertiesDao.getInstance();
	}
  
  	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
  	// Map for storing messages.
  	Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.   
		// req.getRequestDispatcher("/PropertyCreate.jsp").forward(req, resp);
	}
  	
	@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
  		throws ServletException, IOException {
      // Map for storing messages.
	  Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    LandlordsDao landlordsDao = LandlordsDao.getInstance(); 

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
        messages.put("success", "Invalid UserName");
    } else {
    	// Create the Property.
    	Landlords landlord = new Landlords(userName);
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        boolean transit = Boolean.valueOf(req.getParameter("transit"));
        
        // not sure how we're going to display this (??)
        String picture = req.getParameter("picture");
        String street = req.getParameter("street");
        String neighborhood = req.getParameter("neighborhood");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        int zip = Integer.valueOf(req.getParameter("zip"));
        float latitude = Float.valueOf(req.getParameter("latitude"));
        float longitude = Float.valueOf(req.getParameter("longitude"));
        String propertyType = req.getParameter("propertytype");
        String roomType = req.getParameter("roomtype");
        int accommodates = Integer.valueOf(req.getParameter("accommodates"));
        float bathrooms = Float.valueOf(req.getParameter("bathrooms"));
        int bedrooms = Integer.valueOf(req.getParameter("bedrooms"));
        float monthlyPrice = Float.valueOf(req.getParameter("monthlyprice"));
        float securityDeposit = Float.valueOf(req.getParameter("securitydeposit"));
        boolean available = Boolean.valueOf(req.getParameter("available"));
    	
    			
    	try {
    		// needs to make sure landlord exists
    		landlord = landlordsDao.getLandlordsFromUserName(userName);
    	} catch (Exception e) {
    		e.printStackTrace();
		throw new IOException(e);
    	}
      try {
      	// Exercise: parse the input for StatusLevel.
      	Properties property = new Properties(landlord, title, description, transit, picture, 
      								street, neighborhood, city, state, zip, latitude, longitude,
      								propertyType, roomType, accommodates, bathrooms, bedrooms,
      								monthlyPrice, securityDeposit, available);
      	property = propertiesDao.create(property);
      	messages.put("success", "Successfully created " + property.getTitle());
      } catch (SQLException e) {
		e.printStackTrace();
		throw new IOException(e);
      }
    }    
    req.getRequestDispatcher("/PropertyCreate.jsp").forward(req, resp);
  }
}