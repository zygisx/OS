package processes;

import os.Kernel;
import os.Resource;

public class Interrupt extends Process {

	
	public Interrupt(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "interrupt";
	}
	
	@Override
	public void run() {
		switch(this.missingResource) {
			case "interrupt":
				
				//get interrupt resource
				Resource interuptResource = Kernel.getResources().getStartsWith("INTERRUPT");
				
				//ignore "INTERRUPT" in resource ID
				String resourceForJobGovernor = interuptResource.getId().substring(9);
				
				//create new INTERRUPT resource for Job Governor process. That resource's ID contains type and virtual machine identification
				Kernel.getResources().create(new Resource("JOBGOVERNOR_INTERRUPT" + resourceForJobGovernor, this.id));
				
				//destroy interrupt resource
				
				Kernel.getResources().destroy(interuptResource.getId());
		}
	}
	
}
