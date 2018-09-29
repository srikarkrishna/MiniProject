package com.capgemini.university.ui;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.Participant;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.student_dao_impl;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.service.MAC_service;
import com.capgemini.university.service.MAC_service_impl;
import com.capgemini.university.service.University_service_impl;
import com.capgemini.university.service.student_service;
import com.capgemini.university.service.student_service_impl;

public class UniversityMain {
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws UniversityException, ParseException {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("resources//log4j.properties");
		Participant participantBean = null;
		ProgramsOffered programsOfferedBean = null;
		ProgramsScheduled programsScheduledBean = null;
		Application ApplicationBean=null;
		Users usersBean = null;
		
		MAC_service mac_service_obj=null;
		student_service student_obj=null;
		int option = 0;

		while(true){

			// code to show menu
			System.out.println();
			System.out.println();
			System.out.println("  University Admission System ");
			System.out.println("_______________________________\n");

			System.out.println("1. Login as Admin ");
			System.out.println("2. Login as MAC");
			System.out.println("3. Check-in as a Student");
			System.out.println("________________________________");
			System.out.println("Select an option to login as one of the above:");
			// accept option

			try {
				option = sc.nextInt();
				switch (option) {
					case 1: 
						while(usersBean == null) {
							usersBean = populateUsersBean();
						}
						
						University_service_impl university_obj=new University_service_impl();
	
					    	//Code to verify Password
						if(university_obj.check_login(usersBean)){
							//password matches
							while (true) {
	
								// code to show menu
								System.out.println();
								System.out.println();
								System.out.println("  Admin Login ");
								System.out.println("_______________________________\n");
	
								System.out.println("1. Add a new Offered Program");
								System.out.println("2. Delete a Program Offered");
								System.out.println("3. Add a new Scheduled Program");
								System.out.println("4. Delete a Program Scheduled");
								System.out.println("5. View list of Applicants for Scheduled program");
								System.out.println("6. View list of programs scheduled to commence in a give time period");
								System.out.println("________________________________");
								System.out.println("Select an option from the above:");
								// accept option
	
								try {
									String schedule_id;
									option = sc.nextInt();
									switch (option) {
										case 1: 
											while (programsOfferedBean == null) {
												programsOfferedBean = populateprogramsOfferedBean();
											}
											university_obj.add_program(programsOfferedBean);
											break;
											
										case 2:
											/* code to show available programs offered and 
											ask him to select which one he wants to delete by ID */
											university_obj.display_programs();
											sc.nextLine();
											System.out.println("Enter the program name to be deleted");
											String program_name=sc.nextLine();
											System.out.println("Enter the program name to be deleted");
											university_obj.delete_program(program_name);
											break;
											
										case 3: 
											while (programsScheduledBean == null) {
												programsScheduledBean = populateprogramsScheduledBean();
												//edit populateprogramsScheduledBean method
											}
											//code for storing program details
											
											university_obj.add_schedule(programsScheduledBean);
											break;
										case 4:
											/* code to show available programs scheduled and 
											ask him to select which one he wants to delete by ID */
											
											university_obj.display_schedules();
											sc.nextLine();
											System.out.println("Enter the schedule id to be deleted");
											schedule_id=sc.nextLine();
											
											university_obj.delete_schedule(schedule_id);
											break;
										case 5:
											/* code to view application status based on program schedule ID */
											university_obj.display_schedules();
											sc.nextLine();
											System.out.println("Enter the schedule id to view applicants status for that scheduled program ");
											schedule_id=sc.nextLine();
											
											break;
										case 6:
											sc.nextLine();
											System.out.println("Please enter the interval starting");
											String start_interval=sc.nextLine();
											System.out.println("Please enter the interval ending");
											String end_interval=sc.nextLine();
											university_obj.schedules_between_time_interval(start_interval, end_interval);
											break;
											
										default:
											System.out.println("Please Enter value from 1 to 6");
										}						
								
								} finally {
									System.out.println("");
								}
							}
						}
						break;
					case 2:
						while(usersBean == null) {
							usersBean = populateUsersBean();
						}
						mac_service_obj=new MAC_service_impl();
						
						if(mac_service_obj.check_login(usersBean)){
							while (true) {
								
								// code to show menu
								System.out.println();
								System.out.println();
								System.out.println("  MAC Login ");
								System.out.println("_______________________________\n");
	
								System.out.println("1. View applications for a specific program");
								System.out.println("2. Accept/Reject an application ");
								System.out.println("3. After the interview, update the status of the application to Confirmed/Rejected");
								
								System.out.println("________________________________");
								System.out.println("Select an option from the above:");
								// accept option
	
								try {
									option = sc.nextInt();
									switch (option) {
										case 1:
											System.out.println("Please enter the program name for which you want to view the applications");
											
									}						
								
								} finally {
									System.out.println("");
								}
							}
						}
						break;
					case 3:
						while (true) {
							
							student_obj =new student_service_impl();
							List<ProgramsScheduled> rs=student_obj.display_scheduled_programs();
							
							for(ProgramsScheduled p:rs){
								System.out.println(p.getScheduledProgramId()+"\t"+p.getProgramName()+"\t"+p.getLocation()+"\t"+p.getStartDate()+"\t"+p.getEndDate()+"\t"+p.getSessionsPerWeek());
							}
							
							System.out.println("1. Apply for a scheduled program of the university");
							System.out.println("2. View the application status, based on the application ID");
							
							System.out.println("________________________________");
							System.out.println("Select an option from the above:");
							// accept option

							try {
								option = sc.nextInt();
								switch (option) {
									case 1:
										while (ApplicationBean == null) {
											ApplicationBean = populateApplicationBean();
										}
										int insert=student_obj.insert_application(ApplicationBean);
										if(insert==-1){
											System.out.println("Couldn't apply for the program ");
										}else{
											System.out.println("Applied for the program and your application id is "+insert);
										}
										break;
										
									case 2:
										System.out.println("Please enter your application_id");
										int id=sc.nextInt();
										String status=student_obj.display_status(id);
										System.out.println("The status of your application is "+status);
								}						
							
							} finally {
								System.out.println("");
							}
						}
						
				}

			} finally {
				System.out.println("");
			}
		}
	}
	
