package com.capgemini.university.bean;


public class ProgramsOffered {
	
	private String programName;
	private String description;
	private String applicantEligibility;
	private int duration;
	private String degreeCertificateOffered;
	
	

	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplicantEligibility() {
		return applicantEligibility;
	}
	public void setApplicantEligibility(String applicantEligibility) {
		this.applicantEligibility = applicantEligibility;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDegreeCertificateOffered() {
		return degreeCertificateOffered;
	}
	public void setDegreeCertificateOffered(String degreeCertificateOffered) {
		this.degreeCertificateOffered = degreeCertificateOffered;
	}

	public String toString()
	{
		int n=0;
		StringBuilder sb = new StringBuilder();
		sb.append( programName);
		n=17-programName.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( description);
		n=24-description.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( applicantEligibility);
		n=48-applicantEligibility.length();
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( duration+" years");
		n=9;
		while(n>0)
		{
			sb.append(" ");
			n--;
		}
		sb.append( degreeCertificateOffered);
		return sb.toString();	
	}
	
}
