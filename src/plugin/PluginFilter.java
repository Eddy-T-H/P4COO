package plugin;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Constructor;

/**
 * Class used to say whether a plugin is acceptable or not
 */
public class PluginFilter implements FilenameFilter {

	@Override
	public boolean accept(File dir, String name) {
		if (!fileExtensionIsClass(name)) {
			return false;
		}
		Class<?> theClass = getClass(name);
		if (theClass == null) {
			return false;
		}

		return inheritFromPlugin(theClass) && classInPluginPackage(theClass)
				&& classHasParameterlessClassConstructor(theClass);
	}

	/**
	 * Returns true is the file extension is ".class"
	 * 
	 * @param filename
	 *            the filename to analyse
	 * @return true is the file extension is ".class"
	 */
	protected boolean fileExtensionIsClass(String filename) {
		return filename.endsWith(".class");
	}

	/**
	 * Returns the class of the plugin
	 * 
	 * @param filename
	 *            the name of the file to analyse
	 * @return the class of the plugin
	 */
	protected Class<?> getClass(String filename) {
		String className = filename.replace("\\.class$", "");
		try {
			return Class.forName("plugins" + className);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * Returns true if theClass inherits from the plugin interface
	 * 
	 * @param theClass
	 *            the class to analyse
	 * @return true if theClass inherits from the plugin interface
	 */
	protected boolean inheritFromPlugin(Class<?> theClass) {
		return Plugin.class.isAssignableFrom(theClass);
	}

	/**
	 * Returns true if theClass is in the plugin package
	 * 
	 * @param theClass
	 *            the class to analyse
	 * @return true if theClass is in the plugin package
	 */
	protected boolean classInPluginPackage(Class<?> theClass) {
		return theClass.getPackage().getName().equals("plugins");
	}

	/**
	 * Returns true if the constructor of the class takes no parameter
	 * 
	 * @param theClass
	 *            the class to analyse
	 * @return true if the constructor of the class takes no parameter
	 */
	protected boolean classHasParameterlessClassConstructor(Class<?> theClass) {
		for (Constructor<?> constructor : theClass.getConstructors()) {
			if (constructor.getParameterTypes().length == 0) {
				return true;
			}
		}
		return false;
	}

}
