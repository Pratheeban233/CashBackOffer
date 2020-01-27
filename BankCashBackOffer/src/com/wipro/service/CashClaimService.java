package com.wipro.service;


import com.wipro.dao.CashClaimDao;
import com.wipro.form.CommonForm;

public class CashClaimService 
{
	CashClaimDao cashClaimDao;
	CommonForm commonform;
	
	public int validateOffer(CommonForm commonform,String couponcode) {
		return cashClaimDao.cashOffer(commonform.getBalance(),couponcode);
	}
}
