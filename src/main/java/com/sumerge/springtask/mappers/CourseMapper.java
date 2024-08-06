package com.sumerge.springtask.mappers;

import com.sumerge.springtask.DTOs.CourseDTO;
import com.sumerge.springtask.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "course_name", target = "course_name")
    @Mapping(source = "course_description", target = "course_description")
    @Mapping(source = "course_credit", target = "course_credit")
    public CourseDTO CoursetoCourseDTO(Course course);

    @Mapping(source = "course_name", target = "course_name")
    @Mapping(source = "course_description", target = "course_description")
    @Mapping(source = "course_credit", target = "course_credit")
    public Course CourseDTOtoCourse(CourseDTO courseDTO);
}
