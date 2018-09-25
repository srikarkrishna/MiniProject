package com.capgemini.university.dao;

import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface University_dao {
	public boolean check_login_dao(Users userBean) throws UniversityException;
}
