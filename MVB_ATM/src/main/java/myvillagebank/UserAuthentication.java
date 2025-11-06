package myvillagebank;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/userauthentication")
public class UserAuthentication extends HttpServlet {
    private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String user = "pavan";
    private static final String pword = "pavan123";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardnumber = request.getParameter("cardNumber");
        String pin = request.getParameter("pin");

        try (Connection con = DriverManager.getConnection(url, user, pword)) {
            PrintWriter pw = response.getWriter();
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM MYVILLAGEBANKATM WHERE atmcardnumber=? AND pin=?");
            pstmt.setString(1, cardnumber);
            pstmt.setString(2, pin);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String accountnumber = rs.getString("accountnumber");
                String username = rs.getString("username");
                HttpSession session = request.getSession();
                session.setAttribute("accountnumber", accountnumber);
                session.setAttribute("username", username);
                session.setAttribute("cardnumber",cardnumber);
                response.sendRedirect("atmindex.jsp");
            } else {
                pw.println("<html><body bgcolor='black' text='white'><center>");
                pw.println("<h2 style='color:red;'>Invalid Card Number or PIN</h2>");
                pw.println("<a href='login.jsp' style='color:deepskyblue;'>Try Again</a>");
                pw.println("</center></body></html>");
            }

            rs.close();
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<html><body bgcolor='black' text='white'><center>");
            pw.println("<h2 style='color:red;'>Error occurred: " + e.getMessage() + "</h2>");
            pw.println("<a href='login.jsp' style='color:deepskyblue;'>Try Again</a>");
            pw.println("</center></body></html>");
        }
    }
}
