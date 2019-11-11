package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import pillow.model.Properties;

public class PropertiesDao {
  protected ConnectionManager connectionManager;

  private static PropertiesDao instance = null;
  protected PropertiesDao() {
    connectionManager = new ConnectionManager();
  }
  public static PropertiesDao getInstance() {
    if(instance == null) {
      instance = new PropertiesDao();
    }
    return instance;
  }

  public Properties create(Properties property) throws SQLException {
    String insertProperty =
        "INSERT INTO Properties(UserName,Title,Description,Transit,Picture,Street,"
            + "Neighborhood,City,State,Zip,Latitude,Longitude,PropertyType,RoomType,Accomodates,"
            + "Bathrooms,Bedrooms,MonthlyPrice,SecurityDesposit,Available) VALUES(?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertProperty,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, property.getUser().getUserName());
      insertStmt.setString(2, property.getTitle());
      insertStmt.setString(3, property.getDescription());
      insertStmt.setBoolean(4, property.isTransit());
      insertStmt.setString(5, property.getPicture());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int propertyId = -1;
      if(resultKey.next()) {
        propertyId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      property.setPropertyId(propertyId);
      return property;
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

}
