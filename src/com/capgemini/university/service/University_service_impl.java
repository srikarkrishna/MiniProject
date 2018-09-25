package com.capgemini.university.service;

import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.University_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class University_service_impl implements University_service{
	University_dao_impl dao_obj = new University_dao_impl(); 

	@Override
	public boolean check_login(Users userBean) throws UniversityException {
		return dao_obj.check_login_dao(userBean);
	}
	
}
