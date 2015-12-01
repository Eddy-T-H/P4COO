package plugin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PluginFilterTest {

	@Test
	public void testIfHasGoodExtent() {
		PluginFilter filter = new PluginFilter();
		assertTrue(filter.fileExtensionIsClass("fichier.class"));
		assertFalse(filter.fileExtensionIsClass("fichier.nope"));
	}
}
