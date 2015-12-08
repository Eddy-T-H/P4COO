package editor.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import plugin.Plugin;
import plugin.PluginAddedEvent;
import plugin.PluginEventListener;
import editor.Editor;

/**
 * Class defining the Help Menu
 */
public class HelpMenu extends JMenu implements PluginEventListener {
	private static final long serialVersionUID = 1L;
	private Editor editor;

	/**
	 * Constructor for the HelpMenu class
	 * 
	 * @param editor
	 *            the Editor which is the main window class
	 */
	public HelpMenu(Editor editor) {
		super("Help");
		this.editor = editor;
	}

	@Override
	public void pluginAdded(PluginAddedEvent plugin) {
		JMenuItem menuItem = new JMenuItem(plugin.getLabel());
		menuItem.addActionListener(new HelpListener(plugin, this.editor));
		this.add(menuItem);
	}

	/**
	 * Internal Class used to manage the addition of a plugin to the help menu
	 */
	public class HelpListener implements ActionListener {

		private Editor editor;
		private Plugin plugin;

		/**
		 * Constructor for the HelpListener class
		 * 
		 * @param plugin
		 * @param editor
		 *            the Editor which is the main window class
		 */
		public HelpListener(Plugin plugin, Editor editor) {
			this.editor = editor;
			this.plugin = plugin;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.editor.confirm(this.plugin.getLabel(),
					this.plugin.helpMessage());
		}
	}
}
