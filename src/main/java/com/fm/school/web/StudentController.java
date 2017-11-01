package com.fm.school.web;

import com.fm.school.domain.ErrorResponse;
import com.fm.school.domain.Student;
import com.fm.school.domain.StudentsResponse;
import com.fm.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentsResponse> getStudents() throws UnknownHostException {

        StudentsResponse studentResponse = studentService.getStudents();

        if (studentResponse.getData().isEmpty()) {
            return new ResponseEntity<StudentsResponse>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<StudentsResponse>(studentResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") String studentId) {
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {

        if (studentService.isStudentExist(student.getStudentId())) {
                return new ResponseEntity(HttpStatus.CONFLICT);
        }

        studentService.addStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/v1/students/{id}").buildAndExpand(student.getStudentId()).toUri());

        return new ResponseEntity(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") String studentId, @RequestBody Student student) {
        int updateCount = studentService.updateStudent(studentId, student);

        if (updateCount > 0) {
            return new ResponseEntity<Student>(student,HttpStatus.OK);
        }

        return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteStudent(@PathVariable int studentId) {

        if (studentService.deleteStudent(studentId) > 0) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> studentNotFoundException() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
