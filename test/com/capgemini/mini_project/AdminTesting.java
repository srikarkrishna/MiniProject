package com.capgemini.mini_project;


import java.sql.Connection;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.dao.AdminDAO;
import com.capgemini.university.exception.UniversityException;

public class AdminTesting {
	
	static Connection conn;
	static AdminDAO admin;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		conn=null;
		admin=new AdminDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		conn=null;
		admin=null;
	}

	@Test
	public void testLogin() throws UniversityException {
		Users user=new Users();
		user.setLoginId("adda");
		user.setPassword("laxman");
		Assert.assertEquals(true,admin.checkLoginDao(user));
	}
	
	@Test
	public void testAddProgram() throws UniversityException {
		ProgramsOffered p=new ProgramsOffered();
		p.setProgramName("maths");
		p.setDescription("teach maths");
		p.setApplicantEligibility("degree");
		p.setDuration(2);
		p.setDegreeCertificateOffered("m.tech");
		Assert.assertEquals(1,admin.addProgramDao(p));
	}
	
	@Test
	public void testdeleteProgram() throws UniversityException {
		Assert.assertEquals(1,admin.deleteProgramDao("maths"));
	}
	
	@Test
	public void testAddSchedule() throws UniversityException {
		ProgramsScheduled p=new ProgramsScheduled();
		p.setScheduledProgramId("s1");
		p.setProgramName("maths");
		p.setLocation("bangalore");
		p.setStartDate("12/10/2018");
		p.setEndDate("12/10/2020");
		p.setSessionsPerWeek(3);
		Assert.assertEquals(1,admin.addScheduleDao(p));
	}
	
	@Test
	public void testdeleteSchedule() throws UniversityException {
		Assert.assertEquals(1,admin.deleteProgramDao("s1"));
	}
	
	@Test
	public void testscheduleApplicantStatus() throws UniversityException  {
		Assert.assertNotNull(admin.scheduleApplicantStatus("s1"));
	}
	
	@Test
	public void testscheduleByTimeIntervalDao() throws UniversityException  {
		Assert.assertNotNull(admin.scheduleByTimeIntervalDao("10/10/2018","14/10/2020"));
	}
	
	@Test
	public void testdisplaySchedulesDao() throws UniversityException  {
		Assert.assertNotNull(admin.displaySchedulesDao());
	}
	
	@Test
	public void testdisplayProgramsDao() throws UniversityException  {
		Assert.assertNotNull(admin.displayProgramsDao());
	}


}
