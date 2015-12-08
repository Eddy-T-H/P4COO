package plugin;

public interface Plugin {

	/** the package name of plugins */
	public static final Object PACKAGE_NAME = "plugins";

	/**
	 * performs a transformation on a string
	 * 
	 * @param s
	 *            the string to be transformed
	 * @return the transformed string
	 */
	public String transform(String s);

	/**
	 * returns a label associated to this plugin.
	 * 
	 * @return a label associated to this plugin.
	 */
	public String getLabel();

	/**
	 * a help message describing the plugin transformation operation
	 * 
	 * @return a help message describing the plugin transformation operation
	 */
	public String helpMessage();

}
