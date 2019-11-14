package pillow.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
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


@WebServlet("/tenantcreate")
public class TenantCreate extends HttpServlet {
	
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
  		req.getRequestDispatcher("/TenantCreate.jsp").forward(req, resp);
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
        	int creditScore = Integer.valueOf(req.getParameter("creditscore"));
        	int income = Integer.valueOf(req.getParameter("income"));
        	
        	// might need to change this depending on how we setting this in jsp
        	boolean backgroundCheck = Boolean.parseBoolean(req.getParameter("bgcheck"));
        	
        	try {
        		dob = dateFormat.parse(stringDob);
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Tenants tenant = new Tenants(userName, password, firstName, lastName, email, 
	        								dob, phone, creditScore, income, backgroundCheck);
	        	tenant = tenantsDao.create(tenant);
	        	messages.put("success", "Successfully created " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TenantCreate.jsp").forward(req, resp);
    }

}
