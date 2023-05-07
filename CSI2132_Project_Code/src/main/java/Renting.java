
public class Renting {
	int renting_ID;
	String start_date;
	String end_datel;
	double paid_amount;
	int hotel_ID;
	int room_ID;
	int customer_ID;
	int employee_ID;
	int booking_ID;
	
	public Renting(int renting_ID, String start_date, String end_datel, double paid_amount, int hotel_ID,
			int room_ID, int customer_ID, int employee_ID, int booking_ID) {
		super();
		this.renting_ID = renting_ID;
		this.start_date = start_date;
		this.end_datel = end_datel;
		this.paid_amount = paid_amount;
		this.hotel_ID = hotel_ID;
		this.room_ID = room_ID;
		this.customer_ID = customer_ID;
		this.employee_ID = employee_ID;
		this.booking_ID = booking_ID;
	}

	public int getRenting_ID() {
		return renting_ID;
	}

	public void setRenting_ID(int renting_ID) {
		this.renting_ID = renting_ID;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_datel() {
		return end_datel;
	}

	public void setEnd_datel(String end_datel) {
		this.end_datel = end_datel;
	}

	public double getPaid_amount() {
		return paid_amount;
	}

	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}

	public int getHotel_ID() {
		return hotel_ID;
	}

	public void setHotel_ID(int hotel_ID) {
		this.hotel_ID = hotel_ID;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public int getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

	public int getEmployee_ID() {
		return employee_ID;
	}

	public void setEmployee_ID(int employee_ID) {
		this.employee_ID = employee_ID;
	}

	public int getBooking_ID() {
		return booking_ID;
	}

	public void setBooking_ID(int booking_ID) {
		this.booking_ID = booking_ID;
	}
	
	

}
