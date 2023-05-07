
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

@WebServlet("/add-remove-employee")
public class ServletAddOrRemoveEmployee extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String employeeID = req.getParameter("employeeID");
		System.out.println("remove employee: " + employeeID);

		String id_remove = req.getParameter("id");
		
		
		String add_remove = req.getParameter("add-remove");

		int employeeIDint = Integer.parseInt(employeeID);



		Runner run = new Runner();
		run.getConn();

		if (add_remove.equals("remove")) {
			try {
				if (id_remove.length() > 0) {
					int id_removeInt = Integer.parseInt(id_remove);
					run.deleteEmployee(id_removeInt);
					String previousPage = req.getHeader("referer");
					out.println("<ul>");
					out.println("CONFIRMED");
					out.println("</ul>");
				    out.println("<html><head><title>Back Button</title></head>");
				    out.println("<body>");
				    out.println("<button onclick=\"goBack()\">Go Back</button>");
				    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				    out.println("</body></html>");	
				}
				
			} catch (SQLException e) {
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
			String sin = req.getParameter("sin-number");
			String fname = req.getParameter("first-name");
			String lname = req.getParameter("last-name");
			String address = req.getParameter("address");
			String position = req.getParameter("position");
			String hotelID = req.getParameter("hotel-id");
			
//			int SINint = Integer.parseInt(sin);
//			int hotelIDint = Integer.parseInt(hotelID);
			try {
				int SINint = Integer.parseInt(sin);
				int hotelIDint = Integer.parseInt(hotelID);
				run.createEmployee(hotelIDint, SINint, "111111111", fname, lname, position, address);
				String previousPage = req.getHeader("referer");
				out.println("<ul>");
				out.println("CONFIRMED");
				out.println("</ul>");
			    out.println("<html><head><title>Back Button</title></head>");
			    out.println("<body>");
			    out.println("<button onclick=\"goBack()\">Go Back</button>");
			    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
			    out.println("</body></html>");				
			} catch (Exception e) {
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

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
