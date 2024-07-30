package com.sumerge.dependencyinjectiontask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTaskApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringTaskApplication.class, args);
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		CourseService service = context.getBean(CourseService.class);
		service.recommendCourse();

	}

}
