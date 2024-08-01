package com.sumerge.springtask;

import com.sumerge.springtask.model.Course;
import com.sumerge.springtask.repository.CourseRepository;
import com.sumerge.springtask.service.CourseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class SpringTaskApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringTaskApplication.class, args);


		//		ApplicationContext context =
//		CourseService service = context.getBean(CourseService.class);

//		Course course1 = context.getBean(Course.class);

//		course1.setCourse_name("Math");
//		course1.setCourse_description("Math101 Course");
//		course1.setCourse_credit(8);

//		service.delete(service.findById(1));

		//service.save(course1);
//		System.out.println(service.findAll());


	}

}
