package com.capgemini.university.bean;


public class ProgramsScheduled {
	

	private String scheduledProgramId;
	private String programName;
	private String location;
	private String startDate;
	private String endDate;
	private int sessionsPerWeek;
	
	
	public String getScheduledProgramId() {
		return scheduledProgramId;
	}
	public void setScheduledProgramId(String scheduledProgramId) {
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getSessionsPerWeek() {
		return sessionsPerWeek;
	}
	public void setSessionsPerWeek(int sessionsPerWeek) {
		this.sessionsPerWeek = sessionsPerWeek;
	}

	public String toString()
	{
		int n=0;
		StringBuilder sb = new StringBuilder();
		sb.append( scheduledProgramId);
		n=17-scheduledProgramId.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( programName);
		n=16-programName.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( location);
		n=16-location.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( startDate.substring(0, 10));
		n=6;
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( endDate.substring(0, 10));
		n=12;
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( sessionsPerWeek);
		return sb.toString();
		
	}
	
}
