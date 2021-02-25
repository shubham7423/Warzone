package entities.mapops;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all the test classes in mapops package
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MapValidationTest.class, ReadMapTest.class, WriteMapTest.class })
public class MapopsSuiteClass {

}
