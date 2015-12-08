package editor.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import editor.Editor;

/**
 * Class defining the File Menu
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class FileMenu extends JMenu {

	// Fields
	
	private static final long serialVersionUID = 1L;
	
	// Methods
	
	/**
	 * Constructor for the FileMenu class
	 * 
	 * @param editor : the Editor
	 */
	public FileMenu(Editor editor) {
		super("File");
		JMenuItem newFile = new JMenuItem("New");
		newFile.addActionListener(new NewFileEvent(editor));
		this.add(newFile);
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new OpenFileEvent(editor));
		this.add(open);
		
		this.add(new JSeparator());
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new SaveFileEvent(editor));
		this.add(save);
		
		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.addActionListener(new SaveAsFileEvent(editor));
		this.add(saveAs);
		
		this.add(new JSeparator());
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitEvent());
		this.add(exit);
	}
	
	/**
	 * Internal Class used to manage the app reset when the user chooses the New item in the Menu
	 */
	private class NewFileEvent implements ActionListener {
		
		// Fields
		
		private Editor editor;
		
		// Methods

		/**
		 * Constructor for the NewFileEvent class
		 * @param editor
		 */
		public NewFileEvent(Editor editor) {
			this.editor = editor;
		}
		
		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			this.editor.reset();
		}
		
	}
	
	/**
	 * Internal Class used to manage the opening of a document when the user chooses the Open item in the Menu
	 */
	private class OpenFileEvent implements ActionListener {
		
		// Fields
		
		private Editor editor;

		// Methods
		
		/**
		 * Constructor for the OpenFileEvent class
		 * 
		 * @param editor : the Editor
		 */
		public OpenFileEvent(Editor editor) {
			this.editor = editor;
		}
		
		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			this.editor.open();
		}
		
	}
	
	/**
	 * Internal Class used to manage the save of the document when the user chooses the Save item in the Menu
	 */
	private class SaveFileEvent implements ActionListener {
		
		// Fields
		
		private Editor editor;
		
		// Methods
		
		/**
		 * Constructor for the SaveFileEvent class
		 * 
		 * @param editor : the Editor
		 */
		public SaveFileEvent(Editor editor) {
			this.editor = editor;
		}

		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.editor.save();
		}
	}
	
	/**
	 * Internal Class used to manage the save of the document when the user chooses the Save As item in the Menu
	 * @author Luigi
	 *
	 */
	private class SaveAsFileEvent implements ActionListener {
		
		// Fields
		
		private Editor editor;
		
		// Methods
		
		/**
		 * Constructor for the SaveAsFileEvent class
		 * 
		 * @param editor : the Editor
		 */
		public SaveAsFileEvent(Editor editor) {
			this.editor = editor;
		}

		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			this.editor.saveAs();
		}
		
	}
	
	/**
	 * Internal Class used to manage the app closing when the user chooses the
	 * Exit item in the Menu
	 */
	class ExitEvent implements ActionListener {

		/**
		 * @see ActionListener#actionPerformed(ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

}
