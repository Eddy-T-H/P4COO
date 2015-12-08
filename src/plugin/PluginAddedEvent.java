package plugin;

import java.io.File;

import plugin.Plugin;

/**
 * Class used to launch an event where a plugin is added
 */
public class PluginAddedEvent implements Plugin {
	private File file;
	protected Plugin instance;

	/**
	 * Constructor for the PluginAddedEvent class
	 * 
	 * @param file
	 *            the plugin to load
	 */
	public PluginAddedEvent(File file) {
		ClassLoader loader = new PluginLoader();
		Class<?> plugin;
		if (file == null || !file.exists())
			return;
		this.file = file;
			try {
				String pluginName = "plugins."
						+ file.getName().replaceFirst("\\.class$", "");

				plugin = loader.loadClass(pluginName);

				this.instance = (Plugin) plugin.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Returns the name of the file
	 * 
	 * @return the name of the file
	 */
	public String getFile() {
		return file.getName();
	}

	/**
	 * Returns a string transformed from s by the plugin
	 * 
	 * @return Returns a string transformed from s by the plugin
	 */
	public String transform(String s) {
		return this.instance.transform(s);
	}

	/**
	 * Returns the name of the plugin
	 * 
	 * @return the name of the plugin
	 */
	public String getLabel() {
		return this.instance.getLabel();
	}

	/**
	 * Returns the help message of the plugin
	 * 
	 * @return the help message of the plugin
	 */
	public String helpMessage() {
		return this.instance.helpMessage();
	}

	/**
	 * Internal Class used to load a plugin
	 */
	public class PluginLoader extends ClassLoader {

	}

}
