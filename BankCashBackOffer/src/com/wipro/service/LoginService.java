package com.wipro.service;

import com.wipro.dao.LoginDao;
import com.wipro.form.CommonForm;

public class LoginService {
	

	LoginDao loginDao;
	
	public  CommonForm customervalidation(CommonForm commonForm,String customerid,String password)
	{
		System.out.println("Inside login service 1");
		return loginDao.validateExistCustomer(commonForm,customerid, password);
	}
	
	
	
	public int fetchBalance(CommonForm commonform,String customerid)
	{
		return loginDao.fetchCustomerBalance(commonform, customerid);
	}
	
	

}
