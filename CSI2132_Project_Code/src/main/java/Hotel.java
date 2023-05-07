public class Hotel {
	
	int hotel_ID;
	double rating;
	String city;
	int chain_ID;
	int num_rooms;
	String address;
	String email_address;
	String phone_number;
	
	public Hotel(int hotelID, double rating, String city, int num_rooms, String address, String email_address, String phone_number, int chainID) {
		this.hotel_ID = hotelID;
		this.chain_ID = chainID;
		this.rating = rating;
		this.city = city;
		this.num_rooms = num_rooms;
		this.address = address;
		this.email_address = email_address;
		this.phone_number = phone_number;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getHotelID() {
		return hotel_ID;
	}

	public void setHotelID(int hotelID) {
		this.hotel_ID = hotelID;
	}

	public int getChainID() {
		return chain_ID;
	}

	public void setChainID(int chainID) {
		this.chain_ID = chainID;
	}

	public int getNum_rooms() {
		return num_rooms;
	}

	public void setNum_rooms(int num_rooms) {
		this.num_rooms = num_rooms;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}