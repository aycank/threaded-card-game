package software_dev_ca;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;;

@RunWith(Suite.class)
@SuiteClasses({NumberOfPlayerInputTest.class,GetCardFromPackTest.class,ReadFileMethodTest.class,WriteFileMethodTest.class,ClearFileMethodTest.class})
public class TestAllUserInput {
}
