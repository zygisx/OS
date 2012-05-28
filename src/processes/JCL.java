package processes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import exception.BadFileException;
import exception.ProcessException;

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
	public void run() throws ProcessException {
		
		switch (this.missingResource) {
		
			case "taskinsupmemory":
				
				Resource res = Kernel.getResources().get("TASK");
				if (res != null) {
					 
					try {
						// ignoring first 4 chars
						String[] task = Parser.validateTask(res.getInfo());
						
						//create resource with task data
						Kernel.getResources().create(new Resource("DATA", this.id, task[0]));
						
						// create resource with task source code
						Kernel.getResources().create(new Resource("CODE", this.id, task[1]));
							
						Kernel.getResources().create(new Resource("loaderstart", this.id));
						
					} catch (BadFileException e) {
						Kernel.getResources().create(new Resource("jclerror " + e.getMessage(), this.id));
					} catch (Exception e) {
						Kernel.getResources().create(new Resource("jclerror " + e.getMessage(), this.id));
					}
					finally {
						// destroy task resource 
						Kernel.getResources().destroy(res.getId());
						Kernel.getResources().destroy("taskinsupmemory");
					}
					
				}
				
				else {
					Kernel.getResources().create(new Resource("jclerror", this.id));
					return;
				}
		}
		
	}
	
}
