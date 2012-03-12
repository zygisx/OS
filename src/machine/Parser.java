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
			
			// Read through file one line at time. Print line # and line
			while (line != null){
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
