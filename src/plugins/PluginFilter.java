package plugins;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Class used to determine if a plugin is acceptable or not
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class PluginFilter implements FilenameFilter {

	/**
	 * @see FilenameFilter#accept(File, String)
	 */
	@Override
	public boolean accept(File dir, String name) {
		if(!fileExtensionIsClass(name))
			return false;
		Class<?> c = getClass(dir, name);
		if(c == null)
			return false;
		return inheritFromPlugin(c) && classInPluginPackage(c) && classHasParameterlessConstructor(c);
	}

	/**
	 * Returns true if the file extension is ".class"
	 * 
	 * @param name : the file name to analyze
	 * @return true if the file extension is ".class"
	 */
	public boolean fileExtensionIsClass(String name) {
		return name.endsWith(".class");
	}
	
	/**
	 * Returns the class of the plugin
	 * 
	 * @param dir : the directory where the plugins are
	 * @param className : the name of the file to analyze
	 * @return the class of the plugin
	 */
	public Class<?> getClass(File dir, String className) {
		className = className.substring(0, className.length()-6);
		try {
			URL[] classLoaderUrls = new URL[]{new URL(dir.toURL().toString())};
			URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
			return urlClassLoader.loadClass("plugins." + className);
		} catch (ClassNotFoundException e) {
			return null;
		} catch (MalformedURLException e) {
			return null;
		}
	}
	
	/**
	 * Returns true if the class inherits from the plugin interface
	 * 
	 * @param c : the class to analyze
	 * @return true if the class inherits from the plugin interface
	 */
	protected boolean inheritFromPlugin(Class<?> c) {
		return Plugin.class.isAssignableFrom(c);
	}
	
	/**
	 * Returns true if the class is in the plugin package
	 * 
	 * @param c : the class to analyze
	 * @return true if the class is in the plugin package
	 */
	protected boolean classInPluginPackage(Class<?> c) {
		return c.getPackage().getName().equals("plugins");
	}
	
	/**
	 * Returns true if the constructor of the class takes no parameter
	 * 
	 * @param c : the class to analyze
	 * @return true if the constructor of the class takes no parameter
	 */
	protected boolean classHasParameterlessConstructor(Class<?> c) {
		for(Constructor<?> constr : c.getConstructors()) {
			if(constr.getParameterTypes().length == 0)
				return true;
		}
		return false;
	}

}
