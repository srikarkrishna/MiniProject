package com.capgemini.university.dao;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;

public interface student_dao {
	public List<ProgramsScheduled> display_scheduled_programs_dao() throws UniversityException;
	public int insert_application_dao(Application app) throws UniversityException;
	public String display_status_dao(int id) throws UniversityException;
}
