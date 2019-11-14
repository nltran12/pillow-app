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


@WebServlet("/tenantdelete")
public class TenantDelete extends HttpServlet {
	
	protected TenantsDao tenantsDao;
	
	@Override
	public void init() throws ServletException {
		tenantsDao = TenantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Tenant");        
        req.getRequestDispatcher("/TenantDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String tenantUserName = req.getParameter("tenantUserName");
        if (tenantUserName == null || tenantUserName.trim().isEmpty()) {
            messages.put("title", "Invalid Tenant");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the Tenant.
	        try {
	          Tenants tenant = tenantsDao.getTenantsFromUserName(tenantUserName);
	        	tenant = tenantsDao.delete(tenant);
	        	// Update the message.
		        if (tenant == null) {
		            messages.put("title", "Successfully deleted " + tenantUserName);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + tenantUserName);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/TenantDelete.jsp").forward(req, resp);
    }
}
