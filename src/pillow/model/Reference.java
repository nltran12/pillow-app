package pillow.model;
/* class that represents a reference for tenant to rent property*/
public class Reference {
	protected int ReferenceId;
	protected String Name;
	protected String Phone;
	protected Users user;
	
	public Reference(int referenceId, String name, String phone, Users user) {
		this.ReferenceId = referenceId;
		this.Name = name;
		this.Phone = phone;
		this.user = user;
	
	}
	public Reference(int referenceId) {
		this.ReferenceId = referenceId;
		
	}
	public Reference(String name, String phone, Users user) {
		this.Name = name;
		this.Phone = phone;
		this.user = user;
	}
	
	public int getReferenceId() {
		return ReferenceId;
	}

	public void setReferenceId(int referenceId) {
		this.ReferenceId = referenceId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		this.Phone = phone;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
