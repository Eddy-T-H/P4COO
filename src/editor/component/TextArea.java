package editor.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import editor.Editor;

/**
 * Class defining the Text Area
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class TextArea extends JTextArea implements DocumentListener {
	
	// Fields
	
	private static final long serialVersionUID = 1L;
	private Editor editor;
	
	// Methods
	
	/**
	 * Constructor for the TextArea class
	 * 
	 * @param editor : the Editor
	 */
	public TextArea(Editor editor) {
		this.editor = editor;
		this.getDocument().addDocumentListener(this);
	}

	/**
	 * Reads a File and replace the current content of the TextArea with the File content
	 * @param file
	 */
	public void readFile(File file) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.read(reader, null);
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error File Opening");
			e.printStackTrace();
		}
	}
	
	/**
	 * Write the content of the document to the File
	 * @param file
	 */
	public void writeFile(File file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			this.write(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see DocumentListener#changedUpdate(DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		this.editor.setNotSaved();
	}

	/**
	 * @see DocumentListener#insertUpdate(DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		this.editor.setNotSaved();
	}

	/**
	 * @see DocumentListener#removeUpdate(DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		this.editor.setNotSaved();
	}

}
