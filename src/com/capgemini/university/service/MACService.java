package com.capgemini.university.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			validationErrors.add("\n Interview Date should be in proper format(dd-mm-yyyy) and a future date \n");
		}
		
		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");

	}

	private boolean isValidDate(String interviewDate) throws ParseException {
		
		
		String year = interviewDate.substring(6, 10);
		int yr= Integer.parseInt(year);
		Pattern namePattern;
		if(yr%4==0)
		{
		namePattern = Pattern.compile("^(?:(?:[12][0-9]|0?[1-9])-0?2|(?:30|[12][0-9]|0?[1-9])-(?:0?[469]|11)|(?:3[01]|[12][0-9]|0?[1-9])-(?:0?[13578]|1[02]))-([0-9]{4})$");
		}
		else
		{
        namePattern = Pattern.compile("^(?:(?:1[0-9]|2[0-8]|0?[1-9])-0?2|(?:30|[12][0-9]|0?[1-9])-(?:0?[469]|11)|(?:3[01]|[12][0-9]|0?[1-9])-(?:0?[13578]|1[02]))-([0-9]{4})$");
		}
		Matcher nameMatcher = namePattern.matcher(interviewDate);
		int years = 0;
	      int months = 0;
	      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	      Date birthDate = sdf.parse(interviewDate); 
	      
	      Calendar birthDay = Calendar.getInstance();
	      birthDay.setTimeInMillis(birthDate.getTime());
	 
	      long currentTime = System.currentTimeMillis();
	      Calendar now = Calendar.getInstance();
	      now.setTimeInMillis(currentTime);
	 
	      years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
	      int currMonth = now.get(Calendar.MONTH) + 1;
	      int birthMonth = birthDay.get(Calendar.MONTH) + 1;
	 
	      months = currMonth - birthMonth;
	 
	      if (months < 0)
	      {
	         years--;
	         months = 12 - birthMonth + currMonth;
	         if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	            months--;
	      } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         years--;
	         months = 11;
	      }
	 
	      if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE)) {
		} else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
	      {
	         now.add(Calendar.MONTH, -1);
	      }
	      else
	      {
	         if (months == 12)
	         {
	            years++;
	            months = 0;
	         }
	      }
	     if(years<0 && nameMatcher.matches())
	     {
	    	 return true;
	     }
	     else return false;
	}

}
