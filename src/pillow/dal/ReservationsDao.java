package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import pillow.model.Properties;
import pillow.model.Reservations;
import pillow.model.Users;

public class ReservationsDao {
  protected ConnectionManager connectionManager;

  private static ReservationsDao instance = null;
  protected ReservationsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReservationsDao getInstance() {
    if(instance == null) {
      instance = new ReservationsDao();
    }
    return instance;
  }

  public Reservations create(Reservations reservation) throws SQLException {
    String insertReservation =
        "INSERT INTO Reservations(PropertyId,UserName,Start,End,NumOccupants) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReservation,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, reservation.getProperty().getPropertyId());
      insertStmt.setString(2, reservation.getUser().getUserName());
      insertStmt.setTimestamp(3, new Timestamp(reservation.getStartDate().getTime()));
      insertStmt.setTimestamp(4, new Timestamp(reservation.getEndDate().getTime()));
      insertStmt.setInt(5, reservation.getNumOccupants());

      insertStmt.executeUpdate();

      // Retrieve the auto-generated key and set it, so it can be used by the caller.
      resultKey = insertStmt.getGeneratedKeys();
      int reservationId = -1;
      if(resultKey.next()) {
        reservationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      reservation.setReservationId(reservationId);
      return reservation;
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

  public Reservations getReservationsById(int reservationId) throws SQLException {
    String selectReference =
        "SELECT * FROM Reservations WHERE ReservationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReference);
      selectStmt.setInt(1, reservationId);
      results = selectStmt.executeQuery();
      UsersDao usersDao = UsersDao.getInstance();
      PropertiesDao propertiesDao = PropertiesDao.getInstance();
      if(results.next()) {
        int resultReservationId = results.getInt("ReservationId");
        int propertyId = results.getInt("PropertyId");
        String userName = results.getString("UserName");
        Date startDate = new Date(results.getTimestamp("Start").getTime());
        Date endDate = new Date(results.getTimestamp("End").getTime());
        int numOccupants = results.getInt("NumOccupants");

        Users user = usersDao.getUserByUserName(userName);
        Properties property = propertiesDao.getPropertiesById(propertyId);
        return new Reservations(resultReservationId, property, user, startDate, endDate, numOccupants);
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

  public Reservations delete(Reservations reservation) throws SQLException {
    String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReservation);
      deleteStmt.setInt(1, reservation.getReservationId());
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
