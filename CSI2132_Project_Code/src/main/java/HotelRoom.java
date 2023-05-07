import java.util.ArrayList;

public class HotelRoom {
	int room_ID;
	int hotel_ID;
	double price;
	int capacity;
	String viewType;
	boolean is_extendable;
	String problems;	
	String amenities;
	public HotelRoom(int room_ID, int hotel_ID, double price, int capacity, String viewType, boolean is_extendable,
			String problems, String amenities) {

		this.room_ID = room_ID;
		this.hotel_ID = hotel_ID;
		this.price = price;
		this.capacity = capacity;
		this.viewType = viewType;
		this.is_extendable = is_extendable;
		this.problems = problems;
		this.amenities = amenities;
	}
	public int getRoom_ID() {
		return room_ID;
	}
	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}
	public int getHotel_ID() {
		return hotel_ID;
	}
	public void setHotel_ID(int hotel_ID) {
		this.hotel_ID = hotel_ID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public boolean isIs_extendable() {
		return is_extendable;
	}
	public void setIs_extendable(boolean is_extendable) {
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