	private static Application populateApplicationBean() throws ParseException {
		Application app_bean=new Application();
		sc.nextLine();
	
		System.out.println("Enter Full Name: ");
		if (sc.hasNextLine()) {
			app_bean.setFullName(sc.nextLine());
		}
		System.out.println("Enter Date of Birth: ");
		if (sc.hasNextLine()) {
			app_bean.setDateOfBirth(sc.nextLine());
		}
		System.out.println("Enter highestQualification: ");
		if (sc.hasNextLine()) {
			app_bean.setHighestQualification(sc.nextLine());
		}
		System.out.println("Enter marksObtained: ");
		if (sc.hasNextLine()) {
			app_bean.setMarksObtained(sc.nextInt());
		}
		System.out.println("Enter goals: ");
		if (sc.hasNextLine()) {
			app_bean.setGoals(sc.nextLine());
		}
		System.out.println("Enter emailId: ");
		if (sc.hasNextLine()) {
			app_bean.setEmailId(sc.nextLine());
		}
		
		System.out.println("Enter scheduledProgramId: ");
		if (sc.hasNextLine()) {
			app_bean.setScheduledProgramId(sc.nextLine());
		}
		
		return app_bean;
	}

	private static ProgramsScheduled populateprogramsScheduledBean() throws ParseException {
		ProgramsScheduled program_scheduled=new ProgramsScheduled();
		sc.nextLine();
	
		System.out.println("Enter scheduledProgramId: ");
		if (sc.hasNextLine()) {
			program_scheduled.setScheduledProgramId(sc.nextLine());
		}
		System.out.println("Enter programName: ");
		if (sc.hasNextLine()) {
			program_scheduled.setProgramName(sc.nextLine());
		}
		System.out.println("Enter location: ");
		if (sc.hasNextLine()) {
			program_scheduled.setLocation(sc.nextLine());;
		}
		System.out.println("Enter startDate: ");
		if (sc.hasNextLine()) {
			program_scheduled.setStartDate(sc.nextLine());
		}
		System.out.println("Enter endDate: ");
		if (sc.hasNextLine()) {
			program_scheduled.setEndDate(sc.nextLine());
		}
		System.out.println("Enter sessionsPerWeek: ");
		if (sc.hasNextLine()) {
			program_scheduled.setSessionsPerWeek(sc.nextInt());
		}
		
		return program_scheduled;
	}

	private static ProgramsOffered populateprogramsOfferedBean() {
		ProgramsOffered program_offered=new ProgramsOffered();
		sc.nextLine();
		System.out.println("Enter programName: ");
		if (sc.hasNextLine()) {
			program_offered.setProgramName(sc.nextLine());
		}
		System.out.println("Enter description: ");
		if (sc.hasNextLine()) {
			program_offered.setDescription(sc.nextLine());
		}
		System.out.println("Enter applicantEligibility: ");
		if (sc.hasNextLine()) {
			program_offered.setApplicantEligibility(sc.nextLine());;
		}
		System.out.println("Enter duration: ");
		if (sc.hasNextLine()) {
			program_offered.setDuration(sc.nextInt());
		}
		sc.nextLine();
		System.out.println("Enter degreeCertificateOffered: ");
		if (sc.hasNextLine()) {
			program_offered.setDegreeCertificateOffered(sc.nextLine());
		}
		
		return program_offered;
	}

	private static Users populateUsersBean() {
		// TODO Auto-generated method stub
		Users usersBean = new Users();
		sc.nextLine();
		System.out.println("Enter your user-Id: ");
		if (sc.hasNextLine()) {
			usersBean.setLoginId(sc.nextLine());
		}

		System.out.println("Enter your password: ");
		if (sc.hasNextLine()) {
			usersBean.setPassword(sc.nextLine());
		}
		return usersBean;
	}    

}
