package entities.orders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite to run all the test classes of orders package
 */
@RunWith(Suite.class)
@SuiteClasses({ AdvanceTest.class, AirliftTest.class, BlockadeTest.class, BombTest.class, DeployTest.class,
		DiplomacyTest.class })
public class AllTests {

}
