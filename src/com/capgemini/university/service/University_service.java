package com.capgemini.university.service;

import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface University_service {
	public boolean check_login(Users userBean) throws UniversityException;
	public void add_program(ProgramsOffered program) throws UniversityException;
	public void add_schedule(ProgramsScheduled program) throws UniversityException;
	public void display_programs() throws UniversityException;
	public void delete_program(String program_name) throws UniversityException;
	public void display_schedules() throws UniversityException;
	public void delete_schedule(int schedule_id) throws UniversityException;
}
