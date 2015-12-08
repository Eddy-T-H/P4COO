/**
 * 
 */
package editor;


import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import editor.component.FileMenu;
import editor.component.HelpMenu;
import editor.component.TextArea;
import editor.component.ToolsMenu;
import plugins.AddedPluginsLogger;
import plugins.PluginFinder;

@SuppressWarnings("serial")
public class Editor extends JFrame {
	
	// Fields

	protected TextArea textArea;
	protected File file;
	protected boolean saved;
	protected static final String TITLE = "Extendable Editor";
	protected final File dropins = new File("dropins/plugins");
	protected final PluginFinder finder = new PluginFinder(dropins);
	protected AddedPluginsLogger apl = new AddedPluginsLogger();
	
	// Methods

	/**
	 * Constructor for the Editor class
	 */
	public Editor() {
		
		/* Menu Bar */
		JMenuBar menuBar = new JMenuBar();		
		this.setJMenuBar(menuBar);
		
		/* Menu File */
		FileMenu fileMenu = new FileMenu(this);
		menuBar.add(fileMenu);
		
		/* Menu Tools */
		ToolsMenu tools = new ToolsMenu(this);
		this.finder.addListener(tools);
		menuBar.add(tools);
		
		/* Menu Help */
		HelpMenu help = new HelpMenu(this);
		this.finder.addListener(help);
		menuBar.add(help);

		/* Text Area */
		this.textArea = new TextArea(this);
		JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
		this.add(scroll);
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setTitle("Extendable Editor");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.finder.addListener(apl);
		this.saved = true;
		if(!this.dropins.exists())
			this.dropins.mkdir();
		this.updateTitle();
		this.finder.start();
		
	}
	
	/**
	 * Updates the title of the frame
	 */
	public void updateTitle() {
		String tmp = "";
		if(!this.saved)
			tmp += "*";
		if(this.file == null)
			tmp += "untitled";
		else
			tmp += this.file.getName();
		tmp += " - " + Editor.TITLE;
		this.setTitle(tmp);
	}
	
	/**
	 * Resets the editor to write on a new document
	 */
	public void reset() {
		this.textArea.setText(null);
		this.file = null;
		this.saved = true;
		this.updateTitle();
	}
	/**
	 * Saves the document to the output if it is known.<br>
	 * If the output is unknown, call saveAs method.
	 */
	public void save() {
		if (this.file == null)
			this.chooseFile();
		if (this.file != null) {
			if (!this.file.exists())
				try {
					this.file.createNewFile();
				} catch (IOException e) {
					System.out.println("erreur lors de la création du fichier"
							+ this.file.getAbsolutePath());
					e.printStackTrace();
				}
			this.textArea.writeFile(this.file);
			this.saved = true;
		}
		this.updateTitle();
	}
	
	/**
	 * Asks the user to choose an output file and saves to it
	 */
	public void saveAs() {
		this.chooseFile();
		if (this.file != null) {
			if (!this.file.exists())
				try {
					this.file.createNewFile();
				} catch (IOException e) {
					System.out.println("erreur lors de la création du fichier"
							+ this.file.getAbsolutePath());
					e.printStackTrace();
				}
			this.textArea.writeFile(this.file);
			this.saved = true;
		}
		this.updateTitle();
	}
	
	/**
	 * Chooses and opens an existing file and fills textArea with the content of this file
	 */
	public void open() {
		this.chooseFile();
		if(this.file != null)
			this.loadFile();
	}
	
	/**
	 * Loads this.file in textArea if this.file is not null
	 */
	public void loadFile() {
		if(this.file.exists()) {
			this.textArea.readFile(this.file);
			this.saved = true;
			this.updateTitle();
		}
	}
	
	/**
	 * Chooses an existing file or creates one with a FileChooser
	 */
	public void chooseFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(fc);
		if(returnVal == JFileChooser.APPROVE_OPTION)
			this.file = fc.getSelectedFile();
	}
	
	/**
	 * Sets <i>saved</i> to false
	 */
	public void setNotSaved() {
		this.saved = false;
		this.updateTitle();
	}
	
	/**
	 * Returns the text selected in the TextArea
	 * 
	 * @return the text selected in the TextArea
	 */
	public String getSelectedText() {
		return this.textArea.getSelectedText();
	}
	
	/**
	 * Replaces the text selected in the TextArea by <i>transform</i>
	 * 
	 * @param transform : the text to write
	 */
	public void replaceSelection(String transform) {
		this.textArea.replaceSelection(transform);
	}
	
	/**
	 * Shows a helping message
	 * 
	 * @param title : Plugin name
	 * @param message : Plugin help message
	 */
	public void helpMessage(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Main method of the Editor
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Editor();
	}
}
