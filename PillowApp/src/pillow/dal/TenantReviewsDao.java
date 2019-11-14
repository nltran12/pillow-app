package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import pillow.model.Landlords;
import pillow.model.Reviews;
import pillow.model.TenantReviews;
import pillow.model.Tenants;

public class TenantReviewsDao extends ReviewsDao {
  private static TenantReviewsDao instance = null;
  protected TenantReviewsDao() {
    super();
  }
  public static TenantReviewsDao getInstance() {
    if(instance == null) {
      instance = new TenantReviewsDao();
    }
    return instance;
  }

  public TenantReviews create(TenantReviews tenantReviews) throws SQLException {
    // Insert into the superclass table first.
    Reviews review = create(new Reviews(tenantReviews.getCreated(), tenantReviews.getRating(),
        tenantReviews.getContent()));

    String insertTenantReview = "INSERT INTO TenantReviews(TenantUserName,ReviewerUserName) VALUES"
        + "(?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertTenantReview);
      insertStmt.setString(1, tenantReviews.getTenant().getUserName());
      insertStmt.setString(2, tenantReviews.getReviewer().getUserName());
      insertStmt.executeUpdate();

      tenantReviews.setReviewId(review.getReviewId());
      return tenantReviews;
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

  public TenantReviews getTenantReviewsById(int reviewId) throws SQLException {
    String selectTenantReview =
        "SELECT TenantReviews.ReviewId AS ReviewId,Created,Rating,Content,Tenant,Reviewer " +
            "FROM TenantReviews INNER JOIN Reviews " +
            "  ON TenantReviews.ReviewId = Reviews.ReviewId " +
            "WHERE TenantReviews.ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTenantReview);
      selectStmt.setInt(1, reviewId);
      results = selectStmt.executeQuery();
      TenantsDao tenantsDao = TenantsDao.getInstance();
      LandlordsDao landlordsDao = LandlordsDao.getInstance();
      if(results.next()) {
        int resultReviewId = results.getInt("ReviewId");
        Date created = new Date(results.getTimestamp("Created").getTime());
        float rating = results.getFloat("Rating");
        String content = results.getString("Content");
        String tenantUsername = results.getString("Tenant");
        String reviewer = results.getString("Reviewer");

        Tenants tenant = tenantsDao.getTenantsFromUserName(tenantUsername);
        Landlords landlord = landlordsDao.getLandlordsFromUserName(reviewer);
        return new TenantReviews(resultReviewId, created, rating, content, landlord, tenant);
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

  public TenantReviews delete(TenantReviews tenantReviews) throws SQLException {
    String deleteTenantReview = "DELETE FROM TenantReviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteTenantReview);
      deleteStmt.setInt(1, tenantReviews.getReviewId());
      deleteStmt.executeUpdate();

      super.delete(tenantReviews);

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
