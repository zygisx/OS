package test;

import static org.junit.Assert.*;

import machine.Word;

import org.junit.Test;

public class WordTest {

	@Test
	public void testWord() {
		Word w = new Word();
		assertTrue(w.getValue().length == 4);
	}

	@Test
	public void testWordCharArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testWordString() {
		fail("Not yet implemented");
	}

	@Test
	public void testWordIntBoolean() {
		fail("Not yet implemented");
	}

}
