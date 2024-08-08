package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.model.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);

    Course courseDtoToCourse(CourseDTO courseDTO);

}
