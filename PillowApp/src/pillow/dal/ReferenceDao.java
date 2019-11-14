package pillow.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pillow.model.Reference;
import pillow.model.Tenants;
import pillow.model.Users;

public class ReferenceDao {
  protected ConnectionManager connectionManager;

  private static ReferenceDao instance = null;
  protected ReferenceDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReferenceDao getInstance() {
    if(instance == null) {
      instance = new ReferenceDao();
    }
    return instance;
  }

  public Reference create(Reference reference) throws SQLException {
    String insertReference =
        "INSERT INTO Reference(Name,Phone,UserName) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReference,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, reference.getName());
      insertStmt.setString(2, reference.getPhone());
      insertStmt.setString(3, reference.getUser().getUserName());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int propertyId = -1;
      if(resultKey.next()) {
        propertyId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      reference.setReferenceId(propertyId);
      return reference;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(insertStmt != null) {
        insertStmt.close();
      }
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }

  public Reference getReferenceById(int referenceId) throws SQLException {
    String selectReference =
        "SELECT * FROM Reference WHERE ReferenceId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReference);
      selectStmt.setInt(1, referenceId);
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      if(results.next()) {
        int resultReferenceId = results.getInt("ReferenceId");
        String name = results.getString("Name");
        String phone = results.getString("Phone");
        String userName = results.getString("UserName");

        Users user = usersDao.getUserByUserName(userName);
        return new Reference(resultReferenceId, name, phone, user);
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
    return null;
  }
  
  public Reference getReferenceFromUserName(String userName) throws SQLException {
	    String selectReference = "SELECT* FROM Reference WHERE UserName = ?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectReference);
	      selectStmt.setString(1, userName);
	      results = selectStmt.executeQuery();
	      UsersDao usersDao = UsersDao.getInstance();
	      if(results.next()) {
	        int resultReferenceId = results.getInt("ReferenceId");
	        String name = results.getString("Name");
	        String phone = results.getString("Phone");
	        String username = results.getString("UserName");

	        Users user = usersDao.getUserByUserName(username);
	        return new Reference(resultReferenceId, name, phone, user);
	        
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

  public Reference delete(Reference reference) throws SQLException {
    String deleteReference = "DELETE FROM Reference WHERE ReferenceId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReference);
      deleteStmt.setInt(1, reference.getReferenceId());
      deleteStmt.executeUpdate();

      return null;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(deleteStmt != null) {
        deleteStmt.close();
      }
    }
  }
}
