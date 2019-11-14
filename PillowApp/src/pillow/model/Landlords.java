package pillow.model;

import java.util.Date;

public class Landlords extends Users {
	protected BusinessType BusinessType;
	
	public enum BusinessType {
		INDEPENDENT, MANAGER
	}


	public Landlords(String userName, String password, String firstName, String lastName, String email, Date doB,
			String phone, Landlords.BusinessType businessType) {
		super(userName, password, firstName, lastName, email, doB, phone);
		this.BusinessType = businessType;
	}
	

	public Landlords(String userName) {
		super(userName);

	}

	public BusinessType getBusinessType() {
		return BusinessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.BusinessType = businessType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
