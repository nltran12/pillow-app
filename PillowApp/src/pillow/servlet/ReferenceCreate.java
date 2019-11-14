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


@WebServlet("/referencecreate")
public class ReferenceCreate extends HttpServlet {
	
	protected ReferenceDao referenceDao;

	@Override
	public void init() throws ServletException {
		referenceDao = referenceDao.getInstance();
	}
  
  	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
  		// Map for storing messages.
  		Map<String, String> messages = new HashMap<String, String>();
  		req.setAttribute("messages", messages);
  		// Just render the JSP.   
  		req.getRequestDispatcher("/ReferenceCreate.jsp").forward(req, resp);
	}
  	
  	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        TenantsDao tenantsDao = TenantsDao.getInstance(); 
        
        // Retrieve and validate name.
        String userName = req.getParameter("username");
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the Tenant.
        	Tenants user = new Tenants(userName);
        	String referenceName = req.getParameter("name");
            String phone = req.getParameter("phone");
            
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	user = tenantsDao.getTenantsFromUserName(userName);
	        	Reference reference = new Reference(referenceName, phone, user);
	        	reference = referenceDao.create(reference);
	        	messages.put("success", "Successfully created reference " 
	        	          + referenceName + " for " + userName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }   
        req.getRequestDispatcher("/ReferenceCreate.jsp").forward(req, resp);
    }
}