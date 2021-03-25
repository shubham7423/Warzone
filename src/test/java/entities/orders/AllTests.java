package entities.orders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AdvanceTest.class, AirliftTest.class, BlockadeTest.class, BombTest.class, DeployTest.class,
		DiplomacyTest.class })
public class AllTests {

}
