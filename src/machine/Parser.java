package machine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

	
	public Parser() {		
	}
	
	public Word[] load(String fileName, Word[] memory) {
		
		FileReader input;
		BufferedReader bufRead;
		try {
			input = new FileReader(fileName);
			bufRead = new BufferedReader(input);          
			String line;    // String that holds current file line
			
			// Read first line
			line = bufRead.readLine();
			if (! line.startsWith("DATA")) {
				//TODO BAD FILE
			}
			
			int cursor = 0;
			// read data segment
			while (line != null && !(line.equals("ENDDATA")) ){
				if (line.matches("\\$[0-9A-Fa-f]{2}:\\$.*$")) {
					cursor = Integer.parseInt(line.substring(1, 3), 16);
				}
				else if (line.startsWith("DB") || line.startsWith("db")) { //TODO decimal! operates only hex values
					String s = line.substring(3).replace(" ", "");
					if (s.length() > 4)
						s = s.substring(0, 4);
					memory[cursor].setWordHexInt(Integer.parseInt(s, 16));
				}
				else if (line.startsWith("DW") || line.startsWith("dw")) {
					String s = line.substring(3).replace(" ", "");
					if (s.length() > 4)
						s = s.substring(0, 4);
					memory[cursor].setWordString(s);
				}
				else
					continue;
				
			}
			
			// read code segment
			//TODO
		
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
