package pillow.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import pillow.model.*;

public class ReviewsDao {

	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReviews =
				"INSERT INTO Reviews(ReviewId, Created, Rating, Content) " +
						"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReviews,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getReviewId());
			insertStmt.setTimestamp(2, new Timestamp(review.getCreated().getTime()));
			insertStmt.setFloat(3, review.getRating());
			insertStmt.setString(4, review.getContent());

			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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

	public Reviews getReviewById(int reviewId) throws SQLException{
		String selectReview = "SELECT * FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				String content = results.getString("Content");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				float rating = results.getFloat("Rating");
				
				Reviews review = new Reviews(resultReviewId, created, rating, content);
				return review;
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
	
	public Reviews updateRating(Reviews review, float newRating) throws SQLException {
		String updateRating = "UPDATE Review SET Rating=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRating);
			updateStmt.setFloat(1, newRating);
			updateStmt.setInt(2, review.getReviewId());
			updateStmt.executeUpdate();
			
			review.setRating(newRating);
			return review;
			
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
	
	public Reviews updateContent(Reviews review, String newContent) throws SQLException {
		String updateRating = "UPDATE Review SET Content=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRating);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, review.getReviewId());
			updateStmt.executeUpdate();
			
			
			review.setContent(newContent);
			return review;
			
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

	public Reviews delete(Reviews review) throws SQLException {
		String deleteReviews = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReviews);
			deleteStmt.setInt(1, review.getReviewId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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
