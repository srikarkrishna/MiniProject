package com.capgemini.university.dao;

import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface MAC_dao {
	public boolean check_login(Users userBean) throws UniversityException;
}
