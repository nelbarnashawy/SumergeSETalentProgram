package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("courseRepo")
public class CourseRepositoryImp implements CourseRepository {

    private JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Course findById(int id) {
        return new Course();
    }

    @Override
    public List<Course> findAll() {
        String sql = "select * from course";
        ArrayList<Course> courses = (ArrayList<Course>) template.query(sql, new BeanPropertyRowMapper<Course>(Course.class));
        return courses;
    }

    @Override
    public void save(Course course) {
        String addQuery = "insert into course values(?,?,?,?)";
        template.update(addQuery, course.getCourse_id(), course.getCourse_name(), course.getCourse_description(), course.getCourse_credit());
    }

    @Override
    public void delete(Course course) {

    }

    @Override
    public void update(Course course) {

    }
}
