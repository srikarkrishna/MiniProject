package com.capgemini.university.dao;

import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface University_dao {
	public boolean check_login_dao(Users userBean) throws UniversityException;
	public void add_program_dao(ProgramsOffered program) throws UniversityException;
	public void add_schedule_dao(ProgramsScheduled program) throws UniversityException;
	public void display_programs_dao() throws UniversityException;
	public void delete_program_dao(String program_name) throws UniversityException;
	public void display_schedules_dao() throws UniversityException;
	public void delete_schedule_dao(int schedule_id) throws UniversityException;
}
