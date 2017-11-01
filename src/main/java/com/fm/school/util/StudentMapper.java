package com.fm.school.util;

import com.fm.school.domain.Student;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by fadlymunandar on 10/18/17.
 */

public class StudentMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt(rs.findColumn("_id")));
        student.setStudentName(rs.getString(rs.findColumn("student_name")));
        student.setStudentId(rs.getString(rs.findColumn("student_id")));

        return student;
    }
}
