package processes;

import exception.ProcessException;
import os.Kernel;
import os.Resource;

public class Interrupt extends Process {

	
	public Interrupt(String id, String parent, int priority) {
		super(id, parent, priority);
		super.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "interrupt";
	}
	
	@Override
	public void run()  throws ProcessException {
		switch(this.missingResource) {
			case "interrupt":
				
				//get interrupt resource
				Resource interruptResource = Kernel.getResources().get("interrupt");
				 
				
				// Create message for MainProc to destroy job governor
				Kernel.getResources().create(new Resource("taskinmemory", this.getId()));
				Kernel.getResources().create(new Resource(
						"taskinmemory_false", 
						this.id, 
						""+ interruptResource.getInfo()
						)
				);
				
				//destroy interrupt resource
				Kernel.getResources().destroy(interruptResource.getId());
		}
	}
	
}
