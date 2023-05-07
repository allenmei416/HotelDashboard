import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/process-booking")
public class ServletProcessBooking extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String room_num = req.getParameter("room-num");
		String start_date = req.getParameter("start-date");
		String end_date = req.getParameter("end-date");
		
		Runner run = new Runner();
		run.getConn();
		
		String customerID = req.getParameter("customerID2");
		System.out.println("hiii"+customerID);
		
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
           try {
			if (!(run.sendBookingToRunner(room_num,start_date, end_date, customerID ))) {
				String previousPage = req.getHeader("referer");
				pw.println("<ul>");
				pw.println("The dates you chose are not available");
				pw.println("</ul>");
				pw.println("<html><head><title>Back Button</title></head>");
				pw.println("<body>");
				pw.println("<button onclick=\"goBack()\">Go Back</button>");
				pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				pw.println("</body></html>");	
			} else {
				String previousPage = req.getHeader("referer");
				pw.println("<ul>");
				pw.println("Confirmed");
				pw.println("</ul>");
				pw.println("<html><head><title>Back Button</title></head>");
				pw.println("<body>");
				pw.println("<button onclick=\"goBack()\">Go Back</button>");
				pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				pw.println("</body></html>");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String previousPage = req.getHeader("referer");
			pw.println("<html><head><title>Back Button</title></head>");
			pw.println("<body>");
			pw.println("<p>Error: Please check your input(s)</p>");
			pw.println("<button onclick=\"goBack()\">Go Back</button>");
			pw.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			pw.println("</body></html>");
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
        }

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
