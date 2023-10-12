package mathavan;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String trainName = request.getParameter("train_name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        String passengerName = request.getParameter("passenger_name");
        int seatCount = Integer.parseInt(request.getParameter("seat_count"));

        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/railway_reservation", "root","user");

            
            String sql = "INSERT INTO train_reservations (train_name, source, destination, date, passenger_name, seat_count) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, trainName);
            pstmt.setString(2, source);
            pstmt.setString(3, destination);
            pstmt.setString(4, date);
            pstmt.setString(5, passengerName);
            pstmt.setInt(6, seatCount);
            pstmt.executeUpdate();
            
            
            pstmt.close();
            conn.close();

            out.println("<html>");
            out.println("<head><title>Reservation Confirmation</title></head>");
            out.println("<body>");
            out.println("<h1>Reservation Confirmation</h1>");
            out.println("<p>Reservation successfully made.</p>");
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
