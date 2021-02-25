import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.ControllerSuiteClass;
import entities.EntitiesSuiteClass;
import entities.mapops.MapopsSuiteClass;

/**
 * Test suite to run all the test classes in the project
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerSuiteClass.class, EntitiesSuiteClass.class, MapopsSuiteClass.class })
public class AllTestsSuiteClass {

}
