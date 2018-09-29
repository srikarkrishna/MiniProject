package com.capgemini.university.service;

import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.MAC_dao;
import com.capgemini.university.dao.MAC_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class MAC_service_impl implements MAC_service{
	MAC_dao mac_dao_obj=null;

	@Override
	public boolean check_login(Users userBean) throws UniversityException {
		mac_dao_obj =new MAC_dao_impl();
		return mac_dao_obj.check_login(userBean);
	}
	
}
