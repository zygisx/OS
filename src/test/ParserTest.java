package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testLoad() {
		String s = "$00:$";
		assertTrue(s.matches("\\$\\d{2}:\\$"));
		s = "$000:$";
		assertFalse(s.matches("\\$\\d{2}:\\$"));
		s = "$0:$";
		assertFalse(s.matches("\\$\\d{2}:\\$"));
		s = "$69:$";
		assertTrue(s.matches("\\$\\d{2}:\\$"));
		
	}

}
