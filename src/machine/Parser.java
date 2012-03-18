package machine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.BadFileException;

public class Parser {

	
	private Parser() {		
	}
	
	public static Word[] load(String fileName, Word[] memory) 
			throws BadFileException {
		
		FileReader input;
		BufferedReader bufRead;
		try {
			input = new FileReader(fileName);
			bufRead = new BufferedReader(input);          
			String line;    // String that holds current file line
			
			// Read first line
			line = bufRead.readLine();
			if (! line.startsWith("DATA")) {
				throw new BadFileException("File must start with DATA");
			}
			
			
			int cursor = 0;
			// read data segment
			while ( (line = bufRead.readLine() )!= null && 
					!(line.startsWith("ENDDATA")) ) {
				if (line.matches("\\$[0-9A-Fa-f]{2}:\\$.*$")) {
					cursor = Integer.parseInt(line.substring(1, 3), 16);
					continue;
				}
				else if (line.startsWith("DW") || line.startsWith("dw")) { //TODO decimal! operates only hex values
					String s = line.substring(3).replace(" ", "");
					if (s.length() > 4)
						s = s.substring(0, 4);
					System.out.println(Integer.parseInt(s, 16) + "  " + cursor);
					memory[cursor].setWordHexInt(Integer.parseInt(s, 16));
				}
				else if (line.startsWith("DB") || line.startsWith("db")) {
					// deleted spaces removal
					String s = line.substring(3);
					if (s.length() > 4)
						s = s.substring(0, 4);
					memory[cursor].setWordString(s);
				}
				else
					continue;
				cursor++;
			}
			if (line == null) {
				throw new BadFileException("Bad file. No code segment.");
			}
			
			line = bufRead.readLine();
			if (! line.startsWith("CODE")) {
				throw new BadFileException("Bad file. Code segment must start with CODE.");
			}
			
			
			// read code segment
			cursor = 0x80;
			while ( (line = bufRead.readLine() )!= null && 
					!(line.startsWith("ENDCODE")) ) {
				memory[cursor++].setWordString(line.substring(0, 4));
			}
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memory;
		
	}
}
