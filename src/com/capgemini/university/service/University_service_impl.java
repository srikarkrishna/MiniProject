package com.capgemini.university.service;

import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.University_dao_impl;
import com.capgemini.university.exception.UniversityException;

public class University_service_impl implements University_service{
	University_dao_impl dao_obj = new University_dao_impl(); 

	@Override
	public boolean check_login(Users userBean) throws UniversityException {
		return dao_obj.check_login_dao(userBean);
	}

	@Override
	public void add_program(ProgramsOffered program) throws UniversityException {
		dao_obj.add_program_dao(program);
	}

	@Override
	public void delete_program(String program_name) throws UniversityException {
		dao_obj.delete_program_dao(program_name);
	}

	@Override
	public void display_programs() throws UniversityException {
		dao_obj.display_programs_dao();
	}

	@Override
	public void add_schedule(ProgramsScheduled program) throws UniversityException {
		dao_obj.add_schedule_dao(program);
	}

	@Override
	public void display_schedules() throws UniversityException {
		dao_obj.display_schedules_dao();
	}

	@Override
	public void delete_schedule(int schedule_id) throws UniversityException {
		dao_obj.delete_schedule_dao(schedule_id);
	}
	
}
