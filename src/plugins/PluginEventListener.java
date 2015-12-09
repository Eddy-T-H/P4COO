package plugins;

import java.util.EventListener;

import plugins.PluginAddedEvent;

/**
 * Interface extending EventListener to the plugin
 * 
 * @author E Thockler, A Blondin, H Chaumette
 */
public interface PluginEventListener extends EventListener{

	/**
	 * Executes an action when a plugin is added
	 * 
	 * @param listener : the listener which has to execute the action
	 */
	public void pluginAdded(PluginAddedEvent listener);
	
}
