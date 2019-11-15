package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pillow.model.LandlordReviews;
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

    String insertTenantReview = "INSERT INTO TenantReviews(ReviewId,Tenant,Reviewer) VALUES"
        + "(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertTenantReview);
      insertStmt.setInt(1, review.getReviewId());
      insertStmt.setString(2, tenantReviews.getTenant().getUserName());
      insertStmt.setString(3, tenantReviews.getReviewer().getUserName());
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

  public List<TenantReviews> getTenantReviewsFromUserName(String tenantUserName)
      throws SQLException {
    List<TenantReviews> tenantReviews = new ArrayList<TenantReviews>();
    String selectTenantReviews =
        "SELECT TenantReviews.ReviewId AS ReviewId,Created,Rating,Content,Tenant,Reviewer  " +
            "FROM LandlordReviews INNER JOIN Landlords " +
            "  ON LandlordReviews.Landlord = Landlords.UserName " +
            "WHERE Landlords.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectTenantReviews);
      selectStmt.setString(1, tenantUserName);
      results = selectStmt.executeQuery();

      TenantsDao tenantsDao = TenantsDao.getInstance();
      LandlordsDao landlordsDao = LandlordsDao.getInstance();
      while(results.next()) {
        int reviewId = results.getInt("ReviewId");
        Date created = new Date(results.getTimestamp("Created").getTime());
        float rating = results.getFloat("Rating");
        String content = results.getString("Content");
        String reviewerUserName = results.getString("Reviewer");
        String resultTenantUserName = results.getString("Landlord");

        Tenants tenant = tenantsDao.getTenantsFromUserName(reviewerUserName);
        Landlords reviewer = landlordsDao.getLandlordsFromUserName(resultTenantUserName);
        TenantReviews tenantReview = new TenantReviews(reviewId, created, rating, content,
            reviewer, tenant);
        tenantReviews.add(tenantReview);
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
    return tenantReviews;
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
