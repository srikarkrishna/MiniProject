package com.capgemini.university.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.service.AdminService;
import com.capgemini.university.service.IAdminService;
import com.capgemini.university.service.IMACService;
import com.capgemini.university.service.IStudentService;
import com.capgemini.university.service.MACService;
import com.capgemini.university.service.StudentService;

public class UniversityMain {
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getRootLogger();
	static IAdminService adminService = null;
	static AdminService adminSeviceImpl = null;
	static IMACService macService=null;
	static IStudentService studentService=null;
	static StudentService studentSeviceImpl=null;
	public static void main(String[] args) throws UniversityException, ParseException {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("resources//log4j.properties");
		Application applicationBean = null;
		//Participant participantBean = null;
		ProgramsOffered programsOfferedBean = null;
		ProgramsScheduled programsScheduledBean = null;
		Users usersBean = null;
		int option = 0;
		int queryResult=0;

		while(true){

			// code to show menu
			System.out.println();
			System.out.println();
			System.out.println("------------------- [[ UNIVERSITY ADMISSION SYSTEM ]] --------------------");
			System.out.println("________________________________\n");

			System.out.println("[1.] Login as Admin ");
			System.out.println("[2.] Login as MAC");
			System.out.println("[3.] Check-in as a Student");
			System.out.println("[4.] Exit");
			System.out.println("________________________________");
			System.out.println();
			System.out.println("---------- [[ Select an option to login as one of the above: ]] -----------");
			
			// accept option

			try {
				option = sc.nextInt();
				
				switch (option) {
                
				case 1: 
					System.out.println();
					System.out.println();
					System.out.println("[[LOGIN PORTAL]]");
					System.out.println();
					 adminService=new AdminService();
					 
					while(usersBean == null) {
						usersBean = populateUsersBean();
					}

				    	//Code to verify Password
					if(adminService.checkLogin(usersBean))
					{
						//password matches
						while (true) {

							// code to show menu
							System.out.println();
							System.out.println();
							System.out.println("---------------------------- [[ ADMIN LOGIN ]] ----------------------------");
							System.out.println("_______________________________\n");

							System.out.println("[1.] Add a new offered program");
							System.out.println("[2.] Delete a program offered");
							System.out.println("[3.] Add a new scheduled program");
							System.out.println("[4.] Delete a program scheduled");
							System.out.println("[5.] View list of applicants for a scheduled program");
							System.out.println("[6.] View list of programs scheduled in certain time period");
							System.out.println("[7.] Exit");
							System.out.println("________________________________");
							System.out.println();
							System.out.println("------------------ [[ Select an option from the above: ]] -----------------");
							
							// accept option

							try {
								String schedule_id;
								option = sc.nextInt();
								System.out.println();
								switch (option) {
				                
								case 1: 
									while (programsOfferedBean == null) {
										programsOfferedBean = populateprogramsOfferedBean();
									}
									
									queryResult=adminService.addProgram(programsOfferedBean);
									
									if(queryResult==0){
										System.out.println("Program insertion failed");
									}else{
										System.out.println("Program added successfully");
									}
									break;
									
								case 2:
									 adminService=new AdminService();
									/* code to show available programs offered and 
									ask him to select which one he wants to delete by ID */
									 List<ProgramsOffered> programsOfferedList = new ArrayList<ProgramsOffered>();
										programsOfferedList= adminService.displayPrograms();
										if (programsOfferedList != null) {
											Iterator<ProgramsOffered> i = programsOfferedList.iterator();
											System.out.println("----------Printing Available Offered Program Details---------- \n");
											System.out.println("Program Name\t Description\t\t Applicant Eligibility\t\t\t\t Duration\t Certificate Offered\n");
											while (i.hasNext()) {
												System.out.println(i.next());
											}
										} else {
											System.out
													.println("\nNo Offered Program is available.");
										}
										
									sc.nextLine();
									System.out.println();
									System.out.println("Enter the PROGRAM NAME to be deleted(Refer the table above for details)");
									String program_name=sc.nextLine();
									queryResult = adminService.deleteProgram(program_name);
									if(queryResult==0){
										System.out.println("\nDeletion failed");
									}else{
										System.out.println("\nThe record with Program Name "+program_name+" deleted");
									}
									break;
									
								case 3: 
									 adminService=new AdminService();
										programsScheduledBean = populateprogramsScheduledBean();
										//edit populateprogramsScheduledBean method
									//code for storing program details
									
									queryResult= adminService.addSchedule(programsScheduledBean);
									if(queryResult==0){
										System.out.println("\nInsertion failed");
									}else{
										System.out.println("\nSchedule added successfully");
									}
									break;
								case 4:
									/* code to show available programs scheduled and 
									ask him to select which one he wants to delete by ID */
									 adminService=new AdminService();
									 List<ProgramsScheduled> programsScheduledList = new ArrayList<ProgramsScheduled>();
									programsScheduledList= adminService.displaySchedules();
									if (programsScheduledList != null) {
										Iterator<ProgramsScheduled> i = programsScheduledList.iterator();
										System.out.println("----------Printing Available Scheduled Program Details---------- \n");
										System.out.println("Program Id\t Program Name\t Location\t Start Date\t End Date\t Sessions/Week \n");
										while (i.hasNext()) {
											System.out.println(i.next());
										}
									} else {
										System.out
												.println("\nNo Schedule Program is available.");
									}

									sc.nextLine();
									System.out.println();
									System.out.println("Enter the ID of the schedule you want to delete(Refer the table above for details)");
									schedule_id=sc.nextLine();					
									queryResult = adminService.deleteSchedule(schedule_id);
									if(queryResult==0){
										System.out.println("\nDeletion failed");
									}else{
										System.out.println("\nThe record with schedule_id "+schedule_id+" deleted");
									}
									break;
								case 5:
									 adminService=new AdminService();
									/* code to view application status based on program schedule ID */
									 List<ProgramsScheduled> programsScheduledList1 = new ArrayList<ProgramsScheduled>();
										programsScheduledList1= adminService.displaySchedules();
										if (programsScheduledList1 != null) {
											Iterator<ProgramsScheduled> i = programsScheduledList1.iterator();
											System.out.println("----------Printing Available Scheduled Program Details---------- \n");
											System.out.println("Program Id\t Program Name\t Location\t Start Date\t End Date\t Sessions/Week \n");
											while (i.hasNext()) {
												System.out.println(i.next());
											}
											sc.nextLine();
											System.out.println();
											System.out.println("\nEnter the ID of the schedule to view status of application for a scheduled program (Refer the table above for details)");
											schedule_id=sc.nextLine();
											 List<Application> applicationList = new ArrayList<Application>();
											 applicationList = adminService.scheduleApplicantStatus(schedule_id);
											 if (applicationList != null) {
													Iterator<Application> i1 = applicationList.iterator();
													System.out.println("----------Printing Available Applications for given Schedule---------- \n");
													System.out.println("Application Id\t Status\t\t Schedule Id\t \n");
													while (i1.hasNext()) {
														System.out.println(i1.next());
													}
												} else {
													System.out
															.println("\nNo Application is available for given Program Schedule.");
												}
										} else {
											System.out
													.println("\nNo Schedule Program is available.");
										}
									
									break;
								case 6:
									 adminService=new AdminService();
									sc.nextLine();
									System.out.println("\nPlease enter the start date(dd-mm-yyyy)");
									String start_interval=sc.nextLine();
									System.out.println("\nPlease enter the end date(dd-mm-yyyy)");
									String end_interval=sc.nextLine();
									 List<ProgramsScheduled> programsScheduledList11 = new ArrayList<ProgramsScheduled>();
										programsScheduledList11= adminService.scheduleByTimeInterval(start_interval, end_interval);
										if (programsScheduledList11 != null) {
											Iterator<ProgramsScheduled> i = programsScheduledList11.iterator();
											System.out.println("----------Printing Available Scheduled Program Details between "+start_interval +" and "+end_interval+" ---------- \n");
											System.out.println("Program Id\t Program Name\t Location\t Start Date\t End Date\t Sessions/Week \n");
											while (i.hasNext()) {
												System.out.println(i.next());
											}
										} else {
											System.out
													.println("\nNo Schedule Program is available in given interval.");
										}
									break;
								case 7: 
									System.out.println("");
									System.out.print("******************Exited University Application**********************");
									System.exit(0);
									break;
								default:
									System.out.println("\nEnter a valid option[1-7]");
									
								}						
							
							} finally {
								System.out.println("");
							}
						}
					}
				case 2:
					System.out.println();
					System.out.println();
					System.out.println("[[LOGIN PORTAL]]");
					System.out.println();
					macService =new MACService();
					
					while(usersBean == null) {
						usersBean = populateUsersBean();
					}
					
					
					if(macService.checkLogin(usersBean)){
						while (true) {
							
							// code to show menu
							System.out.println();
							System.out.println();
							System.out.println("----------------------------- [[ MAC LOGIN ]] -----------------------------");
							System.out.println("_______________________________\n");
							System.out.println("[1.] View all applications for a specific program");
							System.out.println("[2.] Accept/Reject an application ");
							System.out.println("[3.] Update status of application after an interview");
							System.out.println("[4.] Exit");
							System.out.println("________________________________");
							System.out.println();
							System.out.println("------------------ [[ Select an option from the above: ]] -----------------");
							// accept option

							try {
								option = sc.nextInt();
								switch (option) {
									case 1:
										sc.nextLine();
										macService = new MACService();
										 List<ProgramsScheduled> programsScheduledList = new ArrayList<ProgramsScheduled>();
											programsScheduledList= macService.displaySchedules();
											if (programsScheduledList != null) {
												Iterator<ProgramsScheduled> i1 = programsScheduledList.iterator();
												System.out.println("----------Printing Available Scheduled Program Details---------- \n");
												System.out.println("Program Id\t Program Name\t Location\t Start Date\t End Date\t Sessions/Week \n");
												while (i1.hasNext()) {
													System.out.println(i1.next());
												}
												System.out.println();
										System.out.println("\nPlease enter the PROGRAM NAME for which you want to view the applications(Refer the table above for details)");
										String program_name=sc.nextLine();
										List<Application> applicationsList=new ArrayList<Application>();
										applicationsList= macService.displayApplication(program_name);
										printDetails(applicationsList);
										
									}
											else {
												System.out
														.println("\nNo Schedule Program is available.");
											}
										break;
									
									case 2:
										List<Application> applicationsList=new ArrayList<Application>();
										applicationsList= macService.displayNewApplications();
										printDetails(applicationsList);
										System.out.println("\nEnter the application ID you want to approve or reject for interview(Refer the table above for details)");
										int app_id=sc.nextInt();
										sc.nextLine();
										System.out.println("\nSelect an option \n1. Approve \n2. Reject");
										char macSelect = sc.next().charAt(0);
										switch(macSelect)
										{
										case '1': 
											sc.nextLine();
											System.out.println("\nEnter the date of interview for the applicant");
											String interviewDate=sc.nextLine();
											boolean dateValidate =false;
											try{
											 macService.validateRequest(interviewDate);
											 dateValidate=true;
											}catch (UniversityException universityException) {
												logger.error("exception occured", universityException);
												System.err.println("Invalid data:");
												System.err.println(universityException.getMessage() + " \n Try again..");
												System.exit(0);
											}
											if(dateValidate)
											{
											queryResult=macService.updateInteviewDate(app_id,interviewDate);
											if(queryResult==1){
												System.out.println("\nApplicant has been shortlisted and interview date is assigned");
											}else{
												System.out.println("\nUpdating details failed");
											}
											}
											break;
										case '2':
											queryResult = macService.updateRejectStatus(app_id);
											if(queryResult==1){
												System.out.println("\nApplication has been rejected.");
											}else{
												System.out.println("\nRejection failed.");
											}
											
											break;
										default: System.out.println("\nEnter a valid option[1-2]");
										         break;
										}
										break;
									case 3:
										applicationsList=new ArrayList<Application>();
										applicationsList= macService.displayApprovedApplications();
										printDetails(applicationsList);
										System.out.println("\nEnter the application ID you want to confirm or reject for interview (Refer the table above for details)");
										app_id=sc.nextInt();
										sc.nextLine();
										System.out.println("\nSelect an option \n1. Confirm \n2. Reject");
										macSelect = sc.next().charAt(0);
										switch(macSelect)
										{
										case '1': 
											sc.nextLine();
											queryResult=macService.updateConfirmStatus(app_id);
											if(queryResult==1){
												System.out.println("\nApplicant has been selected.");
											}else{
												System.out.println("\nUpdating details failed");
											}
											break;
										case '2':
											queryResult = macService.updateRejectStatus(app_id);
											if(queryResult==1){
												System.out.println("\nApplication has been rejected.");
											}else{
												System.out.println("\nRejection failed.");
											}
											break;
										default: System.out.println("\nEnter a valid option[1-2]");
										         break;
										}
									case 4: 
										System.out.println("");
										System.out.print("******************Exited University Application**********************");
										System.exit(0);
										break;
									default:
										System.out.println("\nEnter a valid option[1-4]");
								}						
							
							} finally {
								System.out.println("");
							}
						}
					}
					break;
				case 3:
					while (true) {
						System.out.println();
						System.out.println();
						System.out.println("------------------------- [[ STUDENT CHECK-IN ]] --------------------------");
						System.out.println("_______________________________\n");	
						System.out.println("[1.] Apply for a scheduled program of the university");
						System.out.println("[2.] View the application status, based on the application ID");
						System.out.println("[3.] Exit");
						System.out.println("_______________________________");
						System.out.println();
						System.out.println("------------------ [[ Select an option from the above: ]] -----------------");
						// accept option

						try {
							option = sc.nextInt();
							switch (option) {
								case 1:
									studentService =new StudentService();
									List<ProgramsScheduled> programsScheduledList= studentService.displayScheduledPrograms();
									if (programsScheduledList != null) {
										Iterator<ProgramsScheduled> i = programsScheduledList.iterator();
										System.out.println("----------Printing Available Scheduled Program Details---------- \n");
										System.out.println("Program Id\t Program Name\t Location\t Start Date\t End Date\t Sessions/Week \n");
										while (i.hasNext()) {
											System.out.println(i.next());
										}
										sc.nextLine();
										while (applicationBean == null) {
											applicationBean = populateApplicationBean();
										}
										queryResult=studentService.insertApplication(applicationBean);
										if(queryResult==-1){
											System.out.println();
											System.out.println("\nCouldn't apply for the program ");
										}else{
											System.out.println();
											System.out.println("\nApplied for the program and your application id is "+queryResult);
										}
										applicationBean=null;
									}
									else
									{
										System.out.println("\nNo program has been scheduled.\n");
										System.out.print("****************** [[ Exited University Application ]] **********************");
										System.exit(0);
									}
										
									break;
									
								case 2:
									studentService =new StudentService();
									System.out.println("\nPlease enter your application_id");
									int id=sc.nextInt();
									String status=studentService.displayStatus(id);
									if(status.isEmpty())
									{
										System.out.println("\nPlease enter a valid Application number!!");
									}
									else
									{
									System.out.println("\nThe status of your application is "+status);
									}
									break;
								case 3: 
									System.out.println("");
									System.out.print("******************Exited University Application**********************");
									System.exit(0);
									break;
								default:
									System.out.println("\nEnter a valid option[1-3]");
							}
							
						
						}finally {
							System.out.println("");
						}
				}
				case 4: 
					System.out.println("");
					System.out.print("******************Exited University Application**********************");
					System.exit(0);
					break;
				default:
					System.out.println("\nEnter a valid option[1-2]");
				}

			}
			finally {
				System.out.println("");
			}
		}
	}

