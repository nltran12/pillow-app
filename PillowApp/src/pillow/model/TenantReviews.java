package pillow.model;

public class TenantReviews {
	protected Reviews Review;
	protected String Reviewer;
	protected Tenants tenant;
	
	public TenantReviews(Reviews review, String reviewer, Tenants tenant) {
		this.Review = review;
		this.Reviewer = reviewer;
		this.tenant = tenant;
	}
	
	public TenantReviews(Reviews review) {
		this.Review = review;
	}
	
	public TenantReviews(String reviewer, Tenants tenant) {
		this.Reviewer = reviewer;
		this.tenant = tenant;
	}

	public Reviews getReview() {
		return Review;
	}

	public void setReview(Reviews review) {
		this.Review = review;
	}

	public String getReviewer() {
		return Reviewer;
	}

	public void setReviewer(String reviewer) {
		this.Reviewer = reviewer;
	}

	public Tenants getTenant() {
		return tenant;
	}

	public void setTenant(Tenants tenant) {
		this.tenant = tenant;
	}	
}
