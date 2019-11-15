package pillow.model;

import java.util.Date;

public class TenantReviews extends Reviews {
	protected Landlords Reviewer;
	protected Tenants tenant;

	public TenantReviews(int reviewId, Date created, float rating, String content,
			Landlords reviewer, Tenants tenant) {
		super(reviewId, created, rating, content);
		Reviewer = reviewer;
		this.tenant = tenant;
	}

	public TenantReviews(int reviewId) {
		super(reviewId);
	}

	public TenantReviews(Date created, float rating, String content, Landlords reviewer,
			Tenants tenant) {
		super(created, rating, content);
		Reviewer = reviewer;
		this.tenant = tenant;
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
