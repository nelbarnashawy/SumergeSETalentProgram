package com.sumerge.springtask;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.sumerge.springtask.tests")
@IncludeTags("integration-test")
public class IntegrationTestsSuite {

}
