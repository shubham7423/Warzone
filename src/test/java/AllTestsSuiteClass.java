import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import controller.ControllerSuiteClass;
import entities.EntitiesSuiteClass;
import entities.mapops.MapopsSuiteClass;

@RunWith(Suite.class)
@SuiteClasses({ControllerSuiteClass.class, EntitiesSuiteClass.class, MapopsSuiteClass.class})
public class AllTestsSuiteClass {

}
