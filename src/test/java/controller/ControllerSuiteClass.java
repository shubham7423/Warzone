package controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all the test classes in controller package
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CommandsTest.class, GameStarterTest.class })
public class ControllerSuiteClass {

}