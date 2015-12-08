package plugins;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class used to launch an event where a plugin is added
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class PluginAddedEvent implements Plugin {

	// Fields
	
	private File file;
	protected Plugin instance;
	
	/**
	 * Constructor for the PluginAddedEvent class
	 * 
	 * @param file : the plugin to load
	 */
	@SuppressWarnings({ "deprecation", "resource" })
	public PluginAddedEvent(File file) {
		Class<?> plugin;
		if(file == null || !file.exists())
			return;
		this.file = file;
		try {
			String pluginName = file.getName().replaceFirst(".class", "");
			URL[] classLoaderUrls = new URL[]{new URL(file.getParentFile().getParentFile().toURL().toString())};
			URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
			plugin = urlClassLoader.loadClass("plugins." + pluginName);
			this.instance = (Plugin) plugin.newInstance();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
	 * @return a string transformed from s by the plugin
	 */
	@Override
	public String transform(String s) {
		return this.instance.transform(s);
	}

	/**
	 * Returns the name of the plugin
	 * 
	 * @return the name of the plugin
	 */
	@Override
	public String getLabel() {
		return this.instance.getLabel();
	}

	/**
	 * Returns the help message of the plugin
	 * 
	 * @return the help message of the plugin
	 */
	@Override
	public String getHelp() {
		return this.instance.getHelp();
	}

}
