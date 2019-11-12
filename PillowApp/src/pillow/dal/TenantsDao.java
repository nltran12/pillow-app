package pillow.dal;

import pillow.model.Tenants;
import pillow.model.Users;

import java.sql.*;

public class TenantsDao extends UsersDao {
    private static TenantsDao instance = null;
    protected ConnectionManager connectionManager;

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
        create(new Users(tenants.getUserName(), tenants.getPassword(), tenants.getFirstName(),
                tenants.getLastName(), tenants.getEmail(), tenants.getDoB(), tenants.getPhone()));

        String insertBlogUser = "INSERT INTO Tenants(UserName, Password, FirstName, LastName," +
                "Email, DoB, Phone, CreditScore, Income, BackgroundCheck) VALUES(?,?,?,?,?,?,?,?,?,?);";
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
     * Update the LastName of the BlogUsers instance.
     * This runs a UPDATE statement.
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
     * Delete the BlogUsers instance.
     * This runs a DELETE statement.
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
        // To build an BlogUser object, we need the Persons record, too.
        String selectTenants =
                "SELECT * FROM Tenants WHERE UserName=?;";
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
                String PassWord = results.getString("PassWord");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String Emails = results.getString("Emails");
                Date dob = new Date(results.getTimestamp("DoB").getTime());
                String Phone = results.getString("Phone");
                int CreditScore = results.getInt("CreditScore");
                int Income = results.getInt("income");
                boolean BackgroundCheck = results.getBoolean("BackgroundCheck");
                Tenants tenant = new Tenants(resultUserName, PassWord, firstName, lastName, Emails, dob, Phone,
                        CreditScore, Income, BackgroundCheck);
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
}


