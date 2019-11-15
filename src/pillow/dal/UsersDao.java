package pillow.dal;

import pillow.model.Users;

import java.sql.*;

public class UsersDao {
    protected ConnectionManager connectionManager;
    private static UsersDao instance = null;
    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }
    public static UsersDao getInstance() {
        if(instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }

    public Users create(Users user) throws SQLException {
        String insertUser = "INSERT INTO Users(UserName,Password,FirstName,LastName,Email,DoB,Phone)"
                            + " VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);

            insertStmt.setString(1, user.getUserName());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setString(5, user.getEmail());
            insertStmt.setTimestamp(6, new Timestamp(user.getDoB().getTime()));
            insertStmt.setString(7, user.getPhone());

            insertStmt.executeUpdate();

            return user;
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
        }
    }

    public Users getUserByUserName(String userName) throws SQLException {
        String selectUser = "SELECT * FROM Users WHERE UserName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, userName);

            results = selectStmt.executeQuery();

            if(results.next()) {
                String resultUserName = results.getString("UserName");
                String firstName = results.getString("FirstName");
                String lastName = results.getString("LastName");
                String password = results.getString("Password");
                String email = results.getString("Email");
                Date DoB = results.getDate("DoB");
                String phone = results.getString("Phone");
                Users user = new Users(resultUserName, firstName, lastName, password, email, DoB, phone);
                return user;
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

    public Users delete(Users user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE UserName=?;";

        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUserName());
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


