package pillow.model;

public class Reservations {
	protected String ReservationiD;
	protected Properties property;
	protected Users user;
	
	public Reservations(String reservationiD, Properties property, Users user) {
		this.ReservationiD = reservationiD;
		this.property = property;
		this.user = user;
	}
	
	public Reservations(String reservationiD) {
		this.ReservationiD = reservationiD;
	}
	
	public Reservations(Properties property, Users user) {
		this.property = property;
		this.user = user;
	}

	public String getReservationiD() {
		return ReservationiD;
	}
	public void setReservationiD(String reservationiD) {
		this.ReservationiD = reservationiD;
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
}
