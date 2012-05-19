package processes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import exception.BadFileException;

import machine.Parser;

import os.Kernel;
import os.Resource;

public class JCL extends Process {

	
	
	public JCL(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "taskinsupmemory"; // task in cache
	}

	@Override
	public void run() {
		
		switch (this.missingResource) {
		
			case "taskinsupmemory":
				
				Resource res = Kernel.getResources().getStartsWith("TASK");
				if (res != null) {
					 
					try {
						// ignoring first 4 chars
						String[] task = Parser.validateTask(res.getId().substring(4));
						
						//create resource with task data
						Kernel.getResources().create(new Resource("DATA" + task[0], this.id));
						
						// create resource with task source code
						Kernel.getResources().create(new Resource("CODE" + task[1], this.id));
						
						
					} catch (BadFileException e) {
						Kernel.getResources().create(new Resource("jclerror " + e.getMessage(), this.id));
					}
					
				}
				
				else {
					// WTF?!?
					return;
				}
		}
		
	}
	
}
