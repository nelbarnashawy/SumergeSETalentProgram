package com.sumerge.springtask.tests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({AuthorControllerIntegrationTest.class, CourseControllerIntegrationTest.class})
public class IntegrationTestsSuite {

}
