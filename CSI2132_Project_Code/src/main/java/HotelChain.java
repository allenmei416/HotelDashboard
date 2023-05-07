import java.util.ArrayList;

public class HotelChain {
	int chain_ID;
	String office_address;
	int num_hotels;
	ArrayList<String> email_addresses;
	ArrayList<String> phone_numbers;
	
	public HotelChain(int chainID, int num_hotels) {
		this.chain_ID = chainID;
		this.num_hotels = num_hotels;
	}	
	
	public int getChainID() {
		return chain_ID;
	}
	public void setChainID(int chainID) {
		this.chain_ID = chainID;
	}
	public String getOffice_address() {
		return office_address;
	}
	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}
	public int getNum_hotels() {
		return num_hotels;
	}
	public void setNum_hotels(int num_hotels) {
		this.num_hotels = num_hotels;
	}

	public ArrayList<String> getEmail_addresses() {
		return email_addresses;
	}
	
	public void removeEmailAddress(String email) {
		email_addresses.remove(email);
	}
	
	public void addEmailAddress(String email) {
		email_addresses.add(email);
	}

	public ArrayList<String> getPhone_numbers() {
		return phone_numbers;
	}
	
	public void removePhoneNumbers(String phone_number) {
		email_addresses.remove(phone_number);
	}
	
	public void addPhoneNumber(String phone_number) {
		email_addresses.add(phone_number);
	}
}
