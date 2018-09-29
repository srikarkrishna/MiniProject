package com.capgemini.university.service;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;

public interface student_service {
	public List<ProgramsScheduled> display_scheduled_programs() throws UniversityException;
	public int insert_application(Application app) throws UniversityException;
	public String display_status(int id) throws UniversityException;
}
