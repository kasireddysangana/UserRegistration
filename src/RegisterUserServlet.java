import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.kasi.dao.DBConnect;
import com.kasi.dao.UserDAO;
import com.kasi.entity.User;
import com.kasi.entity.UserDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends GenericServlet 
{
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String address = request.getParameter("address");
		long phone = Long.parseLong(request.getParameter("phone"));
		
		User u = new User(userName,pwd);
		UserDetails ud = new UserDetails(u,firstName,lastName,address,phone);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print("<center><h1>");
		if(new UserDAO().createUser(ud))
			out.print("User Created Successfully....");
		else
			out.print("Opps Something went wrong");
		out.print("</h1></center>");
		out.print("</body></html>");
	}
}
