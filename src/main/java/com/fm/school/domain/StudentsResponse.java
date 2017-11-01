package com.fm.school.domain;

import java.util.List;

/**
 * Created by fadlymunandar on 10/19/17.
 */


public class StudentsResponse {

    private List<Student> data;

    public StudentsResponse(List<Student> data) {
        this.data = data;
    }

    public List<Student> getData() {
        return data;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }
}
