package processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import os.Kernel;
import os.Resource;

public class Read extends Process {


	
	public Read(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "filename"; //TODO fix resource name. It don't correspond purpose
	}
	
	/* DEPRECATED  */
	/*
	public void addFileName(String fileName) {
		this.filenames.add(fileName); // add to the end of list
	}
	*/
	
	@Override
	public void run() {
		
		switch (this.missingResource) {
			
			case "filename":
				this.missingResource = "supmemory"; // need another resource
				return;
				
			case "supmemory":
				
				if (! Kernel.isTaskQueueEmpty()) {
					try {
						
						// read task from file
						String task = this.readFile(Kernel.useTask());
						
						// create resource
						task = "TASK" + task;
						Kernel.getResources().create(new Resource(task, this.id));
						
						//create resource for JCL
						Kernel.getResources().create(new Resource("taskinsupmemory", this.id));
					}
					catch (IOException ex) {
						// something went bad
					}
					
				}
				
				// if any filenames are available than only resource needed is supervizor memory
				if (! Kernel.isTaskQueueEmpty()) {
					this.missingResource = "supmemory";
					// free used resource
					
					
					Kernel.getResources().get("supmemory").free();
				}
				
				else {
					this.missingResource = "filename";
					// destroy resource
					Kernel.getResources().destroy("filename");
				}
					
				
				return;	
			case "chan3devicefinised":
				
				
				return;
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
