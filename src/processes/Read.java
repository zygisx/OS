package processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import exception.ProcessException;

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
	public void run() throws ProcessException{
		
		switch (this.missingResource) {
			
			case "filename":
				this.missingResource = "supmemory"; // need another resource
				return;
				
			case "supmemory":
				
				if (! Kernel.isTaskQueueEmpty()) {
					
					Kernel.getResources().create(new Resource("chan3devicestart", this.getId()));
					this.missingResource = "chan3devicefinised";
					
				}
				
				Kernel.getResources().get("supmemory").free();
					
				
				return;	
			case "chan3devicefinised":
				
				Kernel.getResources().create(new Resource("taskinsupmemory", this.id));
				Kernel.getResources().destroy("chan3devicefinised");
				// if any filenames are available than only resource needed is supervizor memory
				if (! Kernel.isTaskQueueEmpty()) {
					this.missingResource = "supmemory";
					
				}
				
				else {
					this.missingResource = "filename";
					// destroy resource
					Kernel.getResources().destroy("filename");
				}
				Kernel.getResources().get("supmemory").free();
				
				return;
		}
	}
}
