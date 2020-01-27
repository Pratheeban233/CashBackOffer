package com.wipro.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.daoImpl.LoginDaoImpl;
import com.wipro.form.CommonForm;
import com.wipro.service.LoginService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	CommonForm commonform=new CommonForm();
	LoginService loginService=new LoginService();
	LoginDaoImpl logindaoimpl = new LoginDaoImpl();
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String customerid=req.getParameter("customerid");
		String password= req.getParameter("password");
		System.out.println(customerid);
		System.out.println(password);
		
		
		commonform.setCustomerid(req.getParameter("customerid"));
		commonform.setCustomerid(req.getParameter("password"));
		commonform.setBalance(20000);
		
		
		if(loginService.customervalidation(customerid, password)	) 
		//if(customerid.equalsIgnoreCase("prathi") && password.equalsIgnoreCase("abiya"))
		//if(logindaoimpl.validateExistCustomer(customerid, password)	) 
		  {
			System.out.println("after password validation");
			HttpSession session= req.getSession();
			session.setAttribute("balance", commonform.getBalance());
			res.sendRedirect("offerpage.jsp");
		  }
		  else
		  {
			  res.sendRedirect("index.jsp");
		  }
		
	}

}
