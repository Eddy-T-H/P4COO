package plugin;

/**
 * Class used to log when a plugin is added
 * @author Maiz Nabil
 * @author Cojez Arnaud
 */
public class PluginAddLogger implements PluginEventListener {

	/*
	 * (non-Javadoc)
	 * @see plugin.PluginEventListener#pluginAdded(plugin.PluginAddedEvent)
	 */
	@Override
	public void pluginAdded(PluginAddedEvent e) {
		System.out.println(e.getFile());
	}



}
