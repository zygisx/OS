package processes;

import os.Constants;
import os.Kernel;
import os.Resource;

public class StartStop extends Process {

	
	
	public StartStop(String id) {
		super(id, null, Constants.MAX_PRIORITY);  // start/stop has no parents. 
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.READY;
		this.missingResource = "";
	}
	
	
	@Override
	public void run() {
		
		/*
		 * switch to now in wich stage of process we are, in first stage sprocess do not require any resource
		 * in second one it requires mosworkend resource
		 */
		
		switch (this.missingResource) {
		
			case "":
				// system resources initialization
				this.createResources(); 
				
				// system processes initialization
				this.createProcesses();
				
				// block until MOS finishes its work	
				this.missingResource = "mosworkend";
				return;
			
			case "mosworkend":
				this.missingResource = ""; // no resource required 
				
				// destroy resources
				
				// destroy processes
				
				// system shut down
				Kernel.turnOffSystem();
				return;
			
		}
	}
	
	
	public void createResources() {
		Kernel.getResources().create(new Resource("processor", this.id));
		Kernel.getResources().create(new Resource("supmemory", this.id));
		
		// don't know better place to create it
		if (! Kernel.isTaskQueueEmpty()) Kernel.getResources().create(new Resource("filename", this.id));
		// and so on 
	}
	
	public void createProcesses() {
		Kernel.createProcess(new Read("read", this.id, Constants.MAX_PRIORITY - 1));
		Kernel.createProcess(new Read("chan3device", this.id, Constants.MAX_PRIORITY - 2));
		Kernel.createProcess(new JCL("jcl", this.id, Constants.MAX_PRIORITY - 3));
		Kernel.createProcess(new Loader("loader", this.id, Constants.MAX_PRIORITY - 4));
		
		// and others
	}


	
}
