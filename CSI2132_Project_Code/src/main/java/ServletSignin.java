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
import java.io.IOException;

@WebServlet("/sign-in")
public class ServletSignin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String signinSIN = req.getParameter("SIN");
		
		
		System.out.println(signinSIN);
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		Runner run = new Runner();
		run.getConn();
		
		if (signinSIN.length()==9) {
			int sin = Integer.parseInt(signinSIN);
			if (role.equals("customer")) {
				int customerExists = 0;
				try {
					customerExists = run.checkLoginCustomer(sin, password);
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

				if (customerExists > 0) {

					String url = "CustomerDashboard.html?value=" + String.valueOf(customerExists);

					res.sendRedirect(url);


				} else {
					out.println("Incorrect");
					out.println("<ul>");
					out.println("<li><a href=\"Homepage.html\">Back</a></li>");
					out.println("</ul>");
				}

			} else {

				int employeeExists = 0;
				try {
					employeeExists = run.checkLoginEmployee(sin, password);
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

				if (employeeExists == 1) {
					String url = "AdminDashboard.html?value=" + String.valueOf(employeeExists);

					res.sendRedirect(url);

				} else if (employeeExists > 0) {
//					
					String url = "EmployeeDashboard.html?value=" + String.valueOf(employeeExists);

					res.sendRedirect(url);

				} else {
					out.println("Incorrect");
					out.println("<ul>");
					out.println("<li><a href=\"Homepage.html\">Back</a></li>");
					out.println("</ul>");

				}
			}
		} else {
			out.println("Incorrect");
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
