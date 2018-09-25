package com.capgemini.university.ui;

import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.Participant;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.service.University_service_impl;

public class UniversityMain {
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws UniversityException, ParseException {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("resources//log4j.properties");
		Application applicationBean = null;
		Participant participantBean = null;
		ProgramsOffered programsOfferedBean = null;
		ProgramsScheduled programsScheduledBean = null;
		Users usersBean = null;
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
					if(university_obj.check_login(usersBean))
					{
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
							System.out.println("________________________________");
							System.out.println("Select an option from the above:");
							// accept option

							try {
								option = sc.nextInt();
								switch (option) {
				                
								case 1: 
									while (programsOfferedBean == null) {
										programsOfferedBean = populateprogramsOfferedBean();
									}
									university_obj.add_program(programsOfferedBean);
									
								case 2:
									/* code to show available programs offered and 
									ask him to select which one he wants to delete by ID */
									university_obj.display_programs();
									System.out.println("Enter the program name to be deleted");
									String program_name=sc.nextLine();
									university_obj.delete_program(program_name);
									
								case 3: 
									while (programsScheduledBean == null) {
										programsScheduledBean = populateprogramsScheduledBean();
										//edit populateprogramsScheduledBean method
									}
									//code for storing program details
									
									university_obj.add_schedule(programsScheduledBean);
									
								case 4:
									/* code to show available programs scheduled and 
									ask him to select which one he wants to delete by ID */
									
									university_obj.display_schedules();
									System.out.println("Enter the program name to be deleted");
									int schedule_id=sc.nextInt();
									
									university_obj.delete_schedule(schedule_id);
								case 5:
									/* code to view application status based on program schedule ID */
								}
							
							} finally {
								System.out.println("");
							}
						}
					}
				}

			} finally {
				System.out.println("");
			}
		}
	}

	private static ProgramsScheduled populateprogramsScheduledBean() throws ParseException {
		ProgramsScheduled program_scheduled=new ProgramsScheduled();
		sc.nextLine();
		
		Calendar c=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		long currentDateTime = System.currentTimeMillis();
		
		
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
			Date start=sdf.parse(sc.nextLine());
			program_scheduled.setStartDate(start);
		}
		System.out.println("Enter endDate: ");
		if (sc.hasNextLine()) {
			Date end=sdf.parse(sc.nextLine());
			program_scheduled.setEndDate(end);
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
