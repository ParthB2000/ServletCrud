import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		
		String name =req.getParameter("full-name");
		String email =req.getParameter("your-email");
		String pass =req.getParameter("password");
		String cpass =req.getParameter("comfirm-password");
		
		if(pass.equals(cpass))
		{
			Model m =new Model();
			m.setName(name);
			m.setEmail(email);
			m.setPass(pass);
			
			int status=UserDao.savedata(m);
			if(status>0)
			{
				//out.print("Inserted");
				resp.sendRedirect("ViewServlet");
			}
			else
			{
				out.print("Fail");
			}
		}
		else
		{
			out.print("Password and Confirm Password Must be Same");
		}
		
		
	}
}
