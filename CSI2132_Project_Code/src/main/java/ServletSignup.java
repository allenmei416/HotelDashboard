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

@WebServlet("/sign-up")
public class ServletSignup extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String signupSIN = req.getParameter("SIN");
		System.out.println(signupSIN);
		int sin = Integer.parseInt(signupSIN);
		String address = req.getParameter("address");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String password = req.getParameter("password");
		LocalDate currentDate = LocalDate.now();
		
		System.out.println(sin);
		
		Runner run = new Runner();
		run.getConn();
		
		boolean valid = false;
		try {
			valid = run.newCustomerCheck(sin);
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
				run.createCustomer(sin, address, currentDate.toString(),fname, lname, password );
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
			out.println("Succesfully signed up!");
			out.println("<ul>");
			out.println("<li><a href=\"Homepage.html\">Return to Homepage</a></li>");
			out.println("</ul>");
		} else {
			out.println("SIN is already taken!");
			out.println("<ul>");
			out.println("<li><a href=\"Homepage.html\">Back</a></li>");
			out.println("</ul>");			
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}


