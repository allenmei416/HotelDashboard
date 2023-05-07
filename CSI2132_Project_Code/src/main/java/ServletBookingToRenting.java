
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

@WebServlet("/Book-rent")
public class ServletBookingToRenting extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out  = res.getWriter();
		res.setContentType("text/html");

		String insertBookingID = req.getParameter("insertBookingID");
		int bookingIDInt = Integer.parseInt(insertBookingID);
		String paidAmount = req.getParameter("paidAmount");
		double amount = Double.parseDouble(paidAmount);

		String employeeID = req.getParameter("employeeID");
		System.out.println("hi THIS IS RENT TO BOOK" + employeeID);

		int employeeIDint = Integer.parseInt(employeeID);

		Runner run = new Runner();
		run.getConn();
		

		boolean valid = false;
		try {
			valid = run.checkBookingExistsForBookingToRenting(bookingIDInt, amount, employeeIDint);
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

		if (valid == true) {
			String link = "<li><a href=\"EmployeeDashboard.html?value=" + employeeIDint + "\">Back</a></li>";
			out.println("<ul>");
			out.println("success");
			out.println(link);
			out.println("</ul>");

		} else {
			String previousPage = req.getHeader("referer");
			out.println("<ul>");
			out.println("ERROR");
			out.println("</ul>");
		    out.println("<html><head><title>Back Button</title></head>");
		    out.println("<body>");
		    out.println("<button onclick=\"goBack()\">Go Back</button>");
		    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
		    out.println("</body></html>");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
