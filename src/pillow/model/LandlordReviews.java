package pillow.model;

import java.util.Date;

public class LandlordReviews extends Reviews {
	protected Tenants Reviewer;
	protected Landlords Landlord;

	public LandlordReviews(int reviewId, Date created, float rating, String content,
			Tenants reviewer, Landlords landlord) {
		super(reviewId, created, rating, content);
		Reviewer = reviewer;
		Landlord = landlord;
	}

	public LandlordReviews(int reviewId) {
		super(reviewId);
	}

	public LandlordReviews(Date created, float rating, String content, Tenants reviewer,
			Landlords landlord) {
		super(created, rating, content);
		Reviewer = reviewer;
		Landlord = landlord;
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
