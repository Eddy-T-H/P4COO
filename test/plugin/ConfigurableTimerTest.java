package plugin;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurableTimerTest{

	@Test
	public void testConfigurableTimer() {
		ConfigurableTimer ct = new ConfigurableTimer(null , 5);
		assertEquals(5, ct.maxIteration);
		ct.start(2);
		assertEquals(ct.maxIteration, ct.nbIterations);
	}

}
