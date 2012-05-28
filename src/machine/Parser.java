package machine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import exception.BadFileException;

public class Parser {

	
	private Parser() {		
	}
	
	/**
	 * DEPRECATED
	 */
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
					int commentStarts = s.indexOf("#");
					if (commentStarts >= 0) {
						s = s.substring(0, commentStarts);
					}
					if (s.length() > 4)
						s = s.substring(0, 4);
					s = s.replaceAll("\\s+", "");
					//System.out.println(Integer.parseInt(s, 16) + "  " + cursor);
					memory[cursor].setWordHexInt(Integer.parseInt(s, 16));
				}
				else if (line.startsWith("DB") || line.startsWith("db")) {
					// deleted spaces removal
					String s = line.substring(3);
					int commentStarts = s.indexOf("#");
					if (commentStarts >= 0) {
						s = s.substring(0, commentStarts);
					}
					if (s.length() > 4)
						s = s.substring(0, 4);
					//s = s.replaceAll("\\s+", "");
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
			int commentStarts;
			while ( (line = bufRead.readLine() )!= null && 
					!(line.startsWith("ENDCODE")) ) {
				commentStarts = line.indexOf("#");
				if (commentStarts >= 0) {
					line = line.substring(0, commentStarts);
				}
				line = line.replaceAll("\\s+", "");
				//line = line.trim();
//				System.out.println(line);
				if (line.length() < 4)
					memory[cursor++].setWordString(String.format("%1$-4s", line));
				else
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
	
	/**
	 * method validates task and return separated data and code segments
	 * @param task
	 * @return
	 * @throws BadFileException
	 */
	public static String[] validateTask(String task)  throws BadFileException {
		
		BufferedReader bf = new BufferedReader(new StringReader(task));
		String data = "";
		String code = "";
		
		try {
			String line = bf.readLine();
			
			if (! line.startsWith("DATA")) {
				//Kernel.getResources().create(new Resource("jclerror File must start with DATA", this.id));
				return null;
			}
			
			while ( (line = bf.readLine() )!= null && 
					!(line.startsWith("ENDDATA")) ) {
				if (line.matches("\\$[0-9A-Fa-f]{2}:\\$.*$")) {
					data += line.substring(0, 5) + "\n";
				}
				else if (line.startsWith("DW") || line.startsWith("dw")) {
					String s = line.replace(" ", "");
					
					int commentStarts = s.indexOf("#");
					if (commentStarts >= 0) {
						s = s.substring(0, commentStarts);
					}
					
					data += s + "\n";
				}
				else if (line.startsWith("DB") || line.startsWith("db")) {
					String s = line;
					int commentStarts = s.indexOf("#");
					if (commentStarts >= 0) {
						s = s.substring(0, commentStarts);
					}
					
					data += s + "\n";
				}
				
			}
			
			if (line == null) {
				throw new BadFileException("Bad file. No code segment.");
			}
			
			line = bf.readLine();
			if (! line.startsWith("CODE")) {
				throw new BadFileException("Bad file. Code segment must start with CODE.");
			}
			
			while ( (line = bf.readLine() )!= null && 
					!(line.startsWith("ENDCODE")) ) {
				int commentStarts = line.indexOf("#");
				if (commentStarts >= 0) {
					line = line.substring(0, commentStarts);
				}
				line = line.replaceAll("\\s+", "");

				if (line.length() < 4)
					code += String.format("%1$-4s", line) + "\n";
				else
					code += line.substring(0, 4) + "\n";
			}
		}
		catch (IOException ex) {
			throw new BadFileException(ex.getMessage());
		}
		String[] result = {
				data, code
		};
		return result;
	}
	
	public static void loadData(String data, Word[] mem) {
		BufferedReader bf = new BufferedReader(new StringReader(data));
		try {
			String line;
			int cursor = 0;
			// read data segment
			while ( (line = bf.readLine() )!= null) {
				if (line.matches("\\$[0-9A-Fa-f]{2}:\\$.*$")) {
					cursor = Integer.parseInt(line.substring(1, 3), 16);
					continue;
				}
				else if (line.startsWith("DW") || line.startsWith("dw")) { //TODO decimal! operates only hex values
					String s = line.substring(2);
					if (s.length() > 4)
						s = s.substring(0, 4);
					//System.out.println(s + "  " + cursor);
					s = s.replaceAll("\\s+", "");
					mem[cursor].setWordHexInt(Integer.parseInt(s, 16));
				}
				else if (line.startsWith("DB") || line.startsWith("db")) {
					String s = line.substring(3);
					if (s.length() > 4)
						s = s.substring(0, 4);
					//s = s.replaceAll("\\s+", "");
					mem[cursor].setWordString(s);
				}
				else
					continue;
				cursor++;
			}
		}
		catch (IOException ex) {
			//TODO
		}
	}
	
	public static void loadCode(String code, Word[] mem) {
		BufferedReader bf = new BufferedReader(new StringReader(code));
		int cursor = 0x80;
		try {
			String line;
			while ( (line = bf.readLine() )!= null) {
				
				if (line.length() < 4)
					mem[cursor++].setWordString(String.format("%1$-4s", line));
				else
					mem[cursor++].setWordString(line.substring(0, 4));
			}
		}
		catch (IOException e) {
			// TODO: handle exception
		}
	}
	
}
