package com.spring.app.dao;

import java.util.List;

import com.spring.app.pojo.Student;

public interface StudentDao {

	int create(Student student);

	List<Student> read();

	List<Student> findStudentById(int studentId);

	int update(Student student);

	int delete(int studentId);

}
