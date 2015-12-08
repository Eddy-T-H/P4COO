package editor.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import editor.Editor;
import plugins.Plugin;
import plugins.PluginAddedEvent;
import plugins.PluginEventListener;

/**
 * Class defining the Help Menu
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class HelpMenu extends JMenu implements PluginEventListener {
	
	// Fields
	
	private static final long serialVersionUID = 1L;
	private Editor editor;
	
	// Methods
	
	/**
	 * Constructor for the HelpMenu class
	 * 
	 * @param editor : the Editor
	 */
	public HelpMenu(Editor editor) {
		super("Help");
		this.editor = editor;
	}

	/**
	 * @see PluginEventListener#pluginAdded(PluginAddedEvent)
	 */
	@Override
	public void pluginAdded(PluginAddedEvent plugin) {
		JMenuItem menuItem = new JMenuItem(plugin.getLabel());
		menuItem.addActionListener(new HelpListener(plugin, this.editor));
		this.add(menuItem);
	}
	
	/**
	 * Internal class used to manage the addition of a plugin to the help menu
	 */
	public class HelpListener implements ActionListener {
		
		// Fields
		
		private Editor editor;
		private Plugin plugin;
		
		// Methods
		
		/**
		 * Constructor for the HelpListener class
		 * 
		 * @param plugin : the added Plugin
		 * @param editor : the editor
		 */
		public HelpListener(Plugin plugin, Editor editor) {
			this.editor = editor;
			this.plugin = plugin;
		}

		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.editor.helpMessage(this.plugin.getLabel(), this.plugin.getHelp());
		}
		
	}
	
}
