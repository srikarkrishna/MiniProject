package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class StudentDAO implements IStudentDAO{
	Logger log=Logger.getRootLogger();
	public StudentDAO(){
		PropertyConfigurator.configure("resources//log4j.properties");
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
	public int insertApplicationDao(Application applicationBean) throws UniversityException {
Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		int id=-1;
		try{
			p=conn.prepareStatement(QueryMapper.INSERT_APPLICATION);
			Random rand = new Random(); 
	        int app_id = rand.nextInt(100000); 
			
			p.setInt(1,app_id);
			p.setString(2,applicationBean.getFullName());
			p.setString(3,applicationBean.getDateOfBirth());
			p.setString(4,applicationBean.getHighestQualification());
			p.setInt(5,applicationBean.getMarksObtained());
			p.setString(6,applicationBean.getGoals());
			p.setString(7,applicationBean.getEmailId());
			p.setString(8,applicationBean.getScheduledProgramId());
			p.setString(9,"APPLIED");
			p.setString(10,"");
			//p.setString(10,randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			int success=p.executeUpdate();
			if(success==1){
				id=app_id;
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
		return id;
	}
	@Override
	public String displayStatusDao(int id) throws UniversityException {
Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		String status="";
		try{
			p=conn.prepareStatement(QueryMapper.DISPLAY_APPLICATION_STATUS);
			p.setInt(1,id);
			rs=p.executeQuery();
			if(rs.next()){
				status=rs.getString("status");	
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
		return status;
	}

}
