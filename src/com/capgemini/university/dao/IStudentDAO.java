package com.capgemini.university.dao;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;

public interface IStudentDAO {

	public List<ProgramsScheduled> displaySchedulesDao() throws UniversityException;

	public int insertApplicationDao(Application applicationBean) throws UniversityException;

	public String displayStatusDao(int id) throws UniversityException;

}
