
public class Customer {

	int Customer_ID;
	int SIN;
	String password;
	String name;
	String address;
	String registration_date;
	
	public Customer(int customer_ID, int SIN, String password, String name, String address, String registration_date) {
		this.Customer_ID = customer_ID;
		this.SIN = SIN;
		this.password = password;
		this.name = name;
		this.address = address;
		this.registration_date = registration_date;
	}

	public int getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		Customer_ID = customer_ID;
	}

	public int getSIN() {
		return SIN;
	}

	public void setSIN(int SIN) {
		this.SIN = SIN;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}
	
	
}
