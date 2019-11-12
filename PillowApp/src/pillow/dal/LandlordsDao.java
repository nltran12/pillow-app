package pillow.dal;

import pillow.model.Landlords;
import pillow.model.Tenants;
import pillow.model.Users;

import java.sql.*;

public class LandlordsDao extends UsersDao {
    private static LandlordsDao instance = null;
    protected ConnectionManager connectionManager;

    protected LandlordsDao() {
        super();
    }

    public static LandlordsDao getInstance() {
        if (instance == null) {
            instance = new LandlordsDao();
        }
        return instance;
    }

    public Landlords create(Landlords landlord) throws SQLException {
        // Insert into the superclass table first.
        create(new Users(landlord.getUserName(), landlord.getPassword(), landlord.getFirstName(),
                landlord.getLastName(), landlord.getEmail(), landlord.getDoB(), landlord.getPhone()));

        String insertTenant = "INSERT INTO Tenants(UserName, Password, FirstName, LastName," +
                "Email, DoB, Phone, CreditScore, Income, BackgroundCheck) VALUES(?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertTenant);
            insertStmt.setString(1, landlord.getUserName());
            insertStmt.setString(2,landlord.getBusinessType().name());
            insertStmt.executeUpdate();
            return landlord;
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
     * Update the LastName of the BlogUsers instance.
     * This runs a UPDATE statement.
     */
    public Landlords updateBusinessType(Landlords landlords, Landlords.BusinessType newBusinessType) throws SQLException {
        String updateLandlords = "UPDATE Tenants SET Type=? WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateLandlords);
            updateStmt.setString(1, String.valueOf(newBusinessType));
            updateStmt.setString(2, landlords.getUserName());
            updateStmt.executeUpdate();

            // Update the person param before returning to the caller.
            landlords.setBusinessType(newBusinessType);
            return landlords;
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
     * Delete the BlogUsers instance.
     * This runs a DELETE statement.
     */
    public Landlords delete(Landlords landlord) throws SQLException {
        String deleteLandlords = "DELETE FROM Landlords WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteLandlords);
            deleteStmt.setString(1, landlord.getUserName());
            int affectedRows = deleteStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No records available to delete for UserName=" + landlord.getUserName());
            }
            super.delete(landlord);

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

    public Landlords getTenantsFromUserName(String userName) throws SQLException {
        // To build an BlogUser object, we need the Persons record, too.
        String selectLandlords =
                "SELECT * FROM Landlords WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectLandlords);
            selectStmt.setString(1, userName);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String resultUserName = results.getString("UserName");
                String PassWord = results.getString("PassWord");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String Emails = results.getString("Emails");
                Date dob = new Date(results.getTimestamp("DoB").getTime());
                String Phone = results.getString("Phone");
                Landlords.BusinessType businessType = Landlords.BusinessType
                        .valueOf(results.getString("BusinessType"));
                Landlords landlord = new Landlords(resultUserName, PassWord, firstName, lastName, Emails, dob, Phone,
                        businessType);
                return landlord;
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
}

