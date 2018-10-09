package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class MacDAO implements IMacDAO {
	
	Logger log=Logger.getRootLogger();
	public MacDAO(){
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	@Override
	public boolean checkLoginDao(Users userBean) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		
		try{
			p=conn.prepareStatement(QueryMapper.CHECK_LOGIN_QUERY);
			p.setString(1,userBean.getLoginId());
			p.setString(2,userBean.getPassword());
			rs=p.executeQuery();
			if(rs.next()){
				String role=rs.getString("role");
				return (role.equals("mac"))? true:false;
			}else{
				return false;
			}
		}catch(SQLException s){
			log.error(s.getMessage());
			s.printStackTrace();
		}finally{
			try {
				p.close();
				conn.close();
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				log.error(sqlException.getMessage());
			}
		}
		return false;
	}

	@Override
	public List<ProgramsScheduled> displaySchedulesDao() throws UniversityException {
		
		Connection conn=DBConnection.getInstance().getConnection();	
		
		Statement ps=null;
		ResultSet rs = null;
		int programsScheduledCount=0;
		List<ProgramsScheduled> programsScheduledList=new ArrayList<ProgramsScheduled>();
		try{
			ps=conn.createStatement();
			rs=ps.executeQuery(QueryMapper.VIEW_SCHEDULE);
			while(rs.next())
			{
				ProgramsScheduled programsScheduledBean= new ProgramsScheduled();
				programsScheduledBean.setScheduledProgramId(rs.getString(1)); 
				programsScheduledBean.setProgramName(rs.getString(2)); 
				programsScheduledBean.setLocation(rs.getString(3)); 
				programsScheduledBean.setStartDate(rs.getString(4)); 
				programsScheduledBean.setEndDate(rs.getString(5)); 
				programsScheduledBean.setSessionsPerWeek(rs.getInt(6)); 
				programsScheduledList.add(programsScheduledBean);
				programsScheduledCount++;
			}
			/*while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
			}*/			
		}catch(SQLException s){
			log.error(s.getMessage());
			System.out.println(s.getMessage());
		}finally{
			try{
				ps.close();
				conn.close();
			} 
			catch (SQLException e){
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
		}
		if(programsScheduledCount == 0)
			return null;
		else
			return programsScheduledList;
	}

	@Override
	public List<Application> displayApplicationDao(String program_name) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.prepareStatement(QueryMapper.VIEW_APPLICATIONS);
			ps.setString(1, program_name);
			rs=ps.executeQuery();
			List<Application> applications=new ArrayList<Application>();
			
			while(rs.next()){
				Application s=new Application();
				
				s.setApplicantId(rs.getString("APPLICATION_ID"));
				s.setScheduledProgramId(rs.getString("SCHEDULED_PROGRAM_ID"));
				s.setFullName(rs.getString("FULL_NAME"));
				s.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				s.setEmailId(rs.getString("EMAIL_ID"));
				s.setHighestQualification(rs.getString("HIGHEST_QUALIFICATION"));
				s.setMarksObtained(rs.getInt("MARKS_OBTAINED"));
				s.setGoals(rs.getString("GOALS"));
				s.setStatus(rs.getString("STATUS"));
				applications.add(s);
			}
			return applications;
			
		}catch (SQLException sqlException) {
			log.error(sqlException.getMessage());
			System.out.println(sqlException.getMessage());
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException s){
				s.printStackTrace();
				log.error(s.getMessage());
			}
		}
		return null;
	}

	@Override
	public int updateInterviewDateDao(int app_id, String interviewDate) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;		
		try{
			int r=0;
			ps=conn.prepareStatement(QueryMapper.UPDATE_INTERVIEW_STATUS);
			ps.setString(1,interviewDate); //randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
			ps.setInt(2,app_id);
			r=ps.executeUpdate();
			return r;
		}catch(SQLException sql){
			log.error(sql.getMessage());
			sql.printStackTrace();
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				log.error(sqlException.getMessage());
			}
		}
		return 0;
	}

	@Override
	public List<Application> displayNewApplications() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.prepareStatement(QueryMapper.VIEW_NEW_APPLICATIONS);
			rs=ps.executeQuery();
			List<Application> applications=new ArrayList<Application>();
			
			while(rs.next()){
				Application s=new Application();
				
				s.setApplicantId(rs.getString("APPLICATION_ID"));
				s.setScheduledProgramId(rs.getString("SCHEDULED_PROGRAM_ID"));
				s.setFullName(rs.getString("FULL_NAME"));
				s.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				s.setEmailId(rs.getString("EMAIL_ID"));
				s.setHighestQualification(rs.getString("HIGHEST_QUALIFICATION"));
				s.setMarksObtained(rs.getInt("MARKS_OBTAINED"));
				s.setGoals(rs.getString("GOALS"));
				s.setStatus(rs.getString("STATUS"));
				applications.add(s);
			}
			return applications;
			
		}catch (SQLException sqlException) {
			log.error(sqlException.getMessage());
			System.out.println(sqlException.getMessage());
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException s){
				s.printStackTrace();
				log.error(s.getMessage());
			}
		}
		return null;
	}

	@Override
	public int updateRejectStatus(int app_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;		
		try{
			int r=0;
			ps=conn.prepareStatement(QueryMapper.UPDATE_REJECT_STATUS);
			ps.setInt(1,app_id);
			r=ps.executeUpdate();
			return r;
		}catch(SQLException sql){
			log.error(sql.getMessage());
			sql.printStackTrace();
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				log.error(sqlException.getMessage());
			}
		}
		return 0;
	}

	@Override
	public List<Application> displayApprovedApplications() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.prepareStatement(QueryMapper.VIEW_APPROVED_APPLICATIONS);
			rs=ps.executeQuery();
			List<Application> applications=new ArrayList<Application>();
			
			while(rs.next()){
				Application s=new Application();
				
				s.setApplicantId(rs.getString("APPLICATION_ID"));
				s.setScheduledProgramId(rs.getString("SCHEDULED_PROGRAM_ID"));
				s.setFullName(rs.getString("FULL_NAME"));
				s.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				s.setEmailId(rs.getString("EMAIL_ID"));
				s.setHighestQualification(rs.getString("HIGHEST_QUALIFICATION"));
				s.setMarksObtained(rs.getInt("MARKS_OBTAINED"));
				s.setGoals(rs.getString("GOALS"));
				s.setStatus(rs.getString("STATUS"));
				applications.add(s);
			}
			return applications;
			
		}catch (SQLException sqlException) {
			log.error(sqlException.getMessage());
			System.out.println(sqlException.getMessage());
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException s){
				s.printStackTrace();
				log.error(s.getMessage());
			}
		}
		return null;
	}

	@Override
	public int updateConfirmStatus(int app_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;		
		try{
			int r=0;
			ps=conn.prepareStatement(QueryMapper.UPDATE_CONFIRM_STATUS);
			ps.setInt(1,app_id);
			r=ps.executeUpdate();
			return r;
		}catch(SQLException sql){
			log.error(sql.getMessage());
			sql.printStackTrace();
		}
		finally{
			try {
				ps.close();
				conn.close();
			}catch (SQLException sqlException){
				sqlException.printStackTrace();
				log.error(sqlException.getMessage());
			}
		}
		return 0;
	}

}
