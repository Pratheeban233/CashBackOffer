package com.wipro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.daoImpl.CashClaimOffer;
import com.wipro.form.CommonForm;
import com.wipro.service.LoginService;

@WebServlet("/OfferServlet")
public class OfferServlet extends HttpServlet {
	
	LoginService loginservice;
	
	CommonForm commonform = new CommonForm();
	CashClaimOffer  Impl;
	int newbalance=0;
	int oldBalance=20000;
	float offerpercentage=10;
	float tempbalance=0;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		System.out.println("entered offerServlet");
		String couponcode=req.getParameter("couponcode");
		
		commonform.setCouponcode(req.getParameter("couponcode"));
		
	//	newbalance=Impl.cashOffer(commonform.getBalance(), couponcode);
		{
			System.out.println("tempBalance is "+ tempbalance);
			System.out.println("oldbalance is:"+oldBalance);
		
			
		//	int newbalance=oldBalance+((offerpercentage/100)*oldBalance);
			
			newbalance = (int) ((offerpercentage/100)*oldBalance)+oldBalance;
			System.out.println("tempBalance is "+ tempbalance);

			//newbalance= (int) (tempbalance);
			System.out.println("newBalance is "+ newbalance);

			
			HttpSession session= req.getSession();
			session.setAttribute("newbalance", newbalance);
			session.setAttribute("offerpercentage", offerpercentage);
			
			System.out.println("newBalance is "+ newbalance);
			res.sendRedirect("claim.jsp");
		}
	
	}

}
