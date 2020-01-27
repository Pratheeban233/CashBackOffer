package com.wipro.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.dao.LoginDao;
import com.wipro.form.CommonForm;

public class LoginDaoImpl implements LoginDao {
	@Autowired
	CommonForm commonform;
	
	

	public boolean validateExistCustomer(String customerid, String password)  {
		
		boolean flag=false;
		
		
			try {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "SYSTEM";
				String pass = "Prathi#123";
				System.out.println("test 0");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, user, pass);
				System.out.println("test 1");
				String qry = "select balance from customer where customer_id=? and password=? ";
				PreparedStatement ps=con.prepareStatement(qry);
				ps.setString(1, customerid);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery(qry);
				System.out.println("test 2");

				if (rs.next()) {
					flag= true;
					commonform.setBalance(rs.getInt(1));
				}
				else
				{
					flag=false;
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				
			}
			finally
			{
				/*try {
					con.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		
		
		
		return flag;
	
	}

	@Override
	public int fetchCustomerBalance(CommonForm commonform,String customerid) {
		
		int balance=0;
		Connection con=null;
		PreparedStatement ps1=null;
		
		try {
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "SYSTEM";
			String pass = "Prathi#123";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection(url, user, pass);
			
			String query= "select balance from customer where customer_id=?";
			ps1=con.prepareStatement(query);
			ps1.setString(1, commonform.getCustomerid());
			ResultSet rs=ps1.executeQuery();
			while(rs.next())
			{
				commonform.setBalance(rs.getInt(1));
				balance =rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
				ps1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return balance;
	}

	
}



