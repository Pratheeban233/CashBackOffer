package com.wipro.service;

import com.wipro.dao.LoginDao;
import com.wipro.form.CommonForm;

public class LoginService {
	

	LoginDao loginDao;
	
	public  boolean customervalidation(String customerid,String password)
	{
		System.out.println("Inside login service 1");
		return loginDao.validateExistCustomer(customerid, password);
	}
	
	public int fetchBalance(CommonForm commonform,String customerid)
	{
		return loginDao.fetchCustomerBalance(commonform, customerid);
	}
	
	

}
