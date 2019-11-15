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
import pillow.dal.UsersDao;
import pillow.model.Users;

@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {

  protected UsersDao usersDao;

  @Override
  public void init() throws ServletException {
    usersDao = usersDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String userName = req.getParameter("username");
    if (userName == null || userName.trim().isEmpty()) {
      messages.put("success", "Invalid UserName");
    } else {
      // Create the User.
      String password = req.getParameter("password");
      String firstName = req.getParameter("firstname");
      String lastName = req.getParameter("lastname");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      // dob must be in the format yyyy-mm-dd.
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String stringDob = req.getParameter("dob");
      Date dob = new Date();
      try {
        dob = dateFormat.parse(stringDob);
      } catch (ParseException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
      try {
        // Exercise: parse the input for StatusLevel.
        Users user = new Users(userName, password, firstName, lastName, email, dob,
            phone);
        user = usersDao.create(user);
        messages.put("success", "Successfully created " + userName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
  }

}
