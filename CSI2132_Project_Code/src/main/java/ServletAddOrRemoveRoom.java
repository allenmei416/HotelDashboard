
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

@WebServlet("/add-remove-room")
public class ServletAddOrRemoveRoom extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String employeeID = req.getParameter("employeeID");
		System.out.println("remove room: " + employeeID);

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
					run.deleteRoom(id_removeInt);
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
				}
				
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
			String price = req.getParameter("price");
			String amenities = req.getParameter("amenities");
			String capacity = req.getParameter("capacity");
			String view_type = req.getParameter("view-type");
			String extendable = req.getParameter("extendable");
			Boolean exten = false;
			if (extendable.equals("true")) {
				exten = true;
			}
			
			String problems = req.getParameter("problems");
			String hotelID = req.getParameter("hotel-id");
			
			
			try {
				double priceDouble = Double.parseDouble(price);
				int hotelIDint = Integer.parseInt(hotelID);
				int capacityInt = Integer.parseInt(capacity);
				run.createRoom(priceDouble, amenities, capacityInt, view_type, exten,
						problems, hotelIDint);
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
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
	}

}
