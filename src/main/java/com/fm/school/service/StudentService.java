package com.fm.school.service;

import com.fm.school.domain.Student;
import com.fm.school.domain.StudentsResponse;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by fadlymunandar on 10/18/17.
 */
public interface StudentService {

    void addStudent(Student student);
    StudentsResponse getStudents() throws UnknownHostException;
    Student getStudentById(String studentId);
    boolean isStudentExist(String studentId);
    int updateStudent(String studentId, Student student);
    int deleteStudent(int studentId);
}
