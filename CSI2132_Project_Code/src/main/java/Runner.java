import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Runner {

	String hotelTable = "hotel";
	String customerTable = "customer";
	String employeeTable = "employee";
	String bookingTable = "booking";
	String hotelChainTable = "hotel_chain";
	String rentingTable = "renting";
	String hotelRoomTable = "room";
	static Connection connection = null;
	static Statement statement = null;

	public Runner() {

	}

	public void getConn() {

		// MUST CHANGE USERNAME AND PASSWORD
		String jdbcURL = "";
		String username = "";
		String password = "";

		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			statement = connection.createStatement();
			System.out.println("SUCCESFULLY CONNECTED TO THE DATABASE");
		} catch (SQLException e) {
			System.out.println("Error in connecting to SQL server");
			e.printStackTrace();
		}

	}

	public void transferToRenting(double paid_amount, int employee_ID, Booking b) throws SQLException {
		int id = getHighestID(rentingTable, "renting_ID") + 1;
		String query = "INSERT INTO " + rentingTable + " VALUES (" + id + "," + "'" + b.start_date + "'" + "," + "'"
				+ b.end_datel + "'" + "," + paid_amount + "," + b.hotel_ID + "," + b.room_ID + "," + b.customer_ID + ","
				+ employee_ID + "," + b.booking_ID + ");";
		int rows = statement.executeUpdate(query);
	}

	public void deleteRenting(int id) throws SQLException {
		String query = "DELETE FROM " + rentingTable + " WHERE renting_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();

	}

	public int createBooking(String start_date, String end_date, int hotel_ID, int room_ID, int customer_ID)
			throws SQLException {
		int id = getHighestID(bookingTable, "booking_ID") + 1;
		String start_date1 = "'" + start_date + "'";
		String end_datel = "'" + end_date + "'";
		String query = "INSERT INTO booking VALUES(" + id + "," + start_date1 + "," + end_datel + "," + hotel_ID + ","
				+ room_ID + "," + customer_ID + "); ";
		int rows = statement.executeUpdate(query);
		return id;
	}

	public int getHotelIDFromRoomID(int room_ID) throws SQLException {
		String query = "SELECT hotel_ID FROM room WHERE room_ID = " + room_ID + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("hotel_ID");
		}
		return 0;
	}

	public boolean sendBookingToRunner(String room_num, String start_date, String end_date, String customerID)
			throws NumberFormatException, SQLException {
		int hotel_ID = getHotelIDFromRoomID(Integer.parseInt(room_num));
		int roomIDint = Integer.parseInt(room_num);
		String query = "Select * from booking where room_ID = " + roomIDint + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			String start_dateReturned = rs.getString("start_date");
			String end_dateReturned = rs.getString("end_date");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate startDateReturnedFormatted = LocalDate.parse(start_dateReturned, formatter);
			LocalDate endDateReturnedFormatted = LocalDate.parse(end_dateReturned, formatter);
			LocalDate start_dateFormatted = LocalDate.parse(start_date, formatter);
			LocalDate end_dateFormatted = LocalDate.parse(end_date, formatter);
			if (checkOverLappingDates(startDateReturnedFormatted, start_dateFormatted, endDateReturnedFormatted,
					end_dateFormatted)) {
				return false;
			}

		}
		createBooking(start_date, end_date, hotel_ID, Integer.parseInt(room_num), Integer.parseInt(customerID));
		return true;
	}

	public void deleteBooking(int id) throws SQLException {
		String query = "DELETE FROM " + bookingTable + " WHERE booking_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();

	}

	public void createCustomer(int SIN, String address, String registration_date, String first_name, String last_name,
			String password) throws SQLException {
		int id = getHighestID(customerTable, "customer_ID") + 1;
		String sql = "INSERT INTO customer VALUES (?, ?, ?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
		statement.setInt(2, SIN);
		statement.setString(3, password);
		statement.setString(4, first_name);
		statement.setString(5, last_name);
		statement.setString(6, address);
		statement.setString(7, registration_date);
		int rows = statement.executeUpdate();
	}

	public void deleteCustomer(int id) throws SQLException {
		String query = "DELETE FROM " + customerTable + " WHERE customer_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public void createEmployee(int hotel_ID, int SIN, String password, String first_name, String last_name,
			String position, String address) throws SQLException {
		int id = getHighestID(employeeTable, "employee_ID") + 1;
		String query = "INSERT INTO employee VALUES(" + id + "," + SIN + ",'" + password + "', '" + first_name + "','"
				+ last_name + "','" + address + "','" + position + "'," + hotel_ID + "); ";
		int rows = statement.executeUpdate(query);
	}

	public void editEmployee(int employeeID, String address, String fname, String lname, String password)
			throws SQLException {
		String query = "UPDATE employee set ";
		if (address.length() > 0) {
			query = query + "address =" + "'" + address + "'";
			if (fname.length() > 0 || lname.length() > 0 || password.length() > 0) {
				query = query + ",";
			}
		}

		if (fname.length() > 0) {
			query = query + "first_name =" + "'" + fname + "'";
			if (lname.length() > 0 || password.length() > 0) {
				query = query + ",";
			}
		}

		if (lname.length() > 0) {
			query = query + "last_name =" + "'" + lname + "'";
			if (password.length() > 0) {
				query = query + ",";
			}
		}

		if (password.length() > 0) {
			query = query + "password =" + "'" + password + "'";
		}

		query = query + " WHERE employee_ID = " + employeeID;
		int rows = statement.executeUpdate(query);
	}

	public void editCustomer(int customerID, String address, String fname, String lname, String password)
			throws SQLException {
		String query = "UPDATE customer set ";
		if (address.length() > 0) {
			query = query + "address =" + "'" + address + "'";
			if (fname.length() > 0 || lname.length() > 0 || password.length() > 0) {
				query = query + ",";
			}
		}

		if (fname.length() > 0) {
			query = query + "first_name =" + "'" + fname + "'";
			if (lname.length() > 0 || password.length() > 0) {
				query = query + ",";
			}
		}

		if (lname.length() > 0) {
			query = query + "last_name =" + "'" + lname + "'";
			if (password.length() > 0) {
				query = query + ",";
			}
		}

		if (password.length() > 0) {
			query = query + "password =" + "'" + password + "'";
		}

		query = query + " WHERE customer_ID = " + customerID;
		int rows = statement.executeUpdate(query);
	}

	public void deleteEmployee(int id) throws SQLException {
		String query = "DELETE FROM " + employeeTable + " WHERE employee_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();

	}

	public void createHotel(int chain_ID, double rating, String city, int num_rooms, String address,
			String email_address, String phone_number) throws SQLException {
		int id = getHighestID(hotelTable, "hotel_ID") + 1;
		String query = "INSERT INTO hotel VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.setDouble(2, rating);
		statement.setString(3, city);
		statement.setInt(4, num_rooms);
		statement.setString(5, address);
		statement.setString(6, email_address);
		statement.setString(7, phone_number);
		statement.setInt(8, chain_ID);
		int rows = statement.executeUpdate();
	}

	public void deleteHotel(int id) throws SQLException {
		String query = "DELETE FROM " + hotelTable + " WHERE hotel_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
		deleteRoomsOfHotel(id);
	}

	public void createRoom(double price, String amenities, int capacity, String viewType, boolean is_extendable,
			String problems, int hotel_id) throws SQLException {
		int id = getHighestID(hotelRoomTable, "room_ID") + 1;
		String query = "INSERT INTO room VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.setDouble(2, price);
		statement.setString(3, amenities);
		statement.setInt(4, capacity);
		statement.setString(5, viewType);
		statement.setBoolean(6, is_extendable);
		statement.setString(7, problems);
		statement.setInt(8, hotel_id);
		int rows = statement.executeUpdate();
	}

	public void foreignToZero() throws SQLException {
		String query = "SET FOREIGN_KEY_CHECKS=0";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public void foreignToOne() throws SQLException {
		String query = "SET FOREIGN_KEY_CHECKS=1";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public void deleteRoom(int id) throws SQLException {
		String query = "DELETE FROM room WHERE room_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public void deleteRoomsOfHotel(int id) throws SQLException {
		String query = "DELETE FROM room WHERE hotel_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public void createChain(String name, String address, int num_hotels, String email_address, String phone_number)
			throws SQLException {
		int id = getHighestID(hotelChainTable, "chain_ID") + 1;
		String query = "INSERT INTO hotel_chain VALUES(?,?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.setString(3, address);
		statement.setString(4, email_address);
		statement.setString(5, phone_number);
		int rows = statement.executeUpdate();
	}

	public void deleteChain(int id) throws SQLException {
		String query = "DELETE FROM " + hotelChainTable + " WHERE chain_ID = " + id + ";";
		PreparedStatement statement = connection.prepareStatement(query);
		int rows = statement.executeUpdate();
	}

	public static int getHighestID(String tableName, String idName) throws SQLException {
		int returnID = 0;
		String query = "SELECT MAX(" + idName + ") FROM " + tableName + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			returnID = rs.getInt(1);
		}

		return returnID;
	}

	public static int checkLoginCustomer(int SIN, String password) throws SQLException {
		String query = "SELECT * FROM customer USE INDEX (customer_login) WHERE SIN = " + SIN + " AND Password = "
				+ password + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("customer_ID");
		}
		return 0;
	}

	public static boolean newCustomerCheck(int SIN) throws SQLException {
		String query = "SELECT * FROM customer WHERE SIN = " + SIN + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return false;
		}
		return true;
	}

	public static int checkLoginEmployee(int SIN, String password) throws SQLException {
		String query = "SELECT * FROM employee USE INDEX (employee_login) WHERE SIN = " + SIN + " AND Password = "
				+ password + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("employee_ID");
		}
		return 0;
	}

	public static boolean newEmployeeCheck(int SIN) throws SQLException {
		String query = "SELECT * FROM employee WHERE SIN = " + SIN + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return false;
		}
		return true;
	}

	public boolean checkOverLappingDates(LocalDate startDate1, LocalDate startDate2, LocalDate endDate1,
			LocalDate endDate2) {
		// Check if the start date of one range is after the end date of the other range
		if (startDate1.isAfter(endDate2) || startDate2.isAfter(endDate1)) {
			return false; // No overlap
		}
		return true; // Overlap
	}

	public ArrayList<searchResult> hotelRoomCriteria(String start_date, String end_date, String capacity,
			String hotel_category_rating, String city, String hotel_chain, String num_rooms, String room_price_lower,
			String room_price_upper) throws SQLException {

		String query = "SELECT *\r\n" + "FROM (\r\n"
				+ "  SELECT Hotel.hotel_ID, Hotel.rating, Hotel.city, Hotel.address, Hotel.email_address, Hotel.phone_number, Hotel_Chain.name,\r\n"
				+ "		       Room.room_ID, Room.price, Room.amenities, Room.capacity, Room.view_type, Room.is_extendable, Room.problems\r\n"
				+ "  FROM Hotel\r\n" + "  INNER JOIN Hotel_Chain ON Hotel.chain_ID = Hotel_Chain.chain_ID\r\n"
				+ "		INNER JOIN Room ON Room.hotel_ID = Hotel.hotel_ID\r\n" + ") AS Hotel_Rooms\r\n";

		if (capacity.length() > 0 || hotel_category_rating.length() > 0 || city.length() > 0 || hotel_chain.length() > 0
				|| room_price_upper.length() > 0) {
			query = query + "WHERE ";
		}

		if (capacity.length() > 0) {

			int capacityInt = Integer.parseInt(capacity);
			query = query + "capacity =" + capacityInt;

			if (hotel_category_rating.length() > 0 || city.length() > 0 || hotel_chain.length() > 0
					|| room_price_upper.length() > 0) {
				query = query + " AND ";
			}
		}

		if (hotel_category_rating.length() > 0) {
			double ratingLower = Double.parseDouble(hotel_category_rating) - 1;
			double ratingUpper = Double.parseDouble(hotel_category_rating) + 1;

			query = query + "rating BETWEEN " + String.valueOf(ratingLower) + " AND " + String.valueOf(ratingUpper);

			if (city.length() > 0 || hotel_chain.length() > 0 || room_price_upper.length() > 0) {
				query = query + " AND ";
			}

		}

		if (city.length() > 0) {

			query = query + "city = '" + city + "'";

			if (hotel_chain.length() > 0 || room_price_upper.length() > 0) {
				query = query + " AND ";
			}

		}

		if (hotel_chain.length() > 0) {

			query = query + "name = '" + hotel_chain + "'";

			if (room_price_upper.length() > 0) {
				query = query + " AND ";
			}

		}

		if (room_price_upper.length() > 0) {

			query = query + "price BETWEEN " + String.valueOf(room_price_lower) + " AND "
					+ String.valueOf(room_price_upper);

		}
		ResultSet rs = statement.executeQuery(query);

		ArrayList<searchResult> Results = new ArrayList<searchResult>();
		while (rs.next()) {
			String hotel_ID = rs.getString("hotel_ID");
			String priceReturned = rs.getString("price");
			String capacityReturned = rs.getString("capacity");
			String viewTypeReturned = rs.getString("view_type");
			String isExtendableReturned = rs.getString("is_extendable");

			String isExtendableReturnedBoolean = "false";

			if (isExtendableReturned.equals("1")) {
				isExtendableReturnedBoolean = "true";
			}

			String problemReturned = rs.getString("problems");
			String amenitiesReturned = rs.getString("amenities");

			Double ratingReturned = rs.getDouble("rating");
			String cityReturned = rs.getString("city");
			String addressReturned = rs.getString("address");
			String email_addressReturned = rs.getString("email_address");
			String phone_numberReturned = rs.getString("phone_number");

			String ratingReturnedString = String.valueOf(ratingReturned);

			String chainNameReturned = rs.getString("name");
			String room_IDReturned = rs.getString("room_ID");

			searchResult current = new searchResult(room_IDReturned, ratingReturnedString, cityReturned,
					addressReturned, email_addressReturned, phone_numberReturned, priceReturned, capacityReturned,
					viewTypeReturned, isExtendableReturnedBoolean, problemReturned, amenitiesReturned,
					chainNameReturned);

			Results.add(current);
		}

		for (int i = 0; i < Results.size(); i++) {
			String roomID = (Results.get(i)).getRoom_ID();
			int roomIDint = Integer.parseInt(roomID);

			query = "Select * from booking where room_ID = " + roomIDint + ";";
			rs = statement.executeQuery(query);

			while (rs.next()) {
				String start_dateReturned = rs.getString("start_date");
				String end_dateReturned = rs.getString("end_date");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate startDateReturnedFormatted = LocalDate.parse(start_dateReturned, formatter);
				LocalDate endDateReturnedFormatted = LocalDate.parse(end_dateReturned, formatter);

				LocalDate start_dateFormatted = LocalDate.parse(start_date, formatter);
				LocalDate end_dateFormatted = LocalDate.parse(end_date, formatter);

				if (checkOverLappingDates(startDateReturnedFormatted, start_dateFormatted, endDateReturnedFormatted,
						end_dateFormatted)) {
					Results.remove(i);
				}

			}

		}

		return Results;
	}

	public Boolean checkBookingExistsForBookingToRenting(int bookingID, double paidAmount, int employeeID)
			throws SQLException {
		String query = "SELECT * FROM booking WHERE booking_ID = " + bookingID + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			Booking b;
			b = getBooking(bookingID);
			transferToRenting(paidAmount, employeeID, b);
			return true;
		}
		return false;

	}

	public Booking getBooking(int bookingID) throws SQLException {
		String query = "SELECT * FROM booking WHERE booking_ID = " + bookingID + ";";
		ResultSet rs = statement.executeQuery(query);
		try {
			while (rs.next()) {
				String start_date = rs.getString("start_date");
				String end_date = rs.getString("end_date");
				String hotel_ID = rs.getString("hotel_ID");
				String room_ID = rs.getString("room_ID");
				String customer_ID = rs.getString("customer_ID");
				Booking booking = new Booking(bookingID, start_date, end_date, Integer.parseInt(hotel_ID),
						Integer.parseInt(room_ID), Integer.parseInt(customer_ID));
				return booking;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public ArrayList<Renting> getRentings(int id) {
		ArrayList<Renting> rentings = new ArrayList<Renting>();

		try {
			String query = "SELECT * FROM renting USE INDEX (customer_renting) WHERE customer_ID = " + id + ";";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String renting_ID = rs.getString("renting_ID");
				String start_date = rs.getString("start_date");
				String end_date = rs.getString("end_date");
				String paid_amount = rs.getString("paid_amount");
				String hotel_ID = rs.getString("hotel_ID");
				String room_ID = rs.getString("room_ID");
				String customer_ID = rs.getString("customer_ID");
				String employee_ID = rs.getString("employee_ID");
				String booking_ID = rs.getString("booking_ID");
				Renting renting = new Renting(Integer.parseInt(renting_ID), start_date, end_date,
						Double.parseDouble(paid_amount), Integer.parseInt(hotel_ID), Integer.parseInt(room_ID),
						Integer.parseInt(customer_ID), Integer.parseInt(employee_ID), Integer.parseInt(booking_ID));
				rentings.add(renting);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rentings;
	}

	public int hotelIDFromEmployeeID(int employeeID) throws SQLException {
		String query = "SELECT * FROM employee WHERE employee_ID = " + employeeID + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("hotel_ID");
		}
		return 0;
	}

	public int customerIDFromSin(int SIN) throws SQLException {
		String query = "SELECT * FROM customer WHERE SIN = " + SIN + ";";
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			return rs.getInt("customer_ID");
		}
		return 0;
	}

	public void directBookingReturningCustomer(String start_date, String end_date, int room_ID, int SIN,
			int employee_ID, double paid_amount) throws SQLException {

		int hotelID = hotelIDFromEmployeeID(employee_ID);
		int customer_ID = customerIDFromSin(SIN);
		int bookingID = createBooking(start_date, end_date, hotelID, room_ID, customer_ID);
		Booking b;
		b = getBooking(bookingID);
		transferToRenting(paid_amount, employee_ID, b);
	}

	public void directBookingNewCustomer(String start_date, String end_date, int room_ID, int SIN, int employee_ID,
			double paid_amount, String address, String first_name, String last_name) throws SQLException {

		LocalDate currentDate = LocalDate.now();
		createCustomer(SIN, address, currentDate.toString(), first_name, last_name, "11111111");
		int hotelID = hotelIDFromEmployeeID(employee_ID);
		int customer_ID = customerIDFromSin(SIN);
		int bookingID = createBooking(start_date, end_date, hotelID, room_ID, customer_ID);
		Booking b;
		b = getBooking(bookingID);
		transferToRenting(paid_amount, employee_ID, b);
	}

	public ArrayList<View1> getView1() {
		ArrayList<View1> views = new ArrayList<View1>();

		try {
			String query = "SELECT * FROM rooms_per_city;";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String city = rs.getString("city");
				String num_rooms = rs.getString("available_rooms");
				View1 view = new View1(city, Integer.parseInt(num_rooms));
				views.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return views;
	}

	public ArrayList<View2> getView2(String name, String address) {
		ArrayList<View2> views = new ArrayList<View2>();

		String query = "SELECT * FROM room_capacity_by_hotel where hotel_name = '" + name + "' AND address like '%"
				+ address + "%'";

		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String nameReturned = rs.getString("hotel_name");
				String roomID = rs.getString("room_ID");
				String capacity = rs.getString("capacity");
				View2 view = new View2(nameReturned, roomID, capacity);
				views.add(view);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return views;
	}

	public ArrayList<Booking> getBookings(int id) {
		ArrayList<Booking> bookings = new ArrayList<Booking>();

		try {
			String query = "SELECT * FROM booking WHERE customer_ID = " + id + ";";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String booking_ID = rs.getString("booking_ID");
				String start_date = rs.getString("start_date");
				String end_date = rs.getString("end_date");
				String hotel_ID = rs.getString("hotel_ID");
				String room_ID = rs.getString("room_ID");
				String customer_ID = rs.getString("customer_ID");
				Booking booking = new Booking(Integer.parseInt(booking_ID), start_date, end_date,
						Integer.parseInt(hotel_ID), Integer.parseInt(room_ID), Integer.parseInt(customer_ID));
				bookings.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}

}
