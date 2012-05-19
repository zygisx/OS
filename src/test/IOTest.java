package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import exception.BadFileException;

import machine.Parser;
import machine.Realmachine;
import machine.Word;

public class IOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			String task = readFile("././fibonacci.txt");
			task = "TASK" + task;
			String[] s = Parser.validateTask(task.substring(4));
			System.out.println(s[0]);
			System.out.println(s[1]);
			
			Word[] mem = Realmachine.getVirtualMachineMemory();
			Parser.loadData(s[0], mem);
			Parser.loadCode(s[1], mem);
			
			for (Word w : mem) {
				System.out.println(w);
			}
			//System.out.println(mem);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	
	public static String readFile(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
	}

}
