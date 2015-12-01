package editor;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import javax.swing.text.BadLocationException;

import org.junit.Test;

public class EditorTest {

	@Test
	public void testUpdateTitle() {
		Editor editor = new Editor();
		assertFalse(editor.frame.getTitle().contains("*"));
		editor.setNotSaved();
		editor.updateTitle();
		assertTrue(editor.frame.getTitle().contains("*"));
		editor.file = new File("test/EditorTest.java");
		editor.updateTitle();
		assertTrue(editor.frame.getTitle().contains("EditorTest.java"));
	}

	@Test
	public void testReset() throws BadLocationException {
		Editor editor = new Editor();
		editor.file = new File("test/EditorTest.java");
		editor.loadFile();
		editor.setNotSaved();
		assertTrue(editor.frame.getTitle().contains("*"));
		assertTrue(editor.frame.getTitle().contains("EditorTest.java"));
		editor.reset();
		assertFalse(editor.frame.getTitle().contains("*"));
		assertFalse(editor.frame.getTitle().contains("EditorTest.java"));
		assertEquals(0, editor.textarea.getDocument().getLength());
	}

}
