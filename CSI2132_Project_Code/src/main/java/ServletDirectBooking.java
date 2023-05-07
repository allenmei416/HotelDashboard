
import javax.servlet.RequestDispatcher;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/direct-booking")
public class ServletDirectBooking extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String sin = req.getParameter("sin");
		String address = req.getParameter("address");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String type = req.getParameter("customer-type");

		String start_date = req.getParameter("start-date");
		String end_date = req.getParameter("end-date");
		String roomID = req.getParameter("roomID");
		String paidAmount = req.getParameter("paid-amount");
		double amount = Double.parseDouble(paidAmount);

		String employeeID = req.getParameter("employeeID");
		System.out.println("direct booking: " + employeeID);
		int employeeIDint = Integer.parseInt(employeeID);

		int roomIDint = Integer.parseInt(roomID);
		int SINint = Integer.parseInt(sin);
		
		

		Runner run = new Runner();
		run.getConn();

		if (type.equals("returning")) {
			try {
				run.directBookingReturningCustomer(start_date, end_date, roomIDint,SINint,employeeIDint, amount);
				String previousPage = req.getHeader("referer");
				out.println("<ul>");
				out.println("CONFIRMED");
				out.println("</ul>");
			    out.println("<html><head><title>Back Button</title></head>");
			    out.println("<body>");
			    out.println("<button onclick=\"goBack()\">Go Back</button>");
			    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			    out.println("</body></html>");	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String previousPage = req.getHeader("referer");
				out.println("<html><head><title>Back Button</title></head>");
				out.println("<body>");
				out.println("<p>Error: Please check your input(s)</p>");
				out.println("<button onclick=\"goBack()\">Go Back</button>");
				out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				out.println("</body></html>");
			}
		}
		else {
			boolean valid = false;
			try {
				valid = run.newCustomerCheck(SINint);
				System.out.println(valid);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (valid) {
				try {
					run.directBookingNewCustomer(start_date, end_date, roomIDint,SINint,employeeIDint, amount, address, fname, lname);
					String previousPage = req.getHeader("referer");
					out.println("<ul>");
					out.println("CONFIRMED");
					out.println("</ul>");
				    out.println("<html><head><title>Back Button</title></head>");
				    out.println("<body>");
				    out.println("<button onclick=\"goBack()\">Go Back</button>");
				    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				    out.println("</body></html>");	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String previousPage = req.getHeader("referer");
					out.println("<html><head><title>Back Button</title></head>");
					out.println("<body>");
					out.println("<p>Error: Please check your input(s)</p>");
					out.println("<button onclick=\"goBack()\">Go Back</button>");
					out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
					out.println("</body></html>");
				}
			} else {
				String previousPage = req.getHeader("referer");
				out.println("<ul>");
				out.println("SIN ALREADY TAKEN");
				out.println("</ul>");
			    out.println("<html><head><title>Back Button</title></head>");
			    out.println("<body>");
			    out.println("<button onclick=\"goBack()\">Go Back</button>");
			    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			    out.println("</body></html>");		
			}
			
		}
		

		

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
