package com.capgemini.university.dao;

public interface QueryMapper {
	//for admin
	public static String Check_login_query="select * from Users where login_id=? and password=?";
	public static String insert_program="insert into Programs_Offered values (?,?,?,?,?)";
	public static String insert_schedule="insert into Programs_Scheduled values (?,?,?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),?)";
	public static String view_Programs_Offered="select * from Programs_Offered";
	public static String view_schedule="select * from Programs_Scheduled";
	public static String delete_Program="delete from Programs_Offered where ProgramName=?";
	public static String delete_Schedule="delete from Programs_Scheduled where SCHEDULED_PROGRAM_ID=?";
	public static String schedules_between_time_interval="select * from Programs_Scheduled where start_date between TO_DATE(?, 'DD/MM/YYYY') AND TO_DATE(?, 'DD/MM/YYYY')";
	public static String schedule_applicant_status="select application_id,status,scheduled_program_id from application group by scheduled_program_id,status having scheduled_program_id=?";

	//for student
	public static String insert_application="INSERT INTO APPLICATION  VALUES (?,?,?,?,?,?,?,?,?)";
	public static String display_status="select status from application where application_id=?";
	
	//for MAC
	
}
