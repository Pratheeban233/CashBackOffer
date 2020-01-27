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
	
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "SYSTEM";
	String pass = "Prathi#123";
	Connection con = null;
	PreparedStatement ps = null;

	public CommonForm validateExistCustomer(CommonForm commonForm,String customerid, String password)  
	{
		commonForm.setFlag(false);		
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("COnnecting to DB");
				con = DriverManager.getConnection(url, user, pass);
				
				System.out.println("DB Connected");
				System.out.println("test 1");
				String qry = "select balance from customer where customer_id=? and password=? ";
				ps=con.prepareStatement(qry);
				ps.setString(1, customerid);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				System.out.println("test 2");

				if (rs.next()) {
					commonForm.setFlag(true);
					int balance = rs.getInt(1);
					System.out.println("Balance" + balance);
					commonForm.setBalance(balance);
					System.out.println(commonForm.getBalance());
				}
				else
				{
					commonForm.setFlag(false);
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
				
			}
			finally
			{
				try {
					con.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return commonForm;
	}
	


	@Override
	public int fetchCustomerBalance(CommonForm commonform,String customerid) {
		
		int balance=0;
		try {
			System.out.println("offer servlet balance DB Connecting");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con= DriverManager.getConnection(url, user, pass);
			System.out.println("DB Connected");
			String query= "select balance from customer where customer_id=?";
			ps=con.prepareStatement(query);
			ps.setString(1, customerid);
			ResultSet rs=ps.executeQuery();
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
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return balance;
	}

	
}



