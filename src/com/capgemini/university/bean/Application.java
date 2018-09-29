package com.capgemini.university.bean;

import java.sql.Date;

public class Application {

	private String applicationId;
	private String fullName;
	private String dateOfBirth;
	private String highestQualification;
	private int marksObtained;
	private String goals;
	private String emailId;
	private String scheduledProgramId;
	private String status;
	private Date Date_Of_Interview;
	
	
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicantId(String applicantId) {
		this.applicationId = applicantId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public int getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getScheduledProgramId() {
		return scheduledProgramId;
	}
	public void setScheduledProgramId(String scheduledProgramId) {
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate_Of_Interview() {
		return Date_Of_Interview;
	}
	public void setDate_Of_Interview(Date date_Of_Interview) {
		Date_Of_Interview = date_Of_Interview;
	}
}
