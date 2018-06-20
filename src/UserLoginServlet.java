import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import com.kasi.entity.User;
import com.kasi.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends GenericServlet 
{
	public void service(ServletRequest request, ServletResponse response) throws 
		ServletException, IOException
	{
		String userName = request.getParameter("uname");
		String password = request.getParameter("pwd");
		User u = new User(userName,password);
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		out.print("<html><body>");
		out.print("<center><h1>");
		boolean value = new UserDAO().checkUser(u);
		System.out.println("Value is : " + value);
		if(value) {
			out.print("Hellow welcome to Servlet Mr " + userName);
		}
		else
			out.print("Invalid User Name or Password ");
		out.print("</h1></center>");
		out.print("</body></html>");
		
	}
}
