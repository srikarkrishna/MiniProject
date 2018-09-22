package com.capgemini.university.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;

import com.capgemini.university.exception.UniversityException;

public class DBConnection {

	private static Connection conn = null;
	private static DBConnection instance = null;
	private static Properties props = null;
	private static OracleDataSource dataSource = null;


	/*************************************************************************************
	 *  - @throws CabException
	 *  - Private Constructor
	 *  - Author : SRIKAR 
	 *  - Creation Date : 29/08/2018
	 *  - Desc:Loads the  jdbc.properties file and Driver Class and gets the connection
	 ***************************************************************************************/
	private DBConnection() throws UniversityException {
		try {
			props = loadProperties();
			dataSource = prepareDataSource();
		} catch (IOException e) {
			throw new UniversityException(
					" Could not read the database details from properties file ");
		} catch (SQLException e) {
			throw new UniversityException(e.getMessage());
		}

	}

	/*****************************************************************
	 *  - Method Name:getInstance() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : CabException 
	 *  - Author : SRIKAR 
	 *  - Creation Date : 29/08/2018
	 *  - Description : Singleton and Thread safe class
	 *******************************************************************/
	
	public static DBConnection getInstance() throws UniversityException {
		synchronized (DBConnection.class) {
			if (instance == null) {
				instance = new DBConnection();
			}
		}
		return instance;
	}

	/*****************************************************************
	 *  - Method Name:getConnection() 
	 *  - Input Parameters : 
	 *  - Return Type :DBConnection instance
	 *  - Throws : CabException 
	 *  - Author : SRIKAR 
	 *  - Creation Date : 29/08/2018
	 *  - Description :  Returns connection object
	 *******************************************************************/
	public Connection getConnection() throws UniversityException {
		try {

			conn = dataSource.getConnection();

		} catch (SQLException e) {

			throw new UniversityException(" Database connection problem");
		}
		return conn;
	}
	
	/*****************************************************************
	 *  - Method Name:loadProperties()
	 *  - Input Parameters : 
	 *  - Return Type :Properties object
	 *  - Author : SRIKAR 
	 *  - Creation Date : 29/08/2018
	 *  - Description : Returns Properties object
	 *******************************************************************/
	
	private Properties loadProperties() throws IOException {

		if (props == null) {
			Properties newProps = new Properties();
			String fileName = "resources/jdbc.properties";

			InputStream inputStream = new FileInputStream(fileName);
			newProps.load(inputStream);

			inputStream.close();

			return newProps;
		} else {
			return props;
		}
	}

	/*****************************************************************
	 *  - Method Name:prepareDataSource() 
	 *  - Input Parameters : 
	 *  - Return Type :OracleDataSource object
	 *  - Author : SRIKAR 
	 *  - Creation Date : 29/08/2018
	 *  - Description : Returns OracleDataSource object
	 *******************************************************************/
	
	private OracleDataSource prepareDataSource() throws SQLException {

		if (dataSource == null) {
			if (props != null) {
				String connectionURL = props.getProperty("dburl");
				String username = props.getProperty("username");
				String password = props.getProperty("password");

				dataSource = new OracleDataSource();

				dataSource.setURL(connectionURL);
				dataSource.setUser(username);
				dataSource.setPassword(password);
			}
		}
		return dataSource;
	}

}
