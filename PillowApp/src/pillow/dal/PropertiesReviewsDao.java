package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pillow.model.Properties;
import pillow.model.PropertyReviews;
import pillow.model.Reviews;
import pillow.model.Tenants;

public class PropertiesReviewsDao extends ReviewsDao {
  private static PropertiesReviewsDao instance = null;
  protected PropertiesReviewsDao() {
    super();
  }
  public static PropertiesReviewsDao getInstance() {
    if(instance == null) {
      instance = new PropertiesReviewsDao();
    }
    return instance;
  }

  public PropertyReviews create(PropertyReviews propertyReviews) throws SQLException {
    // Insert into the superclass table first.
    Reviews review = create(new Reviews(propertyReviews.getCreated(), propertyReviews.getRating(),
        propertyReviews.getContent()));

    String insertPropertyReview = "INSERT INTO PropertyReviews(ReviewId,PropertyId,"
        + "Reviewer) VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertPropertyReview);
      insertStmt.setInt(1, review.getReviewId());
      insertStmt.setInt(2, propertyReviews.getProperty().getPropertyId());
      insertStmt.setString(3, propertyReviews.getReviewer().getUserName());
      insertStmt.executeUpdate();

      propertyReviews.setReviewId(review.getReviewId());
      return propertyReviews;
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

  public PropertyReviews getPropertyReviewsById(int reviewId) throws SQLException {
    String selectPropertyReview =
        "SELECT PropertyReviews.ReviewId AS ReviewId,Created,Rating,Content,PropertyId,Reviewer " +
            "FROM PropertyReviews INNER JOIN Reviews " +
            "  ON PropertyReviews.ReviewId = Reviews.ReviewId " +
            "WHERE PropertyReviews.ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPropertyReview);
      selectStmt.setInt(1, reviewId);
      results = selectStmt.executeQuery();

      PropertiesDao propertiesDao = PropertiesDao.getInstance();
      TenantsDao tenantsDao = TenantsDao.getInstance();
      if(results.next()) {
        int resultReviewId = results.getInt("ReviewId");
        Date created = new Date(results.getTimestamp("Created").getTime());
        float rating = results.getFloat("Rating");
        String content = results.getString("Content");
        int propertyId = results.getInt("PropertyId");
        String reviewerUsername = results.getString("Reviewer");

        Properties property = propertiesDao.getPropertiesById(propertyId);
        Tenants reviewer = tenantsDao.getTenantsFromUserName(reviewerUsername);
        return new PropertyReviews(resultReviewId, created, rating, content, property, reviewer);
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

  public List<PropertyReviews> getPropertyReviewsByPropertyId(int propertyId) throws SQLException {
    List<PropertyReviews> propertyReviews = new ArrayList<>();
    String selectPropertyReview =
        "SELECT PropertyReviews.PropertyId AS ReviewId,Created,Rating,Content,PropertyId,Reviewer"
            + " FROM PropertyReviews INNER JOIN Reviews " +
            "  ON PropertyReviews.PropertyId = Reviews.PropertyId " +
            "WHERE PropertyReviews.PropertyId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectPropertyReview);
      selectStmt.setInt(1, propertyId);
      results = selectStmt.executeQuery();

      PropertiesDao propertiesDao = PropertiesDao.getInstance();
      TenantsDao tenantsDao = TenantsDao.getInstance();
      while (results.next()) {
        int reviewId = results.getInt("ReviewId");
        Date created = new Date(results.getTimestamp("Created").getTime());
        float rating = results.getFloat("Rating");
        String content = results.getString("Content");
        int resultPropertyId = results.getInt("PropertyId");
        String reviewerUsername = results.getString("Reviewer");

        Properties property = propertiesDao.getPropertiesById(resultPropertyId);
        Tenants reviewer = tenantsDao.getTenantsFromUserName(reviewerUsername);
        PropertyReviews propertyReview = new PropertyReviews(reviewId, created, rating, content,
            property, reviewer);
        propertyReviews.add(propertyReview);
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
    return propertyReviews;
  }

  public PropertyReviews delete(PropertyReviews propertyReviews) throws SQLException {
    String deletePropertyReview = "DELETE FROM PropertyReviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deletePropertyReview);
      deleteStmt.setInt(1, propertyReviews.getReviewId());
      deleteStmt.executeUpdate();

      super.delete(propertyReviews);

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
