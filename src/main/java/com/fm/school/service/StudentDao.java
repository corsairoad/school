package com.fm.school.service;

import com.fm.school.domain.Student;
import com.fm.school.domain.StudentsResponse;
import com.fm.school.util.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by fadlymunandar on 10/18/17.
 */

@Repository
public class StudentDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void addStudent(Student student) {
        if (student != null) {
            String sql = "INSERT INTO student(student_name, student_id) VALUES(:name, :studentId)";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("name", student.getStudentName());
            param.addValue("studentId", student.getStudentId());

            jdbcTemplate.update(sql, param);
        }
    }

    StudentsResponse getAllStudents() throws UnknownHostException {
        String sql = "SELECT * FROM student ORDER BY student_id";

        List<Student> students = jdbcTemplate.query(sql, new StudentMapper());

        String address = "192.168.0.5";

        for (Student student : students) {
            student.setSelf("http://" + address + ":8888/school/api/v1/students/" + student.getStudentId());
        }

        return new StudentsResponse(students);
    }

    Student getSingleStudent(String studentId){
        String sql = "SELECT * FROM student where student_id = :studentId";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("studentId", studentId);

        Student student = jdbcTemplate.queryForObject(sql, map, new StudentMapper());
        if (student == null) {
            throw new EmptyResultDataAccessException("Student not found", 1);
        }
        return student;
    }

    boolean isStudentExist(String studentId) {
        String sql = "SELECT count(*) FROM student where student_id = :studentId";
        MapSqlParameterSource map = new MapSqlParameterSource("studentId", studentId);

        Integer count = jdbcTemplate.queryForObject(sql,map,Integer.class);

        return count != null && count > 0;
    }

    int updateStudent(String studentId, Student student) {
        String sql = "UPDATE student SET student_name = :studentName, student_id = :studentId" +
                " WHERE student_id = :studentIdparam";

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("studentName", student.getStudentName());
        map.addValue("studentId", student.getStudentId());
        map.addValue("studentIdparam", studentId);

        return jdbcTemplate.update(sql, map);
    }

    int deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE _id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return jdbcTemplate.update(sql, map);
    }

}
