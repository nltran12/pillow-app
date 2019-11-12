package pillow.dal;

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
		
		create(new Reviews(landlordReview.getReviewId(), landlordReview.getCreated(),
			landlordReview.getRating(), landlordReview.getContent()));

		String insertAdministrator = "INSERT INTO LandlordReviews(ReviewId, Reviewer, Landlord) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdministrator);
			insertStmt.setInt(1, landlordReview.getReviewId());
			insertStmt.setString(2, landlordReview.getReviewer().getUserName());
			insertStmt.setString(3, landlordReview.getLandlord().getUserName());
			insertStmt.executeUpdate();
			
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
	

	public LandlordReviews delete(LandlordReviews landlordReview) throws SQLException {
		String deleteAdministrator = "DELETE FROM LandlordReviews WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdministrator);
			deleteStmt.setString(1, landlordReview.getLandlord().getUserName());
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
	
	public LandlordReviews getlandlordReviewFromUserName(String landlordUsername) throws SQLException {
		List<LandlordReviews> landlordReviews = new ArrayList<LandlordReviews>();
		String selectLandlordReviews =
			"SELECT LandlordReviews.ReviewId AS ReviewId, Reviewer, Landlord " +
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
			
			if (results.next()) {
				int reviewId = results.getInt("ReviewId");
				String reviewer = results.getString("Reviewer");
				String landlord = results.getString("Landlord");
				
				Reviews review = getReviewById(reviewId);
				TenantsDao tenantDao = TenantsDao.getInstance();
				LandlordsDao landlordDao = LandlordsDao.getInstance();
			
				LandlordReviews landlordReview = new LandlordReviews(review.getReviewId(),
						review.getCreated(), review.getRating(), review.getContent(),
						tenantDao.getTenantsFromUserName(reviewer), landlordDao.getLandlordsFromUserName(landlord));
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
		return null;
	}

	public List<LandlordReviews> getLandlordReviewsFromLandlordUserName(String landlordUsername)
			throws SQLException {
		List<LandlordReviews> landlordReviews = new ArrayList<LandlordReviews>();
		String selectLandlordReviews =
			"SELECT LandlordReviews.ReviewId AS ReviewId, Reviewer, Landlord " +
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
			
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				String reviewer = results.getString("Reviewer");
				String landlord = results.getString("Landlord");
				
				Reviews review = getReviewById(reviewId);
				TenantsDao tenantDao = TenantsDao.getInstance();
				LandlordsDao landlordDao = LandlordsDao.getInstance();
			
				LandlordReviews landlordReview = new LandlordReviews(review.getReviewId(),
						review.getCreated(), review.getRating(), review.getContent(),
						tenantDao.getTenantsFromUserName(reviewer), landlordDao.getLandlordsFromUserName(landlord));
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
}
