package com.wipro.service;

import com.wipro.daoImpl.CashClaimDaoImpl;
import com.wipro.form.CommonForm;

public class CashClaimService {
	
	
	CashClaimDaoImpl cashClaimDao;
	CommonForm commonform;
	
	public CommonForm getOfferPercentage(String couponcode)
	{
		return cashClaimDao.offerPercentage(commonform, couponcode);
	}
	

}
