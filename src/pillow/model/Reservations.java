package pillow.model;

import java.util.Date;

public class Reservations {
	protected int ReservationId;
	protected Properties property;
	protected Users user;
	protected Date startDate;
	protected Date endDate;
	protected int numOccupants;

	public Reservations(int reservationId, Properties property, Users user, Date startDate,
			Date endDate, int numOccupants) {
		ReservationId = reservationId;
		this.property = property;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOccupants = numOccupants;
	}

	public Reservations(int reservationId) {
		ReservationId = reservationId;
	}

	public Reservations(Properties property, Users user, Date startDate, Date endDate, int numOccupants) {
		this.property = property;
		this.user = user;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOccupants = numOccupants;
	}

	public int getReservationId() {
		return ReservationId;
	}

	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNumOccupants() {
		return numOccupants;
	}

	public void setNumOccupants(int numOccupants) {
		this.numOccupants = numOccupants;
	}

	public boolean hasEnded() {
		return this.endDate.before(new Date());
	}
}
