import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.ControllerSuiteClass;
import entities.EntitiesSuiteClass;
import entities.mapops.MapopsSuiteClass;
import entities.orders.AllTests;

/**
 * Test suite to run all the test classes in the project
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerSuiteClass.class, EntitiesSuiteClass.class, MapopsSuiteClass.class, AllTests.class })
public class AllTestsSuiteClass {

}
