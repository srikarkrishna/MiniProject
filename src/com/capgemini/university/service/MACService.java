package com.capgemini.university.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.IMacDAO;
import com.capgemini.university.dao.MacDAO;
import com.capgemini.university.exception.UniversityException;

public class MACService implements IMACService {
	
   IMacDAO macDao = null;
	   
	public boolean checkLogin(Users userBean) throws UniversityException {
		 macDao = new MacDAO(); 
		boolean check = macDao.checkLoginDao(userBean);
		return check;
	}

	@Override
	public List<Application> displayApplication(String program_name) throws UniversityException {
		macDao =new MacDAO();
		List<Application> app=new ArrayList<Application>();
		app=macDao.displayApplicationDao(program_name);
		return app;
	}

	@Override
	public List<ProgramsScheduled> displaySchedules() throws UniversityException {
		macDao = new MacDAO(); 
		List<ProgramsScheduled> programsScheduledList=null;
		 programsScheduledList = macDao.displaySchedulesDao();
		return programsScheduledList;
	}

	@Override
	public int updateInteviewDate(int app_id, String interviewDate) throws UniversityException {
		macDao =new MacDAO();
		int queryResult = macDao.updateInterviewDateDao(app_id,interviewDate);
		return queryResult;
	}

	@Override
	public int updateRejectStatus(int app_id) throws UniversityException {
		macDao =new MacDAO();
		int queryResult = macDao.updateRejectStatus(app_id);
		return queryResult;
	}

	@Override
	public List<Application> displayNewApplications() throws UniversityException {
		macDao =new MacDAO();
		List<Application> app=new ArrayList<Application>();
		app=macDao.displayNewApplications();
		return app;
	}

	@Override
	public List<Application> displayApprovedApplications() throws UniversityException {
		macDao =new MacDAO();
		List<Application> app=new ArrayList<Application>();
		app=macDao.displayApprovedApplications();
		return app;
	}

	@Override
	public int updateConfirmStatus(int app_id) throws UniversityException {
		macDao =new MacDAO();
		int queryResult = macDao.updateConfirmStatus(app_id);
		return queryResult;
	}

	@Override
	public void validateRequest(String interviewDate) throws ParseException,UniversityException {
		List<String> validationErrors = new ArrayList<String>();

		// Validating donor name
		if (!(isValidDate(interviewDate))) {
			validationErrors.add("\n Program Id should be of at least 3 alphabets not more than 5 characters long!! \n");
		}
		
		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");

	}

	private boolean isValidDate(String interviewDate) throws ParseException {
		
		
		Date current = new Date();
	    String myFormatString = "dd-MM-yyyy";
	    SimpleDateFormat df = new SimpleDateFormat(myFormatString);
	    Date givenDate = df.parse(interviewDate);
	    Long l = givenDate.getTime();
	    //create date object
	    Date next = new Date(l);
	    //compare both dates
	    if(next.after(current) || (next.equals(current))){
	        return true;
	    } else {

	      return false;
	    }
	}

}
