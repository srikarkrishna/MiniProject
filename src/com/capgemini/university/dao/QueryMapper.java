package com.capgemini.university.dao;

public interface QueryMapper {
	public static String Check_login_query="select * from Users where login_id=? and password=?";
}
