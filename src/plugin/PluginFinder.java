package plugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class used to Find the plugins
 */
public class PluginFinder implements ActionListener {

	private static final int REFRESH_INTERVAL_MS = 3000;
	protected final File directory;
	protected final List<PluginEventListener> listeners = new ArrayList<PluginEventListener>();
	protected final ConfigurableTimer timer;
	protected Set<File> knownFiles = new HashSet<File>();
	protected PluginFilter filter;
	
	/**
	 * Constructor for the PluginFinder class
	 * 
	 * @param directory
	 *            the directory to search plugins in
	 */
	public PluginFinder(File directory) {
		super();
		this.directory = directory;
		this.timer = new ConfigurableTimer(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Set<File> currentFiles = this.listFiles();
		Set<File> newFiles = new HashSet<File>(currentFiles);
		newFiles.removeAll(knownFiles);

		for (File file : newFiles) {
			notifyListener(file);
		}
		knownFiles = currentFiles;
	}

	/**
	 * Returns a Set containing the files in the directory
	 * 
	 * @return a Set containing the files in the directory
	 */
	public Set<File> listFiles() {
		return new HashSet<File>(Arrays.asList(directory.listFiles(filter)));
	}

	/**
	 * Adds a plugin to the listeners
	 * 
	 * @param file
	 */
	protected void notifyListener(File file) {
		ArrayList<PluginEventListener> listenerCopy;
		listenerCopy = new ArrayList<PluginEventListener>(listeners);
		for (PluginEventListener listener : listenerCopy) {
			listener.pluginAdded(new PluginAddedEvent(file));
		}
	}

	/**
	 * Starts the timer
	 */
	public void start() {
		timer.start(REFRESH_INTERVAL_MS);
	}

	/**
	 * Adds a listener to this
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public synchronized void addListener(PluginEventListener listener) {
		this.listeners.add(listener);
	}
}
