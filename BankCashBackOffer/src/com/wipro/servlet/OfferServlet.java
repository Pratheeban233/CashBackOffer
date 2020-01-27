package com.wipro.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wipro.daoImpl.CashClaimDaoImpl;
import com.wipro.daoImpl.LoginDaoImpl;
import com.wipro.form.CommonForm;
import com.wipro.service.CashClaimService;
import com.wipro.service.LoginService;

@WebServlet("/OfferServlet")
public class OfferServlet extends HttpServlet {
	
	CashClaimService cashClaimService;
	CashClaimDaoImpl cashClaimDaoImpl = new CashClaimDaoImpl();
	LoginDaoImpl loginDaoImpl =  new LoginDaoImpl();
	LoginService loginservice;
	int balance = 0;
	
	CommonForm commonform = new CommonForm();
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		System.out.println("entered offerServlet");
		
		String couponcode=req.getParameter("couponcode");
		
		commonform.setCouponcode(couponcode);
		
		commonform = cashClaimDaoImpl.offerPercentage(commonform,couponcode);
		
		if(commonform.isFlag()==true )
		{
		
		int OfferPercen = (int)(commonform.getOfferpercentage());
		
		HttpSession session= req.getSession();
		session.setAttribute("offerpercentage", OfferPercen);
		System.out.println("percentage"+ commonform.getOfferpercentage());
		
		String cus_id = (String) session.getAttribute("custid");
		
		int balance= loginDaoImpl.fetchCustomerBalance(commonform, cus_id);
		
		System.out.println("inOfferservelt balance is "+balance);
	
	   balance = (int) ((commonform.getOfferpercentage()/100)*balance)+balance;
	   System.out.println("inOfferservelt updated balance is "+balance);
	   
	   session.setAttribute("newbalance", balance);
	   res.sendRedirect("claim.jsp");
		}
		
		else
		{
			res.sendRedirect("invalidcoupon.jsp");
		}
	

	
		/*{
			System.out.println("tempBalance is "+ tempbalance);
			System.out.println("oldbalance is:"+oldBalance);
			
			newbalance = (int) ((offerpercentage/100)*oldBalance)+oldBalance;
			System.out.println("tempBalance is "+ tempbalance);

			System.out.println("newBalance is "+ newbalance);
			
			
			
			System.out.println("newBalance is "+ newbalance);
			res.sendRedirect("claim.jsp");
		}*/
	
	}

}
