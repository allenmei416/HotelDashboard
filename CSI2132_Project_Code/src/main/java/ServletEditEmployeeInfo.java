
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

@WebServlet("/Edit-employee")
public class ServletEditEmployeeInfo extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String employeeID = req.getParameter("employeeID");
		int employeeIDint = Integer.parseInt(employeeID);
		System.out.println("employee edit: "+employeeID);
		
		String address = req.getParameter("address");
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String password = req.getParameter("password");
		
		Runner run = new Runner();
		run.getConn();
		
		try {
			run.editEmployee(employeeIDint,address, fname, lname, password );
			String previousPage = req.getHeader("referer");
			out.println("<ul>");
			out.println("Confirmed");
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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}