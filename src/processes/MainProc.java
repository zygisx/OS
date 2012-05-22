package processes;

import os.Constants;
import os.Kernel;
import os.Resource;
import processes.Process.Status;

public class MainProc extends Process{



	
	public MainProc(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;
		this.status = Status.BLOCKED;
		this.missingResource = "taskinmemory_true";
	}
	
	public MainProc(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "taskinmemory_true"; // task in cache
	}
	
	@Override
	public void run() {
		
		switch(this.missingResource) {
		
			case "taskinmemory":
				
				Resource resTrue = Kernel.getResources().getStartsWith("taskinmemory_true");
				
				if (resTrue != null) {
					
					Kernel.createProcess(new processes.JobGovernor("jobgoverner" + resTrue.getInfo(), this.id, Constants.JOG_GOVERNER_PRIORITY));
					Kernel.getResources().create(new Resource("jobgovernorstart" + resTrue.getInfo(), this.id));
					
					Kernel.getResources().destroy("taskinmemory");
					Kernel.getResources().destroy("taskinmemory_true");
					
					
				}
				
				Resource resFalse = Kernel.getResources().getStartsWith("taskinmemory_false");
				
				if (resFalse != null) {
					Kernel.removeProcess(resFalse.getId());
					Kernel.getResources().destroy("taskinmemory");
					Kernel.getResources().destroy("taskinmemory_false");
				}
				
				
				
		
		}
		
	}

}
