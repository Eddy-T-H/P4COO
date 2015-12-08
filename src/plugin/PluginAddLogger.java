package plugin;

/**
 * Class used to log when a plugin is added
 */
public class PluginAddLogger implements PluginEventListener {

	@Override
	public void pluginAdded(PluginAddedEvent e) {
		System.out.println(e.getFile());
	}



}