	private static void printDetails(List<Application> applicationsList) {
		if (applicationsList != null) {
			Iterator<Application> iterator = applicationsList.iterator();
			System.out.println("----------Printing Available Application Details---------- \n");
			System.out.println("Appln Id \tName\t\t      ProgId   Date Of Birth\tEmail-Id \t\tQualification \tStatus \t    Marks   Goals  \n");
			
			int n=0;
			while (iterator.hasNext()) {
				StringBuilder applicationListBuilder = new StringBuilder();
				Application log = iterator.next();
				applicationListBuilder.append( log.getApplicationId());
				n=16-log.getApplicationId().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				
				applicationListBuilder.append( log.getFullName());
				n=22-log.getFullName().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append(log.getScheduledProgramId());
				n=9-log.getScheduledProgramId().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append( log.getDateOfBirth().substring(0, 10));
				n=7;
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append( log.getEmailId());
				n=24-log.getEmailId().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append(  log.getHighestQualification());
				n=16-log.getHighestQualification().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append(  log.getStatus());
				n=12-log.getStatus().length();
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append( log.getMarksObtained());
				if( log.getMarksObtained()==100)
				{
				n=5;
				}
				else
				{
				 n=6;
				}
				while(n>0)
				{
					applicationListBuilder.append(" ");
					n--;
				}
				applicationListBuilder.append( log.getGoals());

				System.out.println(applicationListBuilder.toString());
			}
		}
		
	}

