package pillow.dal;

import java.util.Date;
import pillow.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pillow.dal.TenantsDao;
import pillow.dal.LandlordsDao;


public class LandlordReviewsDao extends ReviewsDao {
	private static LandlordReviewsDao instance = null;
	protected LandlordReviewsDao() {
		super();
	}
	public static LandlordReviewsDao getInstance() {
		if(instance == null) {
			instance = new LandlordReviewsDao();
		}
		return instance;
	}
	
	public LandlordReviews create(LandlordReviews landlordReview) throws SQLException {
		
		Reviews review = create(new Reviews(landlordReview.getReviewId(), landlordReview.getCreated(),
			landlordReview.getRating(), landlordReview.getContent()));

		String insertAdministrator = "INSERT INTO LandlordReviews(ReviewId, Reviewer, Landlord) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setInt(1, review.getReviewId());
			insertStmt.setString(2, landlordReview.getReviewer().getUserName());
			insertStmt.setString(3, landlordReview.getLandlord().getUserName());
			insertStmt.executeUpdate();

			landlordReview.setReviewId(review.getReviewId());
			return landlordReview;
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
	
	public LandlordReviews getlandlordReviewFromId(int reviewId) throws SQLException {
		String selectLandlordReviews =
			"SELECT LandlordReviews.ReviewId AS ReviewId,Created,Rating,Content,Landlord,Reviewer " +
			"FROM LandlordReviews INNER JOIN Reviews " +
			"  ON LandlordReviews.ReviewId = Reviews.ReviewId " +
			"WHERE LandlordReviews.ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLandlordReviews);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();

			TenantsDao tenantsDao = TenantsDao.getInstance();
			LandlordsDao landlordsDao = LandlordsDao.getInstance();
			if (results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				float rating = results.getFloat("Rating");
				String content = results.getString("Content");
				String reviewerUserName = results.getString("Reviewer");
				String landlordUserName = results.getString("Landlord");

				Tenants reviewer = tenantsDao.getTenantsFromUserName(reviewerUserName);
				Landlords landlord = landlordsDao.getLandlordsFromUserName(landlordUserName);
				return new LandlordReviews(resultReviewId, created, rating, content, reviewer, landlord);
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

	public List<LandlordReviews> getLandlordReviewsFromUserName(String landlordUsername)
			throws SQLException {
		List<LandlordReviews> landlordReviews = new ArrayList<LandlordReviews>();
		String selectLandlordReviews =
			"SELECT LandlordReviews.ReviewId AS ReviewId,Created,Rating,Content,Landlord,Reviewer  " +
			"FROM LandlordReviews INNER JOIN Landlords " +
			"  ON LandlordReviews.Landlord = Landlords.UserName " +
			"WHERE Landlords.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLandlordReviews);
			selectStmt.setString(1, landlordUsername);
			results = selectStmt.executeQuery();

			TenantsDao tenantsDao = TenantsDao.getInstance();
			LandlordsDao landlordsDao = LandlordsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date created = new Date(results.getTimestamp("Created").getTime());
				float rating = results.getFloat("Rating");
				String content = results.getString("Content");
				String reviewerUserName = results.getString("Reviewer");
				String resultLandlordUserName = results.getString("Landlord");

				Tenants reviewer = tenantsDao.getTenantsFromUserName(reviewerUserName);
				Landlords landlord = landlordsDao.getLandlordsFromUserName(resultLandlordUserName);
				LandlordReviews landlordReview = new LandlordReviews(reviewId, created, rating, content,
						reviewer, landlord);
				landlordReviews.add(landlordReview);
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
		return landlordReviews;
	}

	public LandlordReviews delete(LandlordReviews landlordReview) throws SQLException {
		String deleteAdministrator = "DELETE FROM LandlordReviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;

		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setInt(1, landlordReview.getReviewId());
			deleteStmt.executeUpdate();

			super.delete(landlordReview);

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
