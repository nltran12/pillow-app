package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pillow.model.Properties;
import pillow.model.Users;

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
						+ "Bathrooms,Bedrooms,MonthlyPrice,SecurityDesposit,Available) VALUES(?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
			insertStmt.setString(6, property.getStreet());
			insertStmt.setString(7, property.getNeighborhood());
			insertStmt.setString(8, property.getCity());
			insertStmt.setString(9, property.getState());
			insertStmt.setInt(10, property.getZip());
			insertStmt.setFloat(11, property.getLatitude());
			insertStmt.setFloat(12, property.getLongitude());
			insertStmt.setString(13, property.getPropertyType());
			insertStmt.setString(14, property.getRoomType());
			insertStmt.setInt(15, property.getAccomodates());
			insertStmt.setFloat(16, property.getBathrooms());
			insertStmt.setInt(17, property.getBedrooms());
			insertStmt.setFloat(18, property.getMonthlyPrice());
			insertStmt.setFloat(19, property.getSecurityDeposit());
			insertStmt.setBoolean(20, property.isAvailable());

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

	public Properties getPropertiesById(int propertyId) throws SQLException {
		String selectProperty =
				"SELECT * FROM Properties WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setInt(1, propertyId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			if(results.next()) {
				int resultPropertyId = results.getInt("PropertyId");
				String userName = results.getString("UserName");
				String title = results.getString("Title");
				String description = results.getString("Description");
				boolean transit = results.getBoolean("Transit");
				String picture = results.getString("Picture");
				String street = results.getString("Street");
				String neighborhood = results.getString("Neighborhood");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
				String propertyType = results.getString("PropertyType");
				String roomType = results.getString("RoomType");
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				int bedrooms = results.getInt("Bedrooms");
				float monthlyPrice = results.getFloat("MonthlyPrice");
				float securityDeposit = results.getFloat("SecurityDeposit");
				boolean available = results.getBoolean("Available");

				Users user = usersDao.getUserByUserName(userName);

				return new Properties(resultPropertyId, user, title, description, transit, picture,
						street, neighborhood, city, state, zip, latitude, longitude, propertyType, roomType,
						accommodates, bathrooms, bedrooms, monthlyPrice, securityDeposit, available);
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

	public List<Properties> getPropertiesByMinRating(float rating) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty = "SELECT PropertyId "
				+ "FROM Reviews INNER JOIN PropertyReviews "
				+ "ON Reviews.ReviewId = PropertyReviews.ReviewId "
				+ "WHERE Rating > ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setFloat(1, rating);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				Properties property = getPropertiesById(propertyId);
				properties.add(property);
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
		return properties;
	}

	public List<Properties> getPropertiesByMaxRent(float monthlyPrice) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty = "SELECT PropertyId FROM Properties WHERE MonthlyPrice <= ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setFloat(1, monthlyPrice);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				Properties property = getPropertiesById(propertyId);
				properties.add(property);
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
		return properties;
	}

	public List<Properties> getPropertiesByNeighborhoodAndMinRating(String neighborhood,
			float rating) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty = "SELECT PropertyId FROM Properties WHERE MonthlyPrice <= ? "
				+ "AND NEIGHBORHOOD = ?";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setString(1, neighborhood);
			selectStmt.setFloat(2, rating);
			results = selectStmt.executeQuery();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				Properties property = getPropertiesById(propertyId);
				properties.add(property);
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
		return properties;
	}

	public List<Properties> getPropertiesByRoomType(String roomType) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty =
				"SELECT * FROM Properties WHERE RoomType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setString(1, roomType);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				String userName = results.getString("UserName");
				String title = results.getString("Title");
				String description = results.getString("Description");
				boolean transit = results.getBoolean("Transit");
				String picture = results.getString("Picture");
				String street = results.getString("Street");
				String resultNeighborhood = results.getString("Neighborhood");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
				String propertyType = results.getString("PropertyType");
				String roomTypeResult = results.getString("RoomType");
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				int bedrooms = results.getInt("Bedrooms");
				float monthlyPrice = results.getFloat("MonthlyPrice");
				float securityDeposit = results.getFloat("SecurityDeposit");
				boolean available = results.getBoolean("Available");

				Users user = usersDao.getUserByUserName(userName);
				Properties property = new Properties(propertyId, user, title, description, transit,
						picture, street, resultNeighborhood, city, state, zip, latitude, longitude, propertyType,
						roomTypeResult, accommodates, bathrooms, bedrooms, monthlyPrice, securityDeposit, available);
				properties.add(property);
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
		return properties;
	}

	public List<Properties> getPropertiesByMinAccommodationAmt(int accommodates)
			throws SQLException {
    List<Properties> properties = new ArrayList<Properties>();
    String selectProperty = "SELECT * FROM Properties WHERE Accommodates >= ?";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectProperty);
      selectStmt.setInt(1, accommodates);
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      while (results.next()) {
        int propertyId = results.getInt("PropertyId");
        String userName = results.getString("UserName");
        String title = results.getString("Title");
        String description = results.getString("Description");
        boolean transit = results.getBoolean("Transit");
        String picture = results.getString("Picture");
        String street = results.getString("Street");
        String resultNeighborhood = results.getString("Neighborhood");
        String city = results.getString("City");
        String state = results.getString("State");
        int zip = results.getInt("Zip");
        float latitude = results.getFloat("Latitude");
        float longitude = results.getFloat("Longitude");
        String propertyType = results.getString("PropertyType");
        String roomTypeResult = results.getString("RoomType");
        int accommodatesResult = results.getInt("Accommodates");
        float bathrooms = results.getFloat("Bathrooms");
        int bedrooms = results.getInt("Bedrooms");
        float monthlyPrice = results.getFloat("MonthlyPrice");
        float securityDeposit = results.getFloat("SecurityDeposit");
        boolean available = results.getBoolean("Available");

        Users user = usersDao.getUserByUserName(userName);
        Properties property = new Properties(propertyId, user, title, description, transit,
            picture, street, resultNeighborhood, city, state, zip, latitude, longitude, propertyType,
            roomTypeResult, accommodatesResult, bathrooms, bedrooms, monthlyPrice, securityDeposit, available);
        properties.add(property);
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
    return properties;
  }

	public List<Properties> getPropertiesByNeighborhood(String neighborhood) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty =
				"SELECT * FROM Properties WHERE Neighborhood=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setString(1, neighborhood);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				String userName = results.getString("UserName");
				String title = results.getString("Title");
				String description = results.getString("Description");
				boolean transit = results.getBoolean("Transit");
				String picture = results.getString("Picture");
				String street = results.getString("Street");
				String resultNeighborhood = results.getString("Neighborhood");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
				String propertyType = results.getString("PropertyType");
				String roomType = results.getString("RoomType");
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				int bedrooms = results.getInt("Bedrooms");
				float monthlyPrice = results.getFloat("MonthlyPrice");
				float securityDeposit = results.getFloat("SecurityDeposit");
				boolean available = results.getBoolean("Available");

				Users user = usersDao.getUserByUserName(userName);
				Properties property = new Properties(propertyId, user, title, description, transit,
						picture, street, resultNeighborhood, city, state, zip, latitude, longitude, propertyType,
						roomType, accommodates, bathrooms, bedrooms, monthlyPrice, securityDeposit, available);
				properties.add(property);
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
		return properties;
	}

	public List<Properties> getPropertiesByAvailability(boolean availability) throws SQLException {
		List<Properties> properties = new ArrayList<Properties>();
		String selectProperty =
				"SELECT * FROM Properties WHERE Available=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProperty);
			selectStmt.setBoolean(1, availability);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int propertyId = results.getInt("PropertyId");
				String userName = results.getString("UserName");
				String title = results.getString("Title");
				String description = results.getString("Description");
				boolean transit = results.getBoolean("Transit");
				String picture = results.getString("Picture");
				String street = results.getString("Street");
				String neighborhood = results.getString("Neighborhood");
				String city = results.getString("City");
				String state = results.getString("State");
				int zip = results.getInt("Zip");
				float latitude = results.getFloat("Latitude");
				float longitude = results.getFloat("Longitude");
				String propertyType = results.getString("PropertyType");
				String roomType = results.getString("RoomType");
				int accommodates = results.getInt("Accommodates");
				float bathrooms = results.getFloat("Bathrooms");
				int bedrooms = results.getInt("Bedrooms");
				float monthlyPrice = results.getFloat("MonthlyPrice");
				float securityDeposit = results.getFloat("SecurityDeposit");
				boolean resultAvailable = results.getBoolean("Available");

				Users user = usersDao.getUserByUserName(userName);
				Properties property = new Properties(propertyId, user, title, description, transit,
						picture, street, neighborhood, city, state, zip, latitude, longitude, propertyType,
						roomType, accommodates, bathrooms, bedrooms, monthlyPrice, securityDeposit, resultAvailable);
				properties.add(property);
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
		return properties;
	}

  public Properties updateAvailability(boolean avail, Properties property) throws SQLException {
    String updateProperty = "UPDATE Properties SET Available = ? WHERE PropertyId = ?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateProperty);
      updateStmt.setBoolean(1, avail);
      updateStmt.setInt(2, property.getPropertyId());
      updateStmt.executeUpdate();

      // Update the property before returning
      property.setAvailable(avail);
      return property;
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
	
	public Properties delete(Properties property) throws SQLException {
		String deleteProperty = "DELETE FROM Properties WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProperty);
			deleteStmt.setInt(1, property.getPropertyId());
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
	
	public Properties updatePropertyAvailability(Properties property, boolean newAvailability) throws SQLException {
    String updateAvailibility = "UPDATE Properties SET Available=? WHERE PropertyId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateAvailibility);
      updateStmt.setBoolean(1, newAvailability);
      updateStmt.setInt(2, property.getPropertyId());
      updateStmt.executeUpdate();

      property.setAvailable(newAvailability);;
      return property;

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
}
