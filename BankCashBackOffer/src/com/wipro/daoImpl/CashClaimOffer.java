package com.wipro.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.dao.CashClaimDao;
import com.wipro.form.CommonForm;

public class CashClaimOffer implements CashClaimDao{
	@Autowired
	CommonForm commonform;
	@Override
	public int cashOffer(int balance,String couponcode) {
		
		int oldBalance;
		float offerpercentage;
		int newBalance;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SYSTEM";
		String pass = "Prathi#123";

	
			try {
				System.out.println("entered cashoffer 1");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection(url, user, pass);
				
				String qry="select offerpercentage from coupon where coupon_code=?";
				PreparedStatement ps=con.prepareStatement(qry);
				ps.setString(1,couponcode);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					commonform.setOfferpercentage(rs.getInt(1));
					
					String qry1 = "select balance from customer where customer_id=? ";
					PreparedStatement ps1=con.prepareStatement(qry1);
					ps1.setString(1, commonform.getCustomerid());
					ResultSet rs1=ps1.executeQuery();
					while(rs1.next())
					{
						commonform.setBalance(rs1.getInt(1));
						
						 oldBalance=commonform.getBalance();
						 offerpercentage=commonform.getOfferpercentage();
						 
						 newBalance=(int) (oldBalance+(offerpercentage/100*oldBalance));
						
						 commonform.setBalance(newBalance);
					}
				}
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

		return 0;
	}

}
