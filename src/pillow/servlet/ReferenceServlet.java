package pillow.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pillow.dal.*;
import pillow.model.*;


@WebServlet("/reference")
public class ReferenceServlet extends HttpServlet {
	
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

			List<Reference> references = new ArrayList<Reference>();

			String tenant = req.getParameter("username");
			if (tenant == null || tenant.trim().isEmpty()) {
				messages.put("result", "Please enter a valid tenant.");
			} else {
				// Retrieve properties and store as a message.
				try {
					references = referenceDao.getReferenceFromUserName(tenant);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				messages.put("result", "Displaying results for " + tenant);
				// Save the previous search term, so it can be used as the default
				// in the input box when rendering FindUsers.jsp.
				messages.put("previousFirstName", tenant);
			}
			req.setAttribute("references", references);

			req.getRequestDispatcher("/Reference.jsp").forward(req, resp);
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
        resp.sendRedirect("./reference?username=" + userName);
    }
}