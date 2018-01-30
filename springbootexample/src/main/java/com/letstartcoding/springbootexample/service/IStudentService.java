package com.letstartcoding.springbootexample.service;

import java.util.List;

import com.letstartcoding.springbootexample.model.Student;

public interface IStudentService {
	
	public void save(Student student);
	public List<Student> getAllStudents();
	public List<Student> getStudentsByPage(int pageid, int total);
	public Student getStudentById(int id);
	public int count() ;
	public void update(Student p);
	public void delete(int id);
	public void delete();

}
