
public class searchResult {
	
	String rating;
	String city;
	String address;
	String email_address;
	String phone_number;
	
	String chainName;
	
	String price;
	String capacity;
	String viewType;
	String is_extendable;
	String problems;	
	String amenities;
	
	String room_ID;
	
	public searchResult() {
		
	}

	public searchResult(String room_ID,String rating, String city, String address, String email_address, String phone_number,
			String price, String capacity, String viewType, String is_extendable, String problems, String amenities, String chainName) {

		this.room_ID = room_ID;
		this.rating = rating;
		this.city = city;
		this.address = address;
		this.email_address = email_address;
		this.phone_number = phone_number;
		this.price = price;
		this.capacity = capacity;
		this.viewType = viewType;
		this.is_extendable = is_extendable;
		this.problems = problems;
		this.amenities = amenities;
		this.chainName = chainName;
		
	}

	
	public String getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(String room_ID) {
		this.room_ID = room_ID;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getIs_extendable() {
		return is_extendable;
	}

	public void setIs_extendable(String is_extendable) {
		this.is_extendable = is_extendable;
	}

	public String getProblems() {
		return problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	
	

	
}
