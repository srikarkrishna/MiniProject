package com.capgemini.university.service;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface MAC_service {
	public boolean check_login(Users userBean) throws UniversityException;
	public List<Application> display_application(String program_name) throws UniversityException;
	public int update_inteview_date(int app_id) throws UniversityException;
	public int delete_application(int app_id) throws UniversityException;
	
}
