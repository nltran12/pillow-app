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
	
	public Properties(int propertyId, Users user, String title, String description, boolean transit, String picture,
			String street, String neighborhood, String city, int zip, float latitude, float longitude,
			String propertyType, String roomType, int accomodates, float bathrooms, int bedrooms, float monthlyPrice,
			float securityDeposit, boolean available) {
		this.PropertyId = propertyId;
		this.user = user;
		this.Title = title;
		this.Description = description;
		this.Transit = transit;
		this.Picture = picture;
		this.Street = street;
		this.Neighborhood = neighborhood;
		this.City = city;
		this.Zip = zip;
		this.Latitude = latitude;
		this.Longitude = longitude;
		this.PropertyType = propertyType;
		this.RoomType = roomType;
		this.Accomodates = accomodates;
		this.Bathrooms = bathrooms;
		this.Bedrooms = bedrooms;
		this.MonthlyPrice = monthlyPrice;
		this.SecurityDeposit = securityDeposit;
		this.Available = available;
	}
	
	
	public Properties(int propertyId) {
		this.PropertyId = propertyId;
	}
	

	public Properties(Users user, String title, String description, boolean transit, String picture, String street,
			String neighborhood, String city, int zip, float latitude, float longitude, String propertyType,
			String roomType, int accomodates, float bathrooms, int bedrooms, float monthlyPrice, float securityDeposit,
			boolean available) {
		super();
		this.user = user;
		Title = title;
		Description = description;
		Transit = transit;
		Picture = picture;
		Street = street;
		Neighborhood = neighborhood;
		City = city;
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
		this.PropertyId = propertyId;
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
		this.Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public boolean isTransit() {
		return Transit;
	}

	public void setTransit(boolean transit) {
		this.Transit = transit;
	}

	public String getPicture() {
		return Picture;
	}

	public void setPicture(String picture) {
		this.Picture = picture;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		this.Street = street;
	}

	public String getNeighborhood() {
		return Neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.Neighborhood = neighborhood;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		this.City = city;
	}

	public int getZip() {
		return Zip;
	}

	public void setZip(int zip) {
		this.Zip = zip;
	}

	public float getLatitude() {
		return Latitude;
	}

	public void setLatitude(float latitude) {
		this.Latitude = latitude;
	}

	public float getLongitude() {
		return Longitude;
	}

	public void setLongitude(float longitude) {
		this.Longitude = longitude;
	}

	public String getPropertyType() {
		return PropertyType;
	}

	public void setPropertyType(String propertyType) {
		this.PropertyType = propertyType;
	}

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		this.RoomType = roomType;
	}

	public int getAccomodates() {
		return Accomodates;
	}

	public void setAccomodates(int accomodates) {
		this.Accomodates = accomodates;
	}

	public float getBathrooms() {
		return Bathrooms;
	}

	public void setBathrooms(float bathrooms) {
		this.Bathrooms = bathrooms;
	}

	public int getBedrooms() {
		return Bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.Bedrooms = bedrooms;
	}

	public float getMonthlyPrice() {
		return MonthlyPrice;
	}

	public void setMonthlyPrice(float monthlyPrice) {
		this.MonthlyPrice = monthlyPrice;
	}

	public float getSecurityDeposit() {
		return SecurityDeposit;
	}

	public void setSecurityDeposit(float securityDeposit) {
		this.SecurityDeposit = securityDeposit;
	}

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean available) {
		this.Available = available;
	}
	
	
	
	
	
	

}
