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
import com.capgemini.university.dao.IStudentDAO;
import com.capgemini.university.dao.StudentDAO;
import com.capgemini.university.exception.UniversityException;

public class StudentService implements IStudentService {
	 IStudentDAO studentDao = null;
	

	@Override
	public List<ProgramsScheduled> displayScheduledPrograms() throws UniversityException {
		studentDao = new StudentDAO(); 
		List<ProgramsScheduled> programsScheduledList=null;
		 programsScheduledList = studentDao.displaySchedulesDao();
		return programsScheduledList;
	}

	@Override
	public int insertApplication(Application applicationBean) throws UniversityException {
		studentDao=new StudentDAO();
		int queryResult= studentDao.insertApplicationDao(applicationBean);
		return queryResult;
	}

	@Override
	public String displayStatus(int id) throws UniversityException {
		studentDao=new StudentDAO();
		String statusResult = studentDao.displayStatusDao(id);
		return statusResult;
	}
	
	public void validateRequest(Application applicationBean) throws UniversityException, ParseException {
		List<String> validationErrors = new ArrayList<String>();
		
		if (!(isValidName(applicationBean.getFullName()))) {
			validationErrors
					.add("\n Name should be of at least 3 alphabets not more than 20 characters long!! \n");
		}
		// Validating address
		if (!(isValidDate(applicationBean.getDateOfBirth()))) {
			validationErrors
					.add("\n DateOfBirth should be should be in proper format(dd-mm-yyyy & age under 30 years)!!\n");
		}
		// Validating Phone Number
		if (!(isValidQualification(applicationBean.getHighestQualification()))) {
			validationErrors.add("\n Qualification should be of at least 3 alphabets not more than 10 characters long!! \n");
		}

		if (!(isValidMarks(applicationBean.getMarksObtained()))) {
			validationErrors.add("\n Marks should be a number less than or equal to 100!! \n");
		}
		
		if (!(isValidGoals(applicationBean.getGoals()))) {
			validationErrors.add("\n Goals should be of at least 5 alphabets not more than 20 characters long!! \n");
		}
		
		if (!(isValidEmail(applicationBean.getEmailId()))) {
			validationErrors.add("\n EmailId should be in standard email format not more than 20 charecters long!! \n");
		}
		
		if (!(isValidSchProgramId(applicationBean.getScheduledProgramId()))) {
			validationErrors.add("\n Program Id should be of at least 3 alphabets not more than 5 characters long!! \n");		}

		
		if (!validationErrors.isEmpty())
			throw new UniversityException(validationErrors + "");
		
	}

	private boolean isValidSchProgramId(String scheduledProgramId) {
		Pattern namePattern = Pattern.compile("^[A-Za-z0-9]{1,5}$");
		Matcher nameMatcher = namePattern.matcher(scheduledProgramId);
		return nameMatcher.matches();
	}

	private boolean isValidEmail(String emailId) {
		Pattern emailPattern = Pattern.compile(
				"^([A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}){1,20}$",
				Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = emailPattern.matcher(emailId);
		return emailMatcher.matches();
	}

	private boolean isValidGoals(String goals) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{5,20}$");
		Matcher nameMatcher = namePattern.matcher(goals);
		return nameMatcher.matches();
	}

	private boolean isValidMarks(int marksObtained) {
		String marks = Integer.toString(marksObtained);
		Pattern namePattern = Pattern.compile("^[1-9][0-9]?$|^100$");
		Matcher nameMatcher = namePattern.matcher(marks);
		return nameMatcher.matches();
	}

	private boolean isValidQualification(String highestQualification) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,10}$");
		Matcher nameMatcher = namePattern.matcher(highestQualification);
		return nameMatcher.matches();
	}

	private boolean isValidDate(String dateOfBirth) throws ParseException {
		

		String year = dateOfBirth.substring(6, 10);
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
		Matcher nameMatcher = namePattern.matcher(dateOfBirth);
		int years = 0;
	      int months = 0;
	      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	      Date birthDate = sdf.parse(dateOfBirth); 
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
	 
	      //Calculate the days
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
	     if(years>15 && years<30 && nameMatcher.matches())
	     {
	    	 return true;
	     }
	     else return false;
		
		
		
		
	}

	private boolean isValidName(String fullName) {
		Pattern namePattern = Pattern.compile("^[A-Za-z ]{3,20}$");
		Matcher nameMatcher = namePattern.matcher(fullName);
		return nameMatcher.matches();
	}


}
