import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;

@WebServlet("/my-bookings")
public class ServletMyBookings extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		
		String customerID = req.getParameter("customerID");
		System.out.println("MY Bookings"+customerID);
		
		Runner run = new Runner();
		run.getConn();
		
        ArrayList<Booking> bookings = run.getBookings(Integer.valueOf(customerID));
			
        if (bookings.isEmpty()) {
            pw.println("No bookings found");
        } else {
            // Set the content type of the response
            res.setContentType("text/html");

            // Get the PrintWriter object
            PrintWriter out = res.getWriter();

            // Output the HTML code for the bookings table
            String previousPage = req.getHeader("referer");
		    out.println("<html><head><title>Back Button</title></head>");
		    out.println("<body>");
		    out.println("<button onclick=\"goBack()\">Go Back</button>");
		    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
		    out.println("</body></html>");		
            out.println("<table>");
            out.println("<tr><th>Booking ID</th><th>Start Date</th><th>End Date</th></tr>");
            for (Booking booking : bookings) {
                out.println("<tr>");
                out.println("<td>" + booking.getBooking_ID() + "</td>");
                out.println("<td>" + booking.getStart_date() + "</td>");
                out.println("<td>" + booking.getEnd_datel() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
	}

}