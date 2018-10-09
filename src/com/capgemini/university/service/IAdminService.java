package com.capgemini.university.service;
import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface IAdminService {
	public boolean checkLogin(Users userBean) throws UniversityException;
	public int addProgram(ProgramsOffered program) throws UniversityException;
	public int addSchedule(ProgramsScheduled program) throws UniversityException;
	public List<ProgramsOffered> displayPrograms() throws UniversityException;
	public int deleteProgram(String program_name) throws UniversityException;
	public List<ProgramsScheduled> displaySchedules() throws UniversityException;
	public int deleteSchedule(String schedule_id) throws UniversityException;
	public List<ProgramsScheduled> scheduleByTimeInterval (String start,String end) throws UniversityException;
	public List<Application> scheduleApplicantStatus(String schedule_id) throws UniversityException;
}
