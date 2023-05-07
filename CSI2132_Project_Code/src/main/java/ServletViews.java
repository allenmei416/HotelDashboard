
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/views")
public class ServletViews  extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String customerID = req.getParameter("customerID");
		System.out.println("remove hotel: " + customerID);
		int employeeIDint = Integer.parseInt(customerID);

		String name = req.getParameter("name");		
		String address = req.getParameter("address");
		String choice = req.getParameter("choice");

		Runner run = new Runner();
		run.getConn();

		if (choice.equals("view1")) {
			 ArrayList<View1> views = run.getView1();
				
		        if (views.isEmpty()) {
		            out.println("None found");
		            String previousPage = req.getHeader("referer");
				    out.println("<html><head><title>Back Button</title></head>");
				    out.println("<body>");
				    out.println("<button onclick=\"goBack()\">Go Back</button>");
				    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				    out.println("</body></html>");		
		            out.println("<table>");
		            out.println("<tr><th>City</th><th>Rooms Available</th></tr>");
		        } else {
		            // Set the content type of the response
		            res.setContentType("text/html");

		            // Get the PrintWriter object

		            // Output the HTML code for the bookings table
		            String previousPage = req.getHeader("referer");
				    out.println("<html><head><title>Back Button</title></head>");
				    out.println("<body>");
				    out.println("<button onclick=\"goBack()\">Go Back</button>");
				    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				    out.println("</body></html>");		
				    out.println("<p>THESE ARE THE NUMBER OF AVAILABLE ROOMS IN EACH CITY TODAY</P>");
		            out.println("<table>");
		            out.println("<tr><th>City</th><th>Rooms Available</th></tr>");
		            for (View1 view : views) {
		                out.println("<tr>");
		                out.println("<td>" + view.getHotel() + "</td>");
		                out.println("<td>" + view.getNum_rooms() + "</td>");
		                out.println("</tr>");
		            }
		            out.println("</table>");
		        }
		} else {
			ArrayList<View2> views = run.getView2(name, address);
			
	        if (views.isEmpty()) {
	            out.println("None found");
	            String previousPage = req.getHeader("referer");
			    out.println("<html><head><title>Back Button</title></head>");
			    out.println("<body>");
			    out.println("<button onclick=\"goBack()\">Go Back</button>");
			    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			    out.println("</body></html>");		
	            out.println("<table>");
	            out.println("<tr><th>City</th><th>Rooms Available</th></tr>");
	        } else {
	            // Set the content type of the response
	            res.setContentType("text/html");

	            // Get the PrintWriter object

	            // Output the HTML code for the bookings table
	            String previousPage = req.getHeader("referer");
			    out.println("<html><head><title>Back Button</title></head>");
			    out.println("<body>");
			    out.println("<button onclick=\"goBack()\">Go Back</button>");
			    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			    out.println("</body></html>");		
	            out.println("<table>");
	            out.println("<tr><th>Hotel Name</th><th>Room Number</th><th>Capacity</th></tr>");
	            for (View2 view2 : views) {
	                out.println("<tr>");
	                out.println("<td>" + view2.getName() + "</td>");
	                out.println("<td>" + view2.getRoomID() + "</td>");
	                out.println("<td>" + view2.getCapacity() + "</td>");
	                out.println("</tr>");
	            }
	            out.println("</table>");
	        }
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
