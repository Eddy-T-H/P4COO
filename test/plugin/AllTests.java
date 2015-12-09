package plugin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConfigurableTimerTest.class, PluginFilterTest.class, PluginFinderTest.class })
public class AllTests {

}
