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

import pillow.dal.*;
import pillow.model.*;


@WebServlet("/landlordcreate")
public class LandlordCreate extends HttpServlet {
	
	protected LandlordsDao landlordsDao;

	@Override
	public void init() throws ServletException {
		landlordsDao = landlordsDao.getInstance();
	}
  
  	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
  	// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.   
		req.getRequestDispatcher("/LandlordCreate.jsp").forward(req, resp);
	}
	
	@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
  		throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
        messages.put("success", "Invalid UserName");
    } else {
    	// Create the Tenant.
    	String password = req.getParameter("password");
    	String firstName = req.getParameter("firstname");
    	String lastName = req.getParameter("lastname");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
    	// dob must be in the format yyyy-mm-dd.
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String stringDob = req.getParameter("dob");
    	Date dob = new Date();
    	String typeStr = req.getParameter("businesstype");
    	Landlords.BusinessType businessType = Landlords.BusinessType.valueOf(typeStr.toUpperCase());
    			
    	try {
    		dob = dateFormat.parse(stringDob);
    		//businessType = Landlords.BusinessType.valueOf(typeStr);
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    		throw new IOException(e);
    	}
      try {
      	// Exercise: parse the input for StatusLevel.
      	Landlords landlord = new Landlords(userName, password, firstName, lastName, email, 
      								dob, phone, businessType);
      	landlord = landlordsDao.create(landlord);
      	messages.put("success", "Successfully created " + userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }
    req.getRequestDispatcher("/LandlordCreate.jsp").forward(req, resp);
  }
}

/**
 * <p>
        <label for="businesstype">Business Type</label>
        <select id="businesstype" name="businesstype">
          <option selected value="independent">Independent</option>
          <option value="manager">Property Manager</option>
        </select>
      </p>
 */
 
