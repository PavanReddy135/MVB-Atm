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
import java.sql.SQLException;

@WebServlet("/pinchange")
public class PinChange extends HttpServlet {
	
	private static final String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user="pavan";
	private static final String pword="pavan123";
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		   PrintWriter out = response.getWriter();
	        response.setContentType("text/html");
	        
		HttpSession session =request.getSession(false);
		String cardnumber=(String) session.getAttribute("cardnumber");
		
		String oldpinentered=request.getParameter("oldpin");
		String newpin=request.getParameter("newpin");
		
		try(Connection con= DriverManager.getConnection(url,user,pword)){
			
			String fquery="SELECT PIN FROM MYVILLAGEBANKATM WHERE ATMCARDNUMBER=?";
			PreparedStatement pstmt1=con.prepareStatement(fquery);
			pstmt1.setString(1, cardnumber);
			ResultSet rs=pstmt1.executeQuery();
			if(rs.next()) {
				
				String oldpin=rs.getString("pin");
				if (oldpinentered != null && oldpinentered.equals(oldpin) && newpin != null) {
					String updatequery = "UPDATE MYVILLAGEBANKATM SET PIN=? WHERE ATMCARDNUMBER=?";
					PreparedStatement pstmt2=con.prepareStatement(updatequery);
					pstmt2.setString(1, newpin);
				    pstmt2.setString(2, cardnumber);
				    int rowsUpdated = pstmt2.executeUpdate();

                    if (rowsUpdated > 0) {
                    	out.println("<html><body bgcolor='#002b45' style='color:white; text-align:center;'>");
                    	out.println("<h2>PIN updated successfully!</h2>");
                    	out.println("<a href='atmindex.jsp' style='color:lightblue;'>Back to Menu</a>");
                    	out.println("</body></html>");

                    } else {
                        out.println("<h2>Failed to update PIN.</h2>");
                    }
                } else {
                    out.println("<h2>Incorrect old PIN or new PIN is empty.</h2>");
                }

            } else {
                out.println("<h2>ATM card not found.</h2>");
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    out.println("<h2 style='color:red;'>Database error: " + e.getMessage() + "</h2>");
		}
		
		
		
	}

}
