package plugin;

import static org.junit.Assert.*;

import org.junit.Test;

import plugins.ConfigurableTimer;

public class ConfigurableTimerTest{

	@Test
	public void testConfigurableTimer() {
		ConfigurableTimer ct = new ConfigurableTimer(null , 5);
		assertEquals(5, ct.getIterationNbMax());
		ct.begin(2);
		assertEquals(ct.getIterationNbMax(), ct.getIterationsNb());
	}

}
