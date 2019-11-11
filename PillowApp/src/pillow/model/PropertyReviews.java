package pillow.model;

import java.util.Date;

public class PropertyReviews extends Reviews {
	protected Reviews review;
	protected Properties property;
	protected Tenants Reviewer;

	public PropertyReviews(int reviewId, Date created, float rating, String content,
			Reviews review, Properties property, Tenants reviewer) {
		super(reviewId, created, rating, content);
		this.review = review;
		this.property = property;
		Reviewer = reviewer;
	}

	public PropertyReviews(int reviewId) {
		super(reviewId);
	}

	public PropertyReviews(Date created, float rating, String content,
			Reviews review, Properties property, Tenants reviewer) {
		super(created, rating, content);
		this.review = review;
		this.property = property;
		Reviewer = reviewer;
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

	public Tenants getReviewer() {
		return Reviewer;
	}

	public void setReviewer(Tenants reviewer) {
		Reviewer = reviewer;
	}
}
