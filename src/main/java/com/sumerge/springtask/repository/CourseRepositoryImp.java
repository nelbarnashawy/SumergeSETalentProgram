package com.sumerge.springtask.repository;

import com.sumerge.springtask.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String selectByIdQuery = "select * from course where id = ?";
        Course course = template.queryForObject(selectByIdQuery, new Object[]{id}, Course.class);
        return course;
    }

    @Override
    public List<Course> findAll() {
        String selectQuery = "select * from course";
        RowMapper<Course> mapper = new RowMapper<Course>() {
            @Override
            public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course c = new Course();
                c.setCourse_id(rs.getInt(1));
                c.setCourse_name(rs.getString(2));
                c.setCourse_description(rs.getString(3));
                c.setCourse_credit(rs.getInt(4));
                return c;
            }
        };
        List<Course> courses = template.query(selectQuery, mapper);
        return courses;
    }

    @Override
    public void save(Course course) {
        String addQuery = "insert into course(name, description, credit) values(?,?,?)";
        template.update(addQuery, course.getCourse_name(), course.getCourse_description(), course.getCourse_credit());
    }

    @Override
    public void delete(Course course) {
        String deleteQuery = "delete from course where id = ?";
        template.update(deleteQuery, course.getCourse_id());
    }

    @Override
    public void update(Course course) {
        String updateQuery = "update course set course_name = ?, course_description = ?, course_credit = ? where id = ?";
        template.update(updateQuery, course.getCourse_name(), course.getCourse_description(), course.getCourse_credit(), course.getCourse_id());

    }
}
