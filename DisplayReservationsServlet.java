import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DisplayReservationsServlet")
public class DisplayReservationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_reservation", "root","user");

            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM train_reservations");

            out.println("<html>");
            out.println("<head><title>Reservations</title></head>");
            out.println("<body>");
            out.println("<h1>Train Reservations</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Train Name</th><th>Source</th><th>Destination</th><th>Date</th><th>Passenger Name</th><th>Seat Count</th></tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("id") + "</td>");
                out.println("<td>" + rs.getString("train_name") + "</td>");
                out.println("<td>" + rs.getString("source") + "</td>");
                out.println("<td>" + rs.getString("destination") + "</td>");
                out.println("<td>" + rs.getString("date") + "</td>");
                out.println("<td>" + rs.getString("passenger_name") + "</td>");
                out.println("<td>" + rs.getInt("seat_count") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body></html>");

            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
