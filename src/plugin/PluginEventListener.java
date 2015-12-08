package plugin;

import java.util.EventListener;

/**
 * Interface extending EventListener to the plugins
 */
public interface PluginEventListener extends EventListener {

	/**
	 * Executes an Action when a plugin is added
	 * 
	 * @param listener
	 *            the listener having to execute the action
	 */
	public void pluginAdded(PluginAddedEvent listener);

}
