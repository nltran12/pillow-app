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


@WebServlet("/findreferencebytenant")
public class ReferenceFindByTenant extends HttpServlet {

  protected ReferenceDao referenceDao;

  @Override
  public void init() throws ServletException {
    referenceDao = ReferenceDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Reference> references = new ArrayList<Reference>();

    String tenant = req.getParameter("tenant");
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

    req.getRequestDispatcher("/ReferenceFindByTenant.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    List<Reference> references = new ArrayList<Reference>();

    String tenant = req.getParameter("tenant");
    if (tenant == null || tenant.trim().isEmpty()) {
      messages.put("result", "Please enter a valid neighborhood.");
    } else {
      // Retrieve properties and store as a message.
      try {
        references = referenceDao.getReferenceFromUserName(tenant);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      messages.put("result", "Displaying results for " + tenant);
    }
    req.setAttribute("references", references);

    req.getRequestDispatcher("/ReferenceFindByTenant.jsp").forward(req, resp);
  }
}


