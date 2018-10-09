package com.capgemini.university.service;

import java.text.ParseException;
import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface IMACService {

	public boolean checkLogin(Users usersBean) throws UniversityException;

	public List<Application> displayApplication(String program_name) throws UniversityException;

	public int updateInteviewDate(int app_id, String interviewDate) throws UniversityException;

	public List<ProgramsScheduled> displaySchedules() throws UniversityException;

	public int updateRejectStatus(int app_id) throws UniversityException;

	public List<Application> displayNewApplications() throws UniversityException;

	public List<Application> displayApprovedApplications() throws UniversityException;

	public int updateConfirmStatus(int app_id)  throws UniversityException;

	public void validateRequest(String interviewDate) throws ParseException, UniversityException;

}
