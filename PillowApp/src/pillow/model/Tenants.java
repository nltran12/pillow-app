package pillow.model;

import java.util.Date;

public class Tenants extends Users {
	protected int CreditScore;
	protected int Income;
	protected boolean backgroundCheck;
	
	public Tenants(String userName, String password, String firstName, String lastName, String email, Date doB,
			String phone, int creditScore, int income, boolean backgroundCheck) {
		super(userName, password, firstName, lastName, email, doB, phone);
		CreditScore = creditScore;
		Income = income;
		this.backgroundCheck = backgroundCheck;
	}
	
	public Tenants(String userName) {
		super(userName);
	}
	

	public int getCreditScore() {
		return CreditScore;
	}
	public void setCreditScore(int creditScore) {
		CreditScore = creditScore;
	}
	public int getIncome() {
		return Income;
	}
	public void setIncome(int income) {
		Income = income;
	}
	public boolean isBackgroundCheck() {
		return backgroundCheck;
	}
	public void setBackgroundCheck(boolean backgroundCheck) {
		this.backgroundCheck = backgroundCheck;
	}
}
