package pillow.model;

import java.util.Date;

public class TenantReviews extends Reviews {
	protected Reviews Review;
	protected Landlords Reviewer;
	protected Tenants tenant;

	public TenantReviews(int reviewId, Date created, float rating, String content,
			Reviews review, Landlords reviewer, Tenants tenant) {
		super(reviewId, created, rating, content);
		Review = review;
		Reviewer = reviewer;
		this.tenant = tenant;
	}

	public TenantReviews(int reviewId) {
		super(reviewId);
	}

	public TenantReviews(Date created, float rating, String content,
			Reviews review, Landlords reviewer, Tenants tenant) {
		super(created, rating, content);
		Review = review;
		Reviewer = reviewer;
		this.tenant = tenant;
	}

	public Reviews getReview() {
		return Review;
	}

	public void setReview(Reviews review) {
		Review = review;
	}

	public Landlords getReviewer() {
		return Reviewer;
	}

	public void setReviewer(Landlords reviewer) {
		Reviewer = reviewer;
	}

	public Tenants getTenant() {
		return tenant;
	}

	public void setTenant(Tenants tenant) {
		this.tenant = tenant;
	}
}
