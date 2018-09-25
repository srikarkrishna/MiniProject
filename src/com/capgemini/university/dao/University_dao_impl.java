package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.university.bean.ProgramsOffered;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;
import com.capgemini.university.util.DBConnection;

public class University_dao_impl implements University_dao{
	Logger log=Logger.getRootLogger();
	public University_dao_impl(){
		PropertyConfigurator.configure("resources//log4j.properties");
	}

	@Override
	public boolean check_login_dao(Users userBean) throws UniversityException {
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
	public void add_program_dao(ProgramsOffered program) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		try{
			p=conn.prepareStatement(QueryMapper.insert_program);
			
			p.setString(1,program.getProgramName());
			p.setString(2,program.getDescription());
			p.setString(3,program.getApplicantEligibility());
			p.setInt(4,program.getDuration());
			p.setString(5,program.getDegreeCertificateOffered());
			
			int success=p.executeUpdate();
			if(success==0){
				System.out.println("Insertion failed");
			}else{
				System.out.println("Program added successfully");
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
	}

	@Override
	public void delete_program_dao(String program_name) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(QueryMapper.delete_Program);
			ps.setString(1,program_name);
			int success=ps.executeUpdate();
			if(success==0){
				System.out.println("Deletion failed");
			}else{
				System.out.println("The record with program_name "+program_name+" deleted");
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
	}

	@Override
	public void display_programs_dao() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		
		Statement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.createStatement();
			rs=ps.executeQuery(QueryMapper.view_Programs_Offered);
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			}			
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
	}

	@Override
	public void add_schedule_dao(ProgramsScheduled program) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();
		
		PreparedStatement p=null;		
		ResultSet rs = null;
		try{
			p=conn.prepareStatement(QueryMapper.insert_schedule);
			
			p.setString(1,program.getScheduledProgramId());
			p.setString(2,program.getProgramName());
			p.setString(3,program.getLocation());
			p.setDate(4,(Date) program.getStartDate());
			p.setDate(5,(Date) program.getEndDate());
			p.setInt(6,program.getSessionsPerWeek());
			
			int success=p.executeUpdate();
			if(success==0){
				System.out.println("Insertion failed");
			}else{
				System.out.println("Schedule added successfully");
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
	}

	@Override
	public void display_schedules_dao() throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		
		Statement ps=null;
		ResultSet rs = null;
		
		try{
			ps=conn.createStatement();
			rs=ps.executeQuery(QueryMapper.view_schedule);
			while(rs.next()){
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
			}			
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
	}

	@Override
	public void delete_schedule_dao(int schedule_id) throws UniversityException {
		Connection conn=DBConnection.getInstance().getConnection();	
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(QueryMapper.delete_Schedule);
			ps.setInt(1,schedule_id);;
			int success=ps.executeUpdate();
			if(success==0){
				System.out.println("Deletion failed");
			}else{
				System.out.println("The record with schedule_id "+schedule_id+" deleted");
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
	}
	
}
