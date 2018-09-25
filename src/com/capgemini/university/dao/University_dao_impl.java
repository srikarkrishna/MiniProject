package com.capgemini.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
		};
		return false;
	}
	
}
