import java.util.ArrayList;

public class Employee {
	int employee_ID;
	int hotel_ID;
	String SIN;
	String password;
	String name;
	String address;
	ArrayList<String> positions;
	
	public Employee(int employee_ID, int hotel_ID, String SIN, String password, String name, String address) {
		super();
		this.employee_ID = employee_ID;
		this.hotel_ID = hotel_ID;
		this.SIN = SIN;
		this.name = name;
		this.address = address;
	}

	public int getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}

	public int getHotel_ID() {
		return hotel_ID;
	}

	public void setHotel_ID(int hotel_ID) {
		this.hotel_ID = hotel_ID;
	}

	public String getSIN() {
		return SIN;
	}

	public void setSSN_SIN(String SIN) {
		this.SIN = SIN;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
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

	public ArrayList<String> getPositions() {
		return positions;
	}
	
	public void removePosition(String position) {
		positions.remove(position);
	}
	
	public void addPosition(String position) {
		positions.add(position);
	}

	
}
