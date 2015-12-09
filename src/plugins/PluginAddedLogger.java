package plugins;

/**
 * Class used to log when a plugin is added
 * 
 * @author E Thockler, A Blondin, H Chaumette
 */
public class PluginAddedLogger implements PluginEventListener {

	/**
	 * @see PluginEventListener#pluginAdded(PluginAddedEvent)
	 */
	@Override
	public void pluginAdded(PluginAddedEvent listener) {
		System.out.println(listener.getFile() + " plugin loaded");
	}

}
