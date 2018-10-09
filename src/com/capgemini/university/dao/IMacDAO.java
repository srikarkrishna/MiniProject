package com.capgemini.university.dao;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface IMacDAO {

	public boolean checkLoginDao(Users userBean) throws UniversityException;

	public List<ProgramsScheduled> displaySchedulesDao() throws UniversityException;

	public List<Application> displayApplicationDao(String program_name) throws UniversityException;

	public int updateInterviewDateDao(int app_id, String interviewDate) throws UniversityException;

	public List<Application> displayNewApplications() throws UniversityException;

	public int updateRejectStatus(int app_id) throws UniversityException;

	public List<Application> displayApprovedApplications() throws UniversityException;

	public int updateConfirmStatus(int app_id) throws UniversityException;

}
