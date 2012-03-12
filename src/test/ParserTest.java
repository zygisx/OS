package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testLoad() {
		String s = "$00:$";
		assertTrue(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "$000:$";
		assertFalse(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "$0:$";
		assertFalse(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "$69:$ ";
		assertTrue(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "$6f:$   \t ";
		assertTrue(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "0$6f:$   \t";
		assertFalse(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		s = "$6f:$   \t";
		assertTrue(s.matches("\\$[0-9A-Fa-f]{2}:\\$.*$"));
		assertEquals("6f", s.substring(1, 3));
	}

}
