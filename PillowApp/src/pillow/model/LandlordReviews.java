package pillow.model;

public class LandlordReviews {
	protected Reviews review;
	protected String Reviewer;
	protected Landlords Landlord;
	
	public LandlordReviews(Reviews review, String reviewer, Landlords landlord) {
		this.review = review;
		this.Reviewer = reviewer;
		this.Landlord = landlord;
	}
	
	public LandlordReviews(Reviews review) {
		this.review = review;
	}
	
	public LandlordReviews(String reviewer, Landlords landlord) {
		this.Reviewer = reviewer;
		this.Landlord = landlord;
	}
	
	public Reviews getReviewId() {
		return review;
	}

	public void setReviewId(Reviews reviewId) {
		this.review = reviewId;
	}

	public String getReviewer() {
		return Reviewer;
	}

	public void setReviewer(String reviewer) {
		this.Reviewer = reviewer;
	}

	public Landlords getLandlord() {
		return Landlord;
	}

	public void setLandlord(Landlords landlord) {
		this.Landlord = landlord;
	}
	
	
	
	

}
