package myvillagebank;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/atmdeposit")
public class ATMDeposit extends HttpServlet {

    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String user = "pavan";
    private static final String pword = "pavan123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        double amount = Double.parseDouble(request.getParameter("amount"));
        String accountnumber = (String) session.getAttribute("accountnumber");
        String username = (String) session.getAttribute("username");

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        try (Connection con = DriverManager.getConnection(url, user, pword)) {

            String fquery = "SELECT BALANCE FROM MYVILLAGEBANK WHERE ACCOUNTNUMBER=?";
            PreparedStatement pstmt = con.prepareStatement(fquery);
            pstmt.setString(1, accountnumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                double newbalance = balance + amount;

                String updatequery = "UPDATE MYVILLAGEBANK SET balance=? WHERE accountnumber=?";
                PreparedStatement pstmt2 = con.prepareStatement(updatequery);
                pstmt2.setDouble(1, newbalance);
                pstmt2.setString(2, accountnumber);

                int rows = pstmt2.executeUpdate();

                if (rows > 0) {
                    String insertTransaction = "INSERT INTO MYVILLAGEBANKTRANSACTIONS (TRANSACTION_ID, ACCOUNTNUMBER, USERNAME, TYPE, AMOUNT) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement transStmt = con.prepareStatement(insertTransaction);
                    transStmt.setString(1, "TXN" + System.currentTimeMillis());
                    transStmt.setString(2, accountnumber);
                    transStmt.setString(3, username);
                    transStmt.setString(4, "ATM DEPOSIT");
                    transStmt.setDouble(5, amount);
                    transStmt.executeUpdate();

                    pw.println("<!DOCTYPE html>");
                    pw.println("<html><head><meta charset='UTF-8'><title>Deposit Success - MyVillageBank</title>");
                    pw.println("<style>");
                    pw.println("body {margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #000000, #1a1a1a); display: flex; justify-content: center; align-items: center; height: 100vh;}");
                    pw.println(".success-box {background-color: #121212; border-radius: 20px; padding: 40px 35px; width: 420px; box-shadow: 0 0 25px rgba(0, 255, 127, 0.3); color: white; text-align: center;}");
                    pw.println(".success-box h2 {color: #32cd32; margin-bottom: 20px;}");
                    pw.println(".success-box p {font-size: 16px; color: #ccc;}");
                    pw.println(".home-link {display: inline-block; margin-top: 25px; padding: 12px 20px; background-color: #32cd32; color: black; text-decoration: none; font-weight: bold; border-radius: 8px; transition: background-color 0.3s ease;}");
                    pw.println(".home-link:hover {background-color: #43e043;}");
                    pw.println("</style></head><body>");

                    pw.println("<div class='success-box'>");
                    pw.println("<h2>Deposit Successful!</h2>");
                    pw.println("<p>New Balance: ₹" + newbalance + "</p>");
                    pw.println("<a class='home-link' href='atmindex.jsp'>&larr; Back to Home</a>");
                    pw.println("</div>");

                    pw.println("</body></html>");

                } else {
                    pw.println("<h2 style='color:red;text-align:center;'>Failed to deposit money. Try again.</h2>");
                }

            } else {
                pw.println("<h2 style='color:red;text-align:center;'>Account not found!</h2>");
                pw.println("<a href='atmindex.jsp' style='color:deepskyblue;'>Back</a>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            pw.println("<h2 style='color:red;text-align:center;'>An error occurred: " + e.getMessage() + "</h2>");
        }
    }
}
