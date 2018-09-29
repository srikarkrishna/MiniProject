package com.capgemini.university.service;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.dao.student_dao;
import com.capgemini.university.dao.student_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class student_service_impl implements student_service{
	student_dao student_dao_obj=null;

	@Override
	public List<ProgramsScheduled> display_scheduled_programs() throws UniversityException {
		student_dao_obj=new student_dao_impl();
		return student_dao_obj.display_scheduled_programs_dao();
	}

	@Override
	public int insert_application(Application app) throws UniversityException {
		student_dao_obj=new student_dao_impl();
		return student_dao_obj.insert_application_dao(app);
	}

	@Override
	public String display_status(int id) throws UniversityException {
		student_dao_obj=new student_dao_impl();
		return student_dao_obj.display_status_dao(id);
	}
	
}
