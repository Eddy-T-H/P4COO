package plugins;

/**
 * Class used to log when a plugin is added
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class AddedPluginsLogger implements PluginEventListener {

	/**
	 * @see PluginEventListener#pluginAdded(PluginAddedEvent)
	 */
	@Override
	public void pluginAdded(PluginAddedEvent listener) {
		System.out.println(listener.getFile() + " plugin successfully added");
	}

}
