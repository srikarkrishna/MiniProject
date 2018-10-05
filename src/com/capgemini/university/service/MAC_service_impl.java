package com.capgemini.university.service;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
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

	@Override
	public List<Application> display_application(String program_name) throws UniversityException {
		mac_dao_obj =new MAC_dao_impl();
		List<Application> app=new ArrayList<Application>();
		app=mac_dao_obj.display_application_dao(program_name);
		return app;
	}

	@Override
	public int update_inteview_date(int app_id) throws UniversityException {
		mac_dao_obj =new MAC_dao_impl();
		return mac_dao_obj.update_interview_date_dao(app_id);
	}

	@Override
	public int delete_application(int app_id) throws UniversityException {
		mac_dao_obj =new MAC_dao_impl();
		return mac_dao_obj.delete_application_dao(app_id);
	}
	
}
