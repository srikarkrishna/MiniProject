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
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.AdminDAO;
import com.capgemini.university.dao.IAdminDAO;
import com.capgemini.university.exception.UniversityException;

public class AdminService implements IAdminService{

   IAdminDAO adminDao = null;
   
	@Override
	public boolean checkLogin(Users userBean) throws UniversityException {
		 adminDao = new AdminDAO(); 
		boolean check = adminDao.checkLoginDao(userBean);
		return check;
	}

	@Override
	public int addProgram(ProgramsOffered program) throws UniversityException {
		 adminDao = new AdminDAO(); 
		int queryResult= adminDao.addProgramDao(program);
		return queryResult;
	}

	@Override
	public int deleteProgram(String program_name) throws UniversityException {
		 adminDao = new AdminDAO(); 
		int queryResult= adminDao.deleteProgramDao(program_name);
		return queryResult;
	}

	@Override
	public List<ProgramsOffered> displayPrograms() throws UniversityException {
		 adminDao = new AdminDAO(); 
		 List<ProgramsOffered> programsOfferedList=null;
		 programsOfferedList= adminDao.displayProgramsDao();
		return programsOfferedList;
	}
	
	@Override
	public List<ProgramsScheduled> displaySchedules() throws UniversityException {
		 adminDao = new AdminDAO(); 
		List<ProgramsScheduled> programsScheduledList=null;
		 programsScheduledList = adminDao.displaySchedulesDao();
		return programsScheduledList;
	}

	@Override
	public int addSchedule(ProgramsScheduled program) throws UniversityException {
		 adminDao = new AdminDAO(); 
		int queryResult= adminDao.addScheduleDao(program);
		return queryResult;
	}
	
	@Override
	public int deleteSchedule(String schedule_id) throws UniversityException {
		 adminDao = new AdminDAO(); 
		int queryResult= adminDao.deleteScheduleDao(schedule_id);
		return queryResult;
	}

	@Override
	public List<ProgramsScheduled> scheduleByTimeInterval(String start, String end) throws UniversityException {
		 adminDao = new AdminDAO(); 
			List<ProgramsScheduled> programsScheduledList=null;
			 programsScheduledList = adminDao.scheduleByTimeIntervalDao(start, end);
			 return programsScheduledList;
	}

	@Override
	public List<Application> scheduleApplicantStatus(String schedule_id) throws UniversityException {
		 adminDao = new AdminDAO(); 
		 List<Application> applicationList=null;
		 applicationList = adminDao.scheduleApplicantStatus(schedule_id);
		return applicationList;
	}


	public void validateRequest(ProgramsOffered program_offered) throws UniversityException {
		List<String> validationErrors = new ArrayList<String>();

		// Validating donor name
		if (!(isValidProgramName(program_offered.getProgramName()))) {
			validationErrors
					.add("\n Program Name should be of at least 3 alphabets not more than 5 characters long!! \n");
		}
		// Validating address
		if (!(isValidDescription(program_offered.getDescription()))) {
			validationErrors
					.add("\n Description should be of at least 3 alphabets not more than 20 characters long!!\n");
		}
		// Validating Phone Number
		if (!(isValidAppEligibility(program_offered.getApplicantEligibility()))) {
			validationErrors.add("\n Applicaiton Eligibility should be of at least 3 alphabets not more than 40 characters long!! \n");
		}

		if (!(isValidDuration(program_offered.getDuration()))) {
			validationErrors.add("\n Duration should be a number in years not more than one digit less than 6!! \n");
		}
		
		if (!(isValidDegreeCertificate(program_offered.getDegreeCertificateOffered()))) {
			validationErrors.add("\n Degree Certificate Offered should be of at least 3 alphabets not more than 10 characters long!! \n");
		}

		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");
		
	}
	
