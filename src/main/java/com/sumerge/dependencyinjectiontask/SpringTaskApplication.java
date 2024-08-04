package com.sumerge.dependencyinjectiontask;


import com.sumerge.dependencyinjectiontask.configuration.CourseRecommenderConfig;
import com.sumerge.dependencyinjectiontask.service.CourseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringTaskApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(CourseRecommenderConfig.class);
		CourseService service = context.getBean(CourseService.class);
		service.recommendCourse();

	}

}
