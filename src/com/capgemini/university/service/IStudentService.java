package com.capgemini.university.service;

import java.util.List;

import com.capgemini.university.bean.Application;
import com.capgemini.university.bean.ProgramsScheduled;
import com.capgemini.university.exception.UniversityException;

public interface IStudentService {

	public List<ProgramsScheduled> displayScheduledPrograms() throws UniversityException;

	public int insertApplication(Application applicationBean) throws UniversityException;

	public String displayStatus(int id) throws UniversityException;


}
