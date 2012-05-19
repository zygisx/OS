package processes;

import os.Kernel;
import os.Resource;
import processes.Process.Status;

public class MainProc extends Process{



	
	public MainProc(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;
		this.status = Status.BLOCKED;
		this.missingResource = "loaderfinish";
	}
	
	public MainProc(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "loaderfinish"; // task in cache
	}
	
	@Override
	public void run() {
		
		switch(this.missingResource) {
		
			case "loaderfinish":
				
				Resource resTrue = Kernel.getResources().getStartsWith("taskinmemory_true");
				
				if (resTrue != null) {
					Resource resFalse = Kernel.getResources().getStartsWith("taskinmemory_false");
					
					if (resFalse != null) {
						// TODO destroy jobGovernor
					} else {
						// TODO create jobGovernor
					}
				}
		
		}
		
	}

}
