package com.capgemini.university.ui;

import java.io.Console;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.Participant;
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;

public class UniversityMain {
	static Scanner sc = new Scanner(System.in);
	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("resources//log4j.properties");
		Application applicationBean = null;
		Participant participantBean = null;
		ProgramsOffered programsOfferedBean = null;
		ProgramsScheduled programsScheduledBean = null;
		Users usersBean = null;
		int option = 0;

		while (true) {

			// code to show menu
			System.out.println();
			System.out.println();
			System.out.println("  University Admission System ");
			System.out.println("_______________________________\n");

			System.out.println("1. Login as Admin ");
			System.out.println("2. Login as MAC");
			System.out.println("3. Check-in as a Student");
			System.out.println("________________________________");
			System.out.println("Select an option from the above:");
			// accept option

			try {
				option = sc.nextInt();
				switch (option) {
                
				case 1: 
					while (usersBean == null) {
						usersBean = populateUsersBean();
					}

				    	//Code to verify Password
					if(true)
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
										//edit populateprogramsOfferedBean method
									}
									//code for storing program details
									
								case 2:
									/* code to show available programs offered and 
									ask him to select which one he wants to delete by ID */
								case 3: 
									while (programsScheduledBean == null) {
										programsScheduledBean = populateprogramsScheduledBean();
										//edit populateprogramsScheduledBean method
									}
									//code for storing program details
								case 4:
									/* code to show available programs scheduled and 
									ask him to select which one he wants to delete by ID */
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

	private static ProgramsScheduled populateprogramsScheduledBean() {
		// TODO Auto-generated method stub
		return null;
	}

	private static ProgramsOffered populateprogramsOfferedBean() {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

}
