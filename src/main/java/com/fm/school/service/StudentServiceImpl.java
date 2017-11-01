package com.fm.school.service;

import com.fm.school.domain.Student;
import com.fm.school.domain.StudentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;


    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public StudentsResponse getStudents() throws UnknownHostException {
        return studentDao.getAllStudents();
    }

    public Student getStudentById(String studentId) throws EmptyResultDataAccessException {
        return studentDao.getSingleStudent(studentId);
    }

    public boolean isStudentExist(String studentId) {
        return studentDao.isStudentExist(studentId);
    }

    public int updateStudent(String studentId, Student student) {
        return studentDao.updateStudent(studentId, student);
    }

    public int deleteStudent(int studentId) {
        return studentDao.deleteStudent(studentId);
    }
}
