package pillow.model;

public class PropertyReviews {
	protected Reviews review;
	protected Properties property;
	protected String Reviewer;
	
	public PropertyReviews(Reviews review, Properties property, String reviewer) {
		this.review = review;
		this.property = property;
		this.Reviewer = reviewer;
	}
	public PropertyReviews(Reviews review) {
		this.review = review;
	}
	public PropertyReviews(Properties property, String reviewer) {
		this.property = property;
		this.Reviewer = reviewer;
	}
	public Reviews getReview() {
		return review;
	}
	public void setReview(Reviews review) {
		this.review = review;
	}
	public Properties getProperty() {
		return property;
	}
	public void setProperty(Properties property) {
		this.property = property;
	}
	public String getReviewer() {
		return Reviewer;
	}
	public void setReviewer(String reviewer) {
		this.Reviewer = reviewer;
	}
}
