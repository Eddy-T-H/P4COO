package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class used to  find the plugins
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class PluginFinder implements ActionListener{
	
	// Fields
	
	private final int REFRESHING_INTERVAL = 1000;
	protected final File dir;
	protected final ConfigurableTimer timer;
	protected final List<PluginEventListener> listeners = new ArrayList<PluginEventListener>();
	protected PluginFilter filter;
	protected Set<File> knownFiles = new HashSet<File>();
	
	// Methods
	
	/**
	 * Constructor for the 
	 * @param dir
	 */
	public PluginFinder(File dir) {
		this.dir = dir;
		this.timer = new ConfigurableTimer(this);
	}
	
	/**
	 * Returns a Set containing the files in the directory
	 * 
	 * @return a Set containing the files in the directory
	 */
	public Set<File> listFiles() {
		return new HashSet<File>(Arrays.asList(dir.listFiles(filter)));
	}
	
	/**
	 * Adds a plugin to the listeners
	 * 
	 * @param file : File
	 */
	protected void notifyListener(File file) {
		ArrayList<PluginEventListener> listenerCopy;
		listenerCopy = new ArrayList<PluginEventListener>(listeners);
		for(PluginEventListener listener : listenerCopy) {
			listener.pluginAdded(new PluginAddedEvent(file));
		}
	}
	
	/**
	 * Starts the timer
	 */
	public void start() {
		timer.start(REFRESHING_INTERVAL);
	}
	
	/**
	 * Adds a listener to this
	 * 
	 * @param listener : the listener to add
	 */
	public synchronized void addListener(PluginEventListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		Set<File> currentFiles = this.listFiles();
		Set<File> newFiles = new HashSet<File>(currentFiles);
		newFiles.removeAll(knownFiles);
		for(File file : newFiles) {
			notifyListener(file);
		}
		knownFiles = currentFiles;
	}

}
