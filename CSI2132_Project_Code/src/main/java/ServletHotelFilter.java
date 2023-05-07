import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;

@WebServlet("/search")
public class ServletHotelFilter extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");

		String start_date = req.getParameter("start-date");
		String end_date = req.getParameter("end-date");
		String capacity = req.getParameter("room-capacity");
		String hotel_category = req.getParameter("hotel-category");
		String city = req.getParameter("city");
		String hotel_chain = req.getParameter("hotel-chain");
		String num_rooms = req.getParameter("num-rooms");
		String room_price_lower = req.getParameter("room-price-lower");
		String room_price_upper = req.getParameter("room-price-upper");

		Runner run = new Runner();
		run.getConn();
		
		String customerID = req.getParameter("customerID");
		System.out.println("hi" + customerID);
		
		LocalDate date1 = LocalDate.parse(start_date);
		LocalDate date2 = LocalDate.parse(end_date);
		
		
		if (date1.isAfter(date2)) {
			String previousPage = req.getHeader("referer");
			pw.println("<html><head><title>Back Button</title></head>");
			pw.println("<body>");
			pw.println("<p>Dates invalid</p>");
			pw.println("<button onclick=\"goBack()\">Go Back</button>");
			pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			pw.println("</body></html>");
			
        } else {

			if (room_price_lower != null && room_price_upper != null) {
				int lower = Integer.parseInt(room_price_lower);
	
				int upper = Integer.parseInt(room_price_upper);
				if (lower > upper) {
	
					String previousPage = req.getHeader("referer");
					pw.println("<html><head><title>Back Button</title></head>");
					pw.println("<body>");
					pw.println("<p>Price range invalid</p>");
					pw.println("<button onclick=\"goBack()\">Go Back</button>");
					pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
					pw.println("</body></html>");
	
				} else {
					ArrayList<searchResult> Results = null;
					try {
						if (Integer.parseInt(room_price_upper) == 0) {
							room_price_upper = "500";
						}
						Results = run.hotelRoomCriteria(start_date, end_date, capacity, hotel_category, city, hotel_chain,
								num_rooms, room_price_lower, room_price_upper);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						String previousPage = req.getHeader("referer");
						pw.println("<html><head><title>Back Button</title></head>");
						pw.println("<body>");
						pw.println("<p>Error: Please check your input(s)</p>");
						pw.println("<button onclick=\"goBack()\">Go Back</button>");
						pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
						pw.println("</body></html>");
					}
					// Header row
					String previousPage = req.getHeader("referer");
					pw.println("<html><head><title>Back Button</title></head>");
					pw.println("<body>");
					pw.println("<button onclick=\"goBack()\">Go Back</button>");
					pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
					pw.println("</body></html>");
					
					pw.append("<html><head><title>Search Results</title></head><body>");
					pw.append("<h1>Search Results</h1>");
					pw.append("<p> Remember Your Room Number to book it on the previous page! </p>");
					pw.append("<table>");
					pw.append("<tr>");
					pw.append("<th>Room Number</th>");
					pw.append("<th>Rating</th>");
					pw.append("<th>City</th>");
					pw.append("<th>Chain Name</th>");
					pw.append("<th>Address</th>");
					pw.append("<th>Email Address</th>");
					pw.append("<th>Phone Number</th>");
					pw.append("<th>Price ($)</th>");
					pw.append("<th>Capacity</th>");
					pw.append("<th>View Type</th>");
					pw.append("<th>Is Extendable</th>");
					pw.append("<th>Problems</th>");
					pw.append("<th>Amenities</th>");
					pw.append("</tr>");
	
					// Data rows
					for (searchResult result : Results) {
						pw.append("<tr>");
						pw.append("<td>").append(result.getRoom_ID()).append("</td>");
						pw.append("<td>").append(result.getRating()).append("</td>");
						pw.append("<td>").append(result.getCity()).append("</td>");
						pw.append("<td>").append(result.getChainName()).append("</td>");
						pw.append("<td>").append(result.getAddress()).append("</td>");
						pw.append("<td>").append(result.getEmail_address()).append("</td>");
						pw.append("<td>").append(result.getPhone_number()).append("</td>");
						pw.append("<td>").append(result.getPrice()).append("</td>");
						pw.append("<td>").append(result.getCapacity()).append("</td>");
						pw.append("<td>").append(result.getViewType()).append("</td>");
						pw.append("<td>").append(result.getIs_extendable()).append("</td>");
						pw.append("<td>").append(result.getProblems()).append("</td>");
						pw.append("<td>").append(result.getAmenities()).append("</td>");
						pw.append("</tr>");
					}
	
					pw.append("</table></body></html>");
	
				}
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
