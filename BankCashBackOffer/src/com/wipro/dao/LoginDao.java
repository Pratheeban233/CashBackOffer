package com.wipro.dao;

import com.wipro.form.CommonForm;

public interface LoginDao {
		
	public  boolean validateExistCustomer(String customerid,String password) ;
	public int fetchCustomerBalance(CommonForm commonform,String customerid);

}
