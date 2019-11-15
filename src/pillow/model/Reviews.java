package pillow.model;

import java.util.Date;

public class Reviews {
	protected int ReviewId;
	protected Date Created;
	protected float Rating;
	protected String Content;
	
	public Reviews(int reviewId, Date created, float rating, String content) {
		this.ReviewId = reviewId;
		this.Created = created;
		this.Rating = rating;
		this.Content = content;
	}
	
	public Reviews(int reviewId) {
		this.ReviewId = reviewId;
	}
	public Reviews(Date created, float rating, String content) {
		this.Created = created;
		this.Rating = rating;
		this.Content = content;
	}

	public int getReviewId() {
		return ReviewId;
	}

	public void setReviewId(int reviewId) {
		this.ReviewId = reviewId;
	}

	public Date getCreated() {
		return Created;
	}

	public void setCreated(Date created) {
		this.Created = created;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		this.Rating = rating;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}
}
