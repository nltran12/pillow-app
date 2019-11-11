package pillow.model;

import java.util.Date;

public class LandlordReviews extends Reviews {
	protected Reviews review;
	protected Tenants Reviewer;
	protected Landlords Landlord;

	public LandlordReviews(int reviewId, Date created, float rating, String content,
			Reviews review, Tenants reviewer, Landlords landlord) {
		super(reviewId, created, rating, content);
		this.review = review;
		Reviewer = reviewer;
		Landlord = landlord;
	}

	public LandlordReviews(int reviewId) {
		super(reviewId);
	}

	public LandlordReviews(Date created, float rating, String content, Reviews review,
			Tenants reviewer, Landlords landlord) {
		super(created, rating, content);
		this.review = review;
		Reviewer = reviewer;
		Landlord = landlord;
	}

	public Reviews getReview() {
		return review;
	}

	public void setReview(Reviews review) {
		this.review = review;
	}

	public Tenants getReviewer() {
		return Reviewer;
	}

	public void setReviewer(Tenants reviewer) {
		Reviewer = reviewer;
	}

	public Landlords getLandlord() {
		return Landlord;
	}

	public void setLandlord(Landlords landlord) {
		Landlord = landlord;
	}
}
