package com.capgemini.university.dao;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface MAC_dao {
	public boolean check_login(Users userBean) throws UniversityException;
	public List<Application> display_application_dao(String program_name) throws UniversityException;
	public int update_interview_date_dao(int app_id) throws UniversityException;
	public int delete_application_dao(int app_id) throws UniversityException;
	public int update_status_dao(int app_id) throws UniversityException;
}
