package plugin;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import plugins.PluginFinder;

public class PluginFinderTest {

	@Test
	public void testPluginFinder() {
		PluginFinder pf = new PluginFinder(new File("test/plugins"));
		assertEquals(2, pf.listFiles().size()) ;
	}

}
