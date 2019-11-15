package pillow.model;

import java.util.Date;

public class Users {
	protected String UserName;
	protected String Password;
	protected String FirstName;
	protected String LastName;
	protected String Email;
	protected Date DoB;
	protected String Phone;
	public Users(String userName, String password, String firstName, String lastName, String email, Date doB,
			String phone) {
		this.UserName = userName;
		this.Password = password;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.Email = email;
		this.DoB = doB;
		this.Phone = phone;
	}
	
	public Users(String userName) {
		this.UserName = userName;
	}

	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Date getDoB() {
		return DoB;
	}
	public void setDoB(Date doB) {
		DoB = doB;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	
	

}
