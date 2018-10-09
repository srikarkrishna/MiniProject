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
import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class AdminDAO implements IAdminDAO{
	Logger log=Logger.getRootLogger();
	public AdminDAO(){
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
				return (role.equals("admin"))? true:false;
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
	public int addProgramDao(ProgramsOffered program) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		try{
			p=conn.prepareStatement(QueryMapper.INSERT_PROGRAM);
			
			p.setString(1,program.getProgramName());
			p.setString(2,program.getDescription());
			p.setString(3,program.getApplicantEligibility());
			p.setInt(4,program.getDuration());
			p.setString(5,program.getDegreeCertificateOffered());
			
			int queryResult=p.executeUpdate();
			return queryResult;
			
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
		return 0;
	}

	@Override
	public int deleteProgramDao(String program_name) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(QueryMapper.DELETE_PROGRAM);
			ps.setString(1,program_name);
			int queryResult=ps.executeUpdate();
			return queryResult;
		
			
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
		return 0;
	}

	@Override
	public List<ProgramsOffered> displayProgramsDao() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		
		Statement ps=null;
		ResultSet rs = null;
		int programsOfferedCount=0;
		List<ProgramsOffered> programsOfferedList=new ArrayList<ProgramsOffered>();
		try{
			ps=conn.createStatement();
			rs=ps.executeQuery(QueryMapper.VIEW_PROGRAMS_OFFERED);
			while(rs.next())
			{
				ProgramsOffered programsOfferedBean= new ProgramsOffered();
				programsOfferedBean.setProgramName(rs.getString(1)); 
				programsOfferedBean.setDescription(rs.getString(2)); 
				programsOfferedBean.setApplicantEligibility(rs.getString(3)); 
				programsOfferedBean.setDuration(rs.getInt(4)); 
				programsOfferedBean.setDegreeCertificateOffered(rs.getString(5)); 
				programsOfferedList.add(programsOfferedBean);
				programsOfferedCount++;
			}
			/*while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
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
		if(programsOfferedCount == 0)
			return null;
		else
			return programsOfferedList;
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
	public int addScheduleDao(ProgramsScheduled program) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		try{
			p=conn.prepareStatement(QueryMapper.INSERT_SCHEDULE);
			
			p.setString(1,program.getScheduledProgramId());
			p.setString(2,program.getProgramName());
			p.setString(3,program.getLocation());
			p.setString(4,program.getStartDate());
			p.setString(5,program.getEndDate());
			p.setInt(6,program.getSessionsPerWeek());
			
			int queryResult=p.executeUpdate();
			return queryResult;
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
		return 0;
	}

	@Override
	public int deleteScheduleDao(String schedule_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(QueryMapper.DELETE_SCHEDULE);
			ps.setString(1,schedule_id);
			int queryResult=ps.executeUpdate();
			return queryResult;
			
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
		return 0;
	}

	@Override
	public List<ProgramsScheduled> scheduleByTimeIntervalDao(String start,String end) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		ResultSet rs = null;
		int programsScheduledCount=0;
		List<ProgramsScheduled> programsScheduledList=new ArrayList<ProgramsScheduled>();
		try{
			ps=conn.prepareStatement(QueryMapper.SCHEDULES_BETWEEN_TIME_INTERVAL);
			ps.setString(1,start);
			ps.setString(2,end);
			rs=ps.executeQuery();
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
		if(programsScheduledCount == 0)
			return null;
		else
			return programsScheduledList;
	}

	@Override
	public List<Application> scheduleApplicantStatus(String schedule_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		
		int applicationCount=0;
		List<Application> applicationList=new ArrayList<Application>();
		try{
			
			ps=conn.prepareStatement(QueryMapper.SCHEDULE_APPLICANT_STATUS);
			ps.setString(1,schedule_id);
			ResultSet rs = null;
			rs=ps.executeQuery();
			while(rs.next())
			{
				Application applicationBean= new Application();
				applicationBean.setScheduledProgramId(rs.getString(8)); 
				applicationBean.setStatus(rs.getString(9)); 
				applicationBean.setApplicantId(rs.getString(1)); 
				applicationList.add(applicationBean);
				applicationCount++;
			}
			
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
		if(applicationCount == 0)
			return null;
		else
			return applicationList;
	}
	
}
