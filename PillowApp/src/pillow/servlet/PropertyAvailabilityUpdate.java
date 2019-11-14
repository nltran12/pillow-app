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


@WebServlet("/availabilityupdate")
public class PropertyAvailabilityUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String propertyId = req.getParameter("propertyid");
        if (propertyId == null || propertyId.trim().isEmpty()) {
            messages.put("success", "Not a valid property");
        } else {
        	try {
        		Properties property = propertiesDao.
        				getPropertiesById(Integer.parseInt(propertyId));
        		if(property == null) {
        			messages.put("success", "Property does not exist.");
        		}
        		req.setAttribute("property", property);
        	} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve property and validate.
        String propertyId = req.getParameter("propertyid");
        if (propertyId == null || propertyId.trim().isEmpty()) {
            messages.put("success", "Not a valid property");
        } else {
        	try {
        		Properties property = propertiesDao.
        				getPropertiesById(Integer.parseInt(propertyId));
        		if(property == null) {
        			messages.put("success", "Property does not exist.");
        		} else {
        			String newAvailability = req.getParameter("availability");
        			if (newAvailability == null) {
        	            messages.put("success", "Please enter a valid availability.");
        	        } else {
        	        	property = propertiesDao.
        	        			updatePropertyAvailability(property, Boolean.valueOf(newAvailability));
        	        	messages.put("success", "Successfully updated " + propertyId);
        	        }
        		}
        		req.setAttribute("blogUser", property);
        	} catch (Exception e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
	
}
