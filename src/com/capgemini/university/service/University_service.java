package com.capgemini.university.service;

import com.capgemini.university.bean.Users;
import com.capgemini.university.exception.UniversityException;

public interface University_service {
	public boolean check_login(Users userBean) throws UniversityException;
}
