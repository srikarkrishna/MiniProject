package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class student_dao_impl implements student_dao{
	
	Logger log=Logger.getRootLogger();
	
	public student_dao_impl(){
		PropertyConfigurator.configure("resources//log4j.properties");
	}
	
	public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
    
    LocalDate randomDate = createRandomDate(2018, 2018);

	@Override
	public List<ProgramsScheduled> display_scheduled_programs_dao() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		
		Statement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.createStatement();
			rs=ps.executeQuery(QueryMapper.view_schedule);
			List<ProgramsScheduled> programs_scheduled=new ArrayList<ProgramsScheduled>();
			while(rs.next()){
				ProgramsScheduled s=new ProgramsScheduled();
				s.setScheduledProgramId(rs.getString(1));
				s.setProgramName(rs.getString(2));
				s.setLocation(rs.getString(3));
				s.setStartDate(rs.getString(4));
				s.setEndDate(rs.getString(5));
				s.setSessionsPerWeek(rs.getInt("SESSIONS_PER_WEEK"));
				programs_scheduled.add(s);
			}
			return programs_scheduled;
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
		return null;
	}


	@Override
	public int insert_application_dao(Application app) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		int id=-1;
		try{
			p=conn.prepareStatement(QueryMapper.insert_application);
			Random rand = new Random(); 
	        int app_id = rand.nextInt(100000); 
			
			p.setInt(1,app_id);
			p.setString(2,app.getFullName());
			p.setString(3,app.getDateOfBirth());
			p.setString(4,app.getHighestQualification());
			p.setInt(5,app.getMarksObtained());
			p.setString(6,app.getGoals());
			p.setString(7,app.getEmailId());
			p.setString(8,app.getScheduledProgramId());
			p.setString(9,"Applied");
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
	public String display_status_dao(int id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		String status="";
		
		try{
			p=conn.prepareStatement(QueryMapper.display_status);
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
