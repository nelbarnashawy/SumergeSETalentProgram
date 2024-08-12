package com.sumerge.springtask.mapper;

import com.sumerge.springtask.dto.CourseDTO;
import com.sumerge.springtask.model.Course;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);

    Course courseDtoToCourse(CourseDTO courseDTO);

}
