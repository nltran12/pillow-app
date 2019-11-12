package pillow.model;

public class Properties {
	protected int PropertyId;
	protected Users user;
	protected String Title;
	protected String Description;
	protected boolean Transit;
	protected String Picture;
	protected String Street;
	protected String Neighborhood;
	protected String City;
	protected String State;
	protected int Zip;
	protected float Latitude;
	protected float Longitude;
	protected String PropertyType;
	protected String RoomType;
	protected int Accomodates;
	protected float Bathrooms;
	protected int Bedrooms;
	protected float MonthlyPrice;
	protected float SecurityDeposit;
	protected boolean Available;

	public Properties(int propertyId, Users user, String title, String description, boolean transit,
			String picture, String street, String neighborhood, String city, String state, int zip,
			float latitude, float longitude, String propertyType, String roomType, int accomodates,
			float bathrooms, int bedrooms, float monthlyPrice, float securityDeposit, boolean available) {
		PropertyId = propertyId;
		this.user = user;
		Title = title;
		Description = description;
		Transit = transit;
		Picture = picture;
		Street = street;
		Neighborhood = neighborhood;
		City = city;
		State = state;
		Zip = zip;
		Latitude = latitude;
		Longitude = longitude;
		PropertyType = propertyType;
		RoomType = roomType;
		Accomodates = accomodates;
		Bathrooms = bathrooms;
		Bedrooms = bedrooms;
		MonthlyPrice = monthlyPrice;
		SecurityDeposit = securityDeposit;
		Available = available;
	}

	public Properties(int propertyId) {
		PropertyId = propertyId;
	}

	public Properties(Users user, String title, String description, boolean transit,
			String picture, String street, String neighborhood, String city, String state, int zip,
			float latitude, float longitude, String propertyType, String roomType, int accomodates,
			float bathrooms, int bedrooms, float monthlyPrice, float securityDeposit, boolean available) {
		this.user = user;
		Title = title;
		Description = description;
		Transit = transit;
		Picture = picture;
		Street = street;
		Neighborhood = neighborhood;
		City = city;
		State = state;
		Zip = zip;
		Latitude = latitude;
		Longitude = longitude;
		PropertyType = propertyType;
		RoomType = roomType;
		Accomodates = accomodates;
		Bathrooms = bathrooms;
		Bedrooms = bedrooms;
		MonthlyPrice = monthlyPrice;
		SecurityDeposit = securityDeposit;
		Available = available;
	}

	public int getPropertyId() {
		return PropertyId;
	}

	public void setPropertyId(int propertyId) {
		PropertyId = propertyId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public boolean isTransit() {
		return Transit;
	}

	public void setTransit(boolean transit) {
		Transit = transit;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		Picture = picture;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getNeighborhood() {
		return Neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		Neighborhood = neighborhood;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getZip() {
		return Zip;
	}

	public void setZip(int zip) {
		Zip = zip;
	}

	public float getLatitude() {
		return Latitude;
	}

	public void setLatitude(float latitude) {
		Latitude = latitude;
	}

	public float getLongitude() {
		return Longitude;
	}

	public void setLongitude(float longitude) {
		Longitude = longitude;
	}

	public String getPropertyType() {
		return PropertyType;
	}

	public void setPropertyType(String propertyType) {
		PropertyType = propertyType;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public int getAccomodates() {
		return Accomodates;
	}

	public void setAccomodates(int accomodates) {
		Accomodates = accomodates;
	}

	public float getBathrooms() {
		return Bathrooms;
	}

	public void setBathrooms(float bathrooms) {
		Bathrooms = bathrooms;
	}

	public int getBedrooms() {
		return Bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		Bedrooms = bedrooms;
	}

	public float getMonthlyPrice() {
		return MonthlyPrice;
	}

	public void setMonthlyPrice(float monthlyPrice) {
		MonthlyPrice = monthlyPrice;
	}

	public float getSecurityDeposit() {
		return SecurityDeposit;
	}

	public void setSecurityDeposit(float securityDeposit) {
		SecurityDeposit = securityDeposit;
	}

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean available) {
		Available = available;
	}
}
