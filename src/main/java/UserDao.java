import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class UserDao 
{
	
	public static Connection getconnect()
	{
		Connection con=null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servletcrud","root",""); 
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
	}
	public static int savedata(Model m)
	{
		Connection con=UserDao.getconnect();
		int status=0;
		try 
		{
			String sql="insert into person(name,email,password) values (?,?,?)";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, m.getName());
			ps.setString(2, m.getEmail());
			ps.setString(3, m.getPass());
			status=ps.executeUpdate();
		
			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public static List<Model>viewdata()
	{
		List<Model>list = new ArrayList<Model>();
		
		Connection con=UserDao.getconnect();
		String sql="select * from person";
		try 
		{
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(sql);
			ResultSet set=ps.executeQuery();
			while(set.next())
			{
				int id=set.getInt(1);
				String name=set.getString(2);
				String email=set.getString(3);
				String password=set.getString(4);
				
				Model m =new Model();
				m.setId(id);
				m.setName(name);
				m.setEmail(email);
				m.setPass(password);
				list.add(m);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return list;
	}
	
	public static int deletedata(int id){
		int status=0;
		try{
			Connection con=getconnect();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("delete from person where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
	public static Model getemployeebyid(int id)
	{
		
		Model m =new Model();
		
			Connection con =UserDao.getconnect();
			
			try 
			{
				PreparedStatement ps =(PreparedStatement) con.prepareStatement("select * from person where id=?");
				ps.setInt(1,id);
				ResultSet set=ps.executeQuery();
				
				if(set.next())
				{
					
					m.setId(set.getInt(1));
					m.setName(set.getString(2));
					m.setEmail(set.getString(3));
					m.setPass(set.getString(4));
					
				}
				
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		return m;
		
	}
	
	public static int updatedata(Model m)
	{
		Connection con=UserDao.getconnect();
		
		int status=0;
		
			try 
			{
				PreparedStatement ps =(PreparedStatement) con.prepareStatement("update person set name=?,email=?,password=? where id=?");
			
				ps.setString(1,m.getName());
				ps.setString(2,m.getEmail());
				ps.setString(3,m.getPass());
				ps.setInt(4, m.getId());
				
				status=ps.executeUpdate();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return status;
	}
	
	
	
}
