package processes;

import exception.ProcessException;
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
	public void run() throws ProcessException{
		
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
				this.destroyResources();
				// destroy processes
				this.destroyProcesses();
				// system shut down
				//Kernel.turnOffSystem();
				Kernel.getOsFrame().setTurnOnButton(true);
				Kernel.getOsFrame().turnOffUtillsButtons();
				return;
			
		}
	}

	private void createResources() {
		Kernel.getResources().create(new Resource("processor", this.id));
		Kernel.getResources().create(new Resource("supmemory", this.id));
		Kernel.getResources().create(new Resource("vmemory", this.id, "1"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "2"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "3"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "4"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "5"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "6"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "7"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "8"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "9"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "10"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "11"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "12"));
		Kernel.getResources().create(new Resource("vmemory", this.id, "13"));
		
		Kernel.getResources().create(new Resource("vmrun", this.id));	// always free resource
		
		
		// don't know better place to create it. For now we cannot add task interactive
		if (! Kernel.isTaskQueueEmpty()) Kernel.getResources().create(new Resource("filename", this.id));
		// and so on 
	}
	
	private void createProcesses() {
		Kernel.createProcess(new Read("read", this.id, Constants.MAX_PRIORITY - 1));
		Kernel.createProcess(new Chan3Device("chan3device", this.id, Constants.MAX_PRIORITY - 2));
		Kernel.createProcess(new JCL("jcl", this.id, Constants.MAX_PRIORITY - 3));
		Kernel.createProcess(new Loader("loader", this.id, Constants.MAX_PRIORITY - 4));
		Kernel.createProcess(new Interrupt("interrupt", this.id, Constants.MAX_PRIORITY - 5));
		Kernel.createProcess(new Print("print", this.id, Constants.MAX_PRIORITY - 6));
		Kernel.createProcess(new Input("input", this.id, Constants.MAX_PRIORITY - 6));
		Kernel.createProcess(new MainProc("mainproc", this.id, Constants.MAX_PRIORITY - 7));
	}
	
	private void destroyResources() {
		Kernel.getResources().destroy("processor");
		Kernel.getResources().destroy("supmemory");
		
		Resource r = Kernel.getResources().get("vmemory");
		while (r != null) {
			Kernel.getResources().destroy(r.getId());
			r = Kernel.getResources().get("vmemory");
		}
		
		Kernel.getResources().destroy("vmrun");
		
	}
	
	private void destroyProcesses() {
		Kernel.removeProcess("read");
		Kernel.removeProcess("chan3device");
		Kernel.removeProcess("jcl");
		Kernel.removeProcess("loader");
		Kernel.removeProcess("interrupt");
		Kernel.removeProcess("mainproc");
	}


	
}
