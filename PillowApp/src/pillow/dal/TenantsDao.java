package pillow.dal;

import pillow.model.Properties;
import pillow.model.Tenants;
import pillow.model.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TenantsDao extends UsersDao {
  private static TenantsDao instance = null;

  protected TenantsDao() {
    super();
  }

  public static TenantsDao getInstance() {
    if (instance == null) {
      instance = new TenantsDao();
    }
    return instance;
  }

  public Tenants create(Tenants tenants) throws SQLException {
    // Insert into the superclass table first.
    create(new Users(tenants.getUserName(), tenants.getPassword(), tenants.getFirstName(), tenants.getLastName(),
        tenants.getEmail(), tenants.getDoB(), tenants.getPhone()));

    String insertBlogUser = "INSERT INTO Tenants(UserName, CreditScore, Income, BackgroundCheck) "
                          + " VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertBlogUser);
      insertStmt.setString(1, tenants.getUserName());
      insertStmt.setInt(2, tenants.getCreditScore());
      insertStmt.setInt(3, tenants.getIncome());
      insertStmt.setBoolean(4, tenants.isBackgroundCheck());
      insertStmt.executeUpdate();
      return tenants;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (insertStmt != null) {
        insertStmt.close();
      }
    }
  }

  /**
   * Update the LastName of the BlogUsers instance. This runs a UPDATE statement.
   */
  public Tenants updateCreditScore(Tenants tenants, int newCreditScore) throws SQLException {
    String updatePerson = "UPDATE Tenants SET CreditName=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updatePerson);
      updateStmt.setInt(1, newCreditScore);
      updateStmt.setString(2, tenants.getUserName());
      updateStmt.executeUpdate();

      // Update the person param before returning to the caller.
      tenants.setCreditScore(newCreditScore);
      return tenants;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (updateStmt != null) {
        updateStmt.close();
      }
    }
  }

  /**
   * Delete the BlogUsers instance. This runs a DELETE statement.
   */
  public Tenants delete(Tenants tenants) throws SQLException {
    String deleteTenants = "DELETE FROM Tenants WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteTenants);
      deleteStmt.setString(1, tenants.getUserName());
      int affectedRows = deleteStmt.executeUpdate();
      if (affectedRows == 0) {
        throw new SQLException("No records available to delete for UserName=" + tenants.getUserName());
      }
      super.delete(tenants);

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }

  public Tenants getTenantsFromUserName(String userName) throws SQLException {
    String selectTenants = "SELECT Users.UserName AS UserName, Password, FirstName, LastName, "
        + "Email, DoB, Phone, CreditScore, Income, BackgroundCheck " + "FROM Users INNER JOIN Tenants "
        + "ON Users.UserName = Tenants.UserName " + "WHERE Users.UserName = ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTenants);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      if (results.next()) {
        String resultUserName = results.getString("UserName");
        String password = results.getString("Password");
        String firstName = results.getString("FirstName");
        String lastName = results.getString("LastName");
        String email = results.getString("Email");
        Date dob = new Date(results.getTimestamp("DoB").getTime());
        String phone = results.getString("Phone");
        int creditScore = results.getInt("CreditScore");
        int income = results.getInt("income");
        boolean backgroundCheck = results.getBoolean("BackgroundCheck");
        
        Tenants tenant = new Tenants(resultUserName, password, firstName, lastName, email, dob,
            phone, creditScore, income, backgroundCheck);
        
        return tenant;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if (connection != null) {
        connection.close();
      }
      if (selectStmt != null) {
        selectStmt.close();
      }
      if (results != null) {
        results.close();
      }
    }
    return null;
  }
  
  public Tenants updateBackgroundCheck(Tenants user, boolean newCheck) throws SQLException {
    String updateBackgroundCheck = "UPDATE Tenants SET BackgroundCheck=? WHERE UserName=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateBackgroundCheck);
      updateStmt.setBoolean(1, newCheck);
      updateStmt.setString(2, user.getUserName());
      updateStmt.executeUpdate();

      user.setBackgroundCheck(newCheck);
      return user;

    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }
  
  public List<Tenants> getTenantsWithRestraints(int minCreditScore, boolean backgroundCheck, 
                                                  int minIncome) throws SQLException {
    List<Tenants> tenants = new ArrayList<Tenants>();
    String selectTenants = "SELECT UserName FROM Tenants "
        + "WHERE CreditScore >= ? AND BackgroundCheck=? AND Income >= ?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTenants);
      selectStmt.setInt(1, minCreditScore);
      selectStmt.setBoolean(2, backgroundCheck);
      selectStmt.setInt(3, minIncome);
      results = selectStmt.executeQuery();
      while (results.next()) {
        String userName = results.getString("UserName");
        Tenants tenant = getTenantsFromUserName(userName);
        tenants.add(tenant);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(selectStmt != null) {
        selectStmt.close();
      }
      if(results != null) {
        results.close();
      }
    }
    return tenants;
  }
}
