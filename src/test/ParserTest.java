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
		
		s = "DB 1258";
		String s1 = s.substring(3).replace(" ", "").substring(0, 4);
		assertEquals(s1, "1258");
		s = "DB         12581256teg";
		s1 = s.substring(3).replace(" ", "").substring(0, 4);
		assertEquals(s1, "1258");
		s = "DB 12";
		s1 = s.substring(3).replace(" ", "");
		if (s1.length() > 4)
			s1 = s1.substring(0, 4);
		assertEquals(s1, "12");
		
		s = "DW njihsdfvdoir";
		s1 = s.substring(3).replace(" ", "");
		if (s.length() > 4)
			s = s.substring(0, 4);
	}

}
