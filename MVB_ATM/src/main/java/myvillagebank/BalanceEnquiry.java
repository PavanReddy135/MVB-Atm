package myvillagebank;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/balanceenquiry")
public class BalanceEnquiry extends HttpServlet {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String user = "pavan";
    private static final String pword = "pavan123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Entered BalanceEnquiry Servlet");

        HttpSession session = request.getSession(false); 
        if (session == null) {
            System.out.println("No active session found.");
            response.sendRedirect("login.jsp");
            return;
        }

        String accountnumber = (String) session.getAttribute("accountnumber");
        if (accountnumber == null || accountnumber.trim().isEmpty()) {
            System.out.println("Account number missing in session.");
            response.sendRedirect("login.jsp");
            return;
        }

        System.out.println("Session found. Account number: " + accountnumber);

        try (Connection con = DriverManager.getConnection(url, user, pword)) {
            System.out.println("Connected to database.");

            String fquery = "SELECT BALANCE FROM MYVILLAGEBANK WHERE ACCOUNTNUMBER=?";
            try (PreparedStatement pstmt = con.prepareStatement(fquery)) {
                pstmt.setString(1, accountnumber);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double balance = rs.getDouble("balance");
                    System.out.println("Balance fetched: ₹" + balance);
                    request.setAttribute("balance", balance);
                } else {
                    System.out.println("No record found for this account number.");
                    request.setAttribute("balance", 0.00);
                }
            }

            request.getRequestDispatcher("balance.jsp").forward(request, response);
            System.out.println("Forwarded to balance.jsp");

        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().println("<h2 style='color:red'>Database error: " + e.getMessage() + "</h2>");
        } catch (Exception e) {
            System.out.println("General error: " + e.getMessage());
            e.printStackTrace();
            response.getWriter().println("<h2 style='color:red'>Server error: " + e.getMessage() + "</h2>");
        }
    }
}
