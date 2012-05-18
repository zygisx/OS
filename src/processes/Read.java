package processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

import os.Kernel;
import os.Resource;

public class Read extends Process {
	
	/**
	 * possible to pass many files and then read them  
	 */
	private ArrayList<String> filenames;
	
	public Read(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "filename"; //TODO fix resource name. It don't correspond purpose
		this.filenames = new ArrayList<String>();
	}
	
	public void addFileName(String fileName) {
		this.filenames.add(fileName); // add to the end of list
	}
	
	@Override
	public void run() {
		
		switch (this.missingResource) {
			
			case "filename":
				this.missingResource = "supervizormemory"; // need another resource
				return;
				
			case "supmemory":
				
				if (this.filenames.size() > 0) {
					try {
						
						// read task from file
						String task = this.readFile(this.filenames.remove(0));
						
						// create resource
						Kernel.getResources().create(new Resource(task, this.id));
						
						//create resource for JCL
						Kernel.getResources().create(new Resource("taskinsupmemory", this.id));
					}
					catch (IOException ex) {
						// something went bad
					}
					
				}
				
				// if any filenames are available than only resource needed is supervizor memory
				if (this.filenames.size() > 0) 
					this.missingResource = "supmemory";
				else 
					this.missingResource = "filename";
				
				return;
			
			//case supervizorinës atminties resursas:
			
			
			
			
			
			
		}
	}
	
	
	/**
	 * efficient way to read entire file to string 
	 * code taken from:
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	 */
	//TODO move that functionality to chan3device method
	private String readFile(String path) throws IOException {
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
