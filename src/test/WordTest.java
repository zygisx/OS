package test;

import static org.junit.Assert.*;

import machine.Word;

import org.junit.Test;

public class WordTest {

	@Test
	public void testWord() {
		Word w = new Word();
		assertTrue(w.getValue().length == Word.WORD_SIZE);
	}

	@Test
	public void testWordCharArray() {
		char[] array, result;
		/* Good case */
		array = new char[] {
			'k', 'l', 'k', 'j'
		};
		Word w = new Word(array);
		result = w.getValue();
		assertArrayEquals(array, result);
		assertTrue(result.length == Word.WORD_SIZE);
		/*END*/
		
		/* Bad case */
		w = null;
		array = new char[] { 
				'h', 'r', 't'
		};
		w = new Word(array);
		result = w.getValue();
		assertNull(result);
	}

	@Test
	public void testWordString() {
		Word w = new Word("ciupakabra");
		char[] array = new char[] {
			'c', 'i', 'u', 'p'
		};
		assertArrayEquals(w.getValue(), array);
		w = new Word("ciup");
		assertArrayEquals(w.getValue(), array);
		w = new Word("ciu");
		assertEquals(" ciu", w.getStringValue());
		w.setWordString("15");
		assertEquals("  15", w.getStringValue());
		
	}

	@Test
	public void testWordIntBoolean() {
		/* Hex cases */
		Word w = new Word(0x1234, true);
		assertEquals("1234", w.getStringValue());
		w = new Word(0x123, true);
		assertEquals("0123", w.getStringValue());
		w = new Word(0xbf, true);
		assertEquals("00bf", w.getStringValue());
		w = new Word(0xbf145, true);
		assertEquals("bf14", w.getStringValue());
		w = new Word(0x0, true);
		assertEquals("0000", w.getStringValue());
		
		/* decimal cases */
		w = new Word(1234, false);
		assertEquals("1234", w.getStringValue());
		w = new Word(123, false);
		assertEquals("0123", w.getStringValue());
		w = new Word(1, false);
		assertEquals("0001", w.getStringValue());
		w = new Word(12345, false);
		assertEquals("1234", w.getStringValue());
		// test fails!!! What to do with negative numbers ?
		//w = new Word(-1, false);
		//assertEquals("00?1", w.getStringValue());
	}
	@Test
	public void testGetDecimalValue() {
		// decimal
		Word w = new Word("1234");
		w.setHexValues(false);
		assertEquals(1234, w.getDecimalValue());
		w.setWordString("50000");
		assertEquals(5000, w.getDecimalValue());
		
		// hex
		w.setWordHexInt(0xf);
		assertEquals("000f", w.getStringValue());
		assertEquals(15, w.getDecimalValue());
		w.setWordHexInt(0xf0);
		assertEquals(240, w.getDecimalValue());
		
	}
	@Test
	public void testGetHexValue()
	{
		// decimal
		assertEquals(0xc, 12);
		Word w = new Word();
		w.setHexValues(false);
		w.setWordString("12");
		assertEquals(0xc, w.getHexValue());
		w.setWordDecInt(32);
		assertEquals(0x20, w.getHexValue());
		
		// hex
		w.setWordHexInt(0xff);
		assertEquals(0xff, w.getHexValue());
		
		w.setWordHexInt(Integer.parseInt("10", 16));
		assertEquals(w.getStringValue(), "0010");
		assertEquals(w.getHexValue(), 0x10);
		
	}

}
