
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

@WebServlet("/add-remove-hotel")
public class ServletAddOrRemoveHotel extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String employeeID = req.getParameter("employeeID");
		System.out.println("remove hotel: " + employeeID);

		String id_remove = req.getParameter("id");
		
		
		String add_remove = req.getParameter("add-remove");

		int employeeIDint = Integer.parseInt(employeeID);


		Runner run = new Runner();
		run.getConn();

		if (add_remove.equals("remove")) {
			try {
				if (id_remove.length() > 0) {
					int id_removeInt = Integer.parseInt(id_remove);
					run.foreignToZero();
					run.deleteHotel(id_removeInt);
					run.foreignToOne();
					String previousPage = req.getHeader("referer");
					out.println("<ul>");
					out.println("CONFIRMED");
					out.println("</ul>");
				    out.println("<html><head><title>Back Button</title></head>");
				    out.println("<body>");
				    out.println("<button onclick=\"goBack()\">Go Back</button>");
				    out.println("<script>function goBack() {window.location.href='" + previousPage + "';}</script>");
				    out.println("</body></html>");	
				} else {
					String previousPage = req.getHeader("referer");
					out.println("<ul>");
					out.println("You did not enter a hotel id");
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
			String city = req.getParameter("city");
			String numRooms = req.getParameter("numRooms");
			String address = req.getParameter("address");
			String email = req.getParameter("email");
			String phone = req.getParameter("phone");
			String chainId = req.getParameter("chainId");
			
			
			
			try {
				int chainIdint = Integer.parseInt(chainId);
				int numRoomsInt = Integer.parseInt(numRooms);
				run.createHotel(chainIdint, 1, city, numRoomsInt, address,
						email, phone);
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