	public void validateRequest(ProgramsScheduled program_scheduled) throws UniversityException, ParseException {
		
		List<String> validationErrors = new ArrayList<String>();

		// Validating donor name
		if (!(isValidProgramId(program_scheduled.getScheduledProgramId()))) {
			validationErrors.add("\n Program Id should be of at least 3 alphabets not more than 5 characters long!! \n");
		}
		// Validating address
		if (!(isValidProgramName(program_scheduled.getProgramName()))) {
			validationErrors.add("\n Program Name should be of at least 3 alphabets not more than 20 characters long!!\n");
		}
		// Validating Phone Number
		if (!(isValidLocation(program_scheduled.getLocation()))) {
			validationErrors.add("\n Location should be of at least 3 alphabets not more than 40 characters long!! \n");
		}

		if (!(isValidDate(program_scheduled.getStartDate()))) {
			validationErrors.add("\n Date should be in proper format(dd-mm-yyyy) and a future date \n");
		}
		
		if (!(isValidDate(program_scheduled.getEndDate()))) {
			validationErrors.add("\n Date should be in proper format(dd-mm-yyyy) and a future date \n");
		}
		
		if (!(isValidSessions(program_scheduled.getSessionsPerWeek()))) {
			validationErrors.add("\n Sessions per week must have 2 digits(Ex: 03,15,00) and should be less than 20 \n");
		}

		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");
	}

	

	public void validateRequest(Users usersBean) throws UniversityException {
		
		List<String> validationErrors = new ArrayList<String>();
		
		if (!(isValidLoginId(usersBean.getLoginId()))) {
			validationErrors.add("\n Login ID should be of at least 3 alphabets not more than 5 characters long!! \n");
		}
		// Validating address
		if (!(isValidPassword(usersBean.getPassword()))) {
			validationErrors
					.add("\n Password should be of at least 6 characters(A-Z,a-z,0-9,.,_,#,%,^,*,?,@,-) not more than 10 characters long!!\n");
		}		
		
		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");
	}

	private boolean isValidPassword(String password) {
		Pattern namePattern = Pattern.compile("^[A-Za-z0-9._#%^*?@-]{6,10}$");
		Matcher nameMatcher = namePattern.matcher(password);
		return nameMatcher.matches();
	}

	private boolean isValidLoginId(String loginId) {
		Pattern namePattern = Pattern.compile("^[A-Za-z]{1,5}$");
		Matcher nameMatcher = namePattern.matcher(loginId);
		return nameMatcher.matches();
	}

	private boolean isValidDegreeCertificate(String degreeCertificateOffered) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,10}$");
		Matcher nameMatcher = namePattern.matcher(degreeCertificateOffered);
		return nameMatcher.matches();
	}

	private boolean isValidDuration(int duration) {
		String dur = Integer.toString(duration);
		Pattern namePattern = Pattern.compile("^[1-6]{1}$");
		Matcher nameMatcher = namePattern.matcher(dur);
		return nameMatcher.matches();
	}

	private boolean isValidAppEligibility(String applicantEligibility) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,40}$");
		Matcher nameMatcher = namePattern.matcher(applicantEligibility);
		return nameMatcher.matches();
	}

	private boolean isValidDescription(String description) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,20}$");
		Matcher nameMatcher = namePattern.matcher(description);
		return nameMatcher.matches();
	}

	private boolean isValidProgramName(String programName) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,5}$");
		Matcher nameMatcher = namePattern.matcher(programName);
		return nameMatcher.matches();
	}

	private boolean isValidSessions(int sessionsPerWeek) {
		if(sessionsPerWeek>0)
		{
			return true;
		}
		else 
			return false;
	}

	private boolean isValidDate(String Date) throws ParseException {
		
		String year = Date.substring(6, 10);
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
		Matcher nameMatcher = namePattern.matcher(Date);
		int years = 0;
	      int months = 0;
	      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	      Date birthDate = sdf.parse(Date); 
	      
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

	private boolean isValidLocation(String location) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,10}$");
		Matcher nameMatcher = namePattern.matcher(location);
		return nameMatcher.matches();
	}

	private boolean isValidProgramId(String scheduledProgramId) {
		Pattern namePattern = Pattern.compile("^[A-Za-z0-9]{1,5}$");
		Matcher nameMatcher = namePattern.matcher(scheduledProgramId);
		return nameMatcher.matches();
	}

	public void validateRequest(String start_interval, String end_interval) throws ParseException,UniversityException {
	
			List<String> validationErrors = new ArrayList<String>();

			// Validating donor name
			if (!(isValidDate(start_interval))) {
				validationErrors.add("\n Start Date should be in proper format(dd-mm-yyyy) and a future date  \n");
			}
			if (!(isValidDate(end_interval))) {
				validationErrors.add("\n End Date should be in proper format(dd-mm-yyyy) and a future date  \n");
			}
			
			if (!validationErrors.isEmpty())
				throw new UniversityException(validationErrors + "");	
		
	}


}
