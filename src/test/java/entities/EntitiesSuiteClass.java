package entities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all the test classes in entities package
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ContinentTest.class, CountryTest.class, DeployTest.class, GameMapTest.class, PlayerTest.class })
public class EntitiesSuiteClass {

}
