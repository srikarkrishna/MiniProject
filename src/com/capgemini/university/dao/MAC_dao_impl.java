package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

public class MAC_dao_impl implements MAC_dao{
	Logger log=Logger.getRootLogger();
	public MAC_dao_impl(){
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
	public boolean check_login(Users userBean) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		
		try{
			p=conn.prepareStatement(QueryMapper.Check_login_query);
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
	public List<Application> display_application_dao(String program_name) throws UniversityException {
		
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.prepareStatement(QueryMapper.view_applications);
			ps.setString(1, program_name);
			rs=ps.executeQuery();
			List<Application> applications=new ArrayList<Application>();
			
			while(rs.next()){
				Application s=new Application();
				s.setApplicantId(rs.getString("APPLICATION_ID"));
				s.setFullName(rs.getString("FULL_NAME"));
				s.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
				s.setEmailId(rs.getString("EMAIL_ID"));
				s.setHighestQualification(rs.getString("HIGHEST_QUALIFICATION"));
				s.setMarksObtained(rs.getInt("HIGHEST_QUALIFICATION"));
				s.setGoals(rs.getString("GOALS"));
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
	public int update_interview_date_dao(int app_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;		
		ResultSet rs= null;
		try{
			int r=0;
			ps=conn.prepareStatement(QueryMapper.update_interview_date);
			ps.setString(1,randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
	public int delete_application_dao(int app_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(QueryMapper.delete_application);
			ps.setInt(1,app_id);
			int row_affected=ps.executeUpdate();
			return row_affected;
			
		}catch (SQLException sqlException) {
			log.error(sqlException.getMessage());
			System.out.println(sqlException.getMessage());
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
	public int update_status_dao(int app_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		PreparedStatement ps=null;		
		ResultSet rs= null;
		try{
			int r=0;
			ps=conn.prepareStatement(QueryMapper.update_status);
			ps.setString(1,"Confirmed");
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

}