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
	LoginDaoImpl impl=new LoginDaoImpl();
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String customerid=req.getParameter("customerid");
		String password= req.getParameter("password");
		System.out.println(customerid);
		System.out.println(password);
		
		commonform.setCustomerid("customerid");
		commonform.setCustomerid("password");
				
		
		commonform = impl.validateExistCustomer(commonform,customerid, password);
		if(commonform.isFlag()==true) 
		//if(customerid.equalsIgnoreCase("prathi") && password.equalsIgnoreCase("abiya"))
		//if(loginService.customervalidation(customerid, password)	) 
		  {
			HttpSession session= req.getSession();
			session.setAttribute("balance", commonform.getBalance());
			session.setAttribute("custid", customerid);
			res.sendRedirect("offerpage.jsp");
		  }
		  else
		  {
			  res.sendRedirect("invalidlogin.jsp");
		  }
		
		
		
	}

}