	private static Application populateApplicationBean() throws ParseException {
		Application applicationBean=new Application();
		//sc.nextLine();
		System.out.println();
		System.out.println("Enter Full Name: ");
		if (sc.hasNextLine()) {
			applicationBean.setFullName(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Date of Birth(Format: dd-mm-yyyy): ");
		if (sc.hasNextLine()) {
			applicationBean.setDateOfBirth(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter your highest qualification: ");
		if (sc.hasNextLine()) {
			applicationBean.setHighestQualification(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter marks obtained: ");
		if (sc.hasNextLine()) {
			applicationBean.setMarksObtained(sc.nextInt());
		}
		System.out.println();
		sc.nextLine();
		System.out.println("Enter your goals: ");
		if (sc.hasNextLine()) {
			applicationBean.setGoals(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter your emailId: ");
		if (sc.hasNextLine()) {
			applicationBean.setEmailId(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Id of a scheduled program you want to enroll(Refer the table above for details): ");
		if (sc.hasNextLine()) {
			applicationBean.setScheduledProgramId(sc.nextLine());
		}
		System.out.println();
		studentSeviceImpl = new StudentService();

		try {
			studentSeviceImpl.validateRequest(applicationBean);
			return applicationBean;
		} catch (UniversityException universityException) {
			logger.error("exception occured", universityException);
			System.err.println("Invalid data:");
			System.err.println(universityException.getMessage() + " \n Try again..");
			System.out.println();
			System.exit(0);

		}

		return null;

	}

	private static ProgramsScheduled populateprogramsScheduledBean() throws ParseException {
		ProgramsScheduled program_scheduled=new ProgramsScheduled();
		sc.nextLine();
		System.out.println();
		System.out.println("Enter an Id for Program Schedule: ");
		if (sc.hasNextLine()) {
			program_scheduled.setScheduledProgramId(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Program Name: ");
		if (sc.hasNextLine()) {
			program_scheduled.setProgramName(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Location: ");
		if (sc.hasNextLine()) {
			program_scheduled.setLocation(sc.nextLine());;
		}
		System.out.println();
		System.out.println("Enter Start-Date (Format: dd-mm-yyyy): ");
		if (sc.hasNextLine()) {
			program_scheduled.setStartDate(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter End-Date (Format: dd-mm-yyyy): ");
		if (sc.hasNextLine()) {
			program_scheduled.setEndDate(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter number of Sessions Per Week(>0): ");
		if (sc.hasNextLine()) {
			program_scheduled.setSessionsPerWeek(sc.nextInt());
		}
		System.out.println();
		adminSeviceImpl = new AdminService();

		try {
			adminSeviceImpl.validateRequest(program_scheduled);
			return program_scheduled;
		} catch (UniversityException universityException) {
			logger.error("exception occured", universityException);
			System.err.println("Invalid data:");
			System.err.println(universityException.getMessage() + " \n Try again..");
			System.out.println();
			System.exit(0);

		}

		return null;
	}

	private static ProgramsOffered populateprogramsOfferedBean() {
		ProgramsOffered program_offered=new ProgramsOffered();
		sc.nextLine();
		System.out.println();
		System.out.println("Enter Program Name: ");
		if (sc.hasNextLine()) {
			program_offered.setProgramName(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Description: ");
		if (sc.hasNextLine()) {
			program_offered.setDescription(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter Applicant Eligibility: ");
		if (sc.hasNextLine()) {
			program_offered.setApplicantEligibility(sc.nextLine());;
		}
		System.out.println();
		System.out.println("Enter Duration (Number of years): ");
		if (sc.hasNextLine()) {
			program_offered.setDuration(sc.nextInt());
		}
		System.out.println();
		sc.nextLine();
		System.out.println("Enter Degree Certificate Offered: ");
		if (sc.hasNextLine()) {
			program_offered.setDegreeCertificateOffered(sc.nextLine());
		}
		System.out.println();
		adminSeviceImpl = new AdminService();

		try {
			adminSeviceImpl.validateRequest(program_offered);
			return program_offered;
		} catch (UniversityException universityException) {
			logger.error("exception occured", universityException);
			System.err.println("Invalid data:");
			System.err.println(universityException.getMessage() + " \n Try again..");
			System.exit(0);

		}

		return null;
	}

	private static Users populateUsersBean() {
		// TODO Auto-generated method stub
		Users usersBean = new Users();
		sc.nextLine();
		System.out.println();
		System.out.println("Enter your user-Id:");
		if (sc.hasNextLine()) {
			usersBean.setLoginId(sc.nextLine());
		}
		System.out.println();
		System.out.println("Enter your password:");
		if (sc.hasNextLine()) {
			usersBean.setPassword(sc.nextLine());
		}
		adminSeviceImpl = new AdminService();
		System.out.println();
		try {
			adminSeviceImpl.validateRequest(usersBean);
			return usersBean;
		} catch (UniversityException universityException) {
			logger.error("exception occured", universityException);
			System.err.println("Invalid data:");
			System.err.println(universityException.getMessage() + " \n Try again..");
			System.exit(0);

		}

		return null;
	}

}
