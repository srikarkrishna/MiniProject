package com.capgemini.university.dao;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface IAdminDAO {
	public boolean checkLoginDao(Users userBean) throws UniversityException;
	public int addProgramDao(ProgramsOffered program) throws UniversityException;
	public int addScheduleDao(ProgramsScheduled program) throws UniversityException;
	public List<ProgramsOffered> displayProgramsDao() throws UniversityException;
	public int deleteProgramDao(String program_name) throws UniversityException;
	public List<ProgramsScheduled> displaySchedulesDao() throws UniversityException;
	public int deleteScheduleDao(String schedule_id) throws UniversityException;
	public List<ProgramsScheduled> scheduleByTimeIntervalDao(String start,String end) throws UniversityException;
	public List<Application> scheduleApplicantStatus(String schedule_id) throws UniversityException;
}
