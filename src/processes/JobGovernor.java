package processes;

import exception.ProcessException;
import machine.Realmachine;
import machine.VirtualMachine;
import machine.VirtualMachineRegisters;
import os.Constants;
import os.Kernel;

public class JobGovernor extends Process {

	private final String firstMissingRes;
	private final int jobNum;
	
	public JobGovernor(String id, String parent, int priority) {
		super(id, parent, priority);
		this.status = Status.BLOCKED;
		this.isSupervisorMode = false;	// true defines that it is system process
		this.missingResource = "jobgovernorstart" + id.substring(11);
		this.jobNum = Integer.parseInt(id.substring(11));
		this.firstMissingRes = this.missingResource;
		
		
	}
	
	@Override
	public void run() throws ProcessException{
		
		if (this.missingResource.equals(this.firstMissingRes)) {
			Kernel.createProcess(new processes.VirtualMachine("VM" + this.jobNum, this.id, Constants.JOG_GOVERNER_PRIORITY - 1));
			Realmachine.addVirtualMachine(
					new VirtualMachine(
							new VirtualMachineRegisters(), 
							Realmachine.getVirtualMachineMemory(this.jobNum)
							), 
					this.jobNum);
			
			
			Kernel.getResources().destroy(this.firstMissingRes);
			
			this.missingResource = "interrupt" + this.jobNum;
		}
		else if (this.missingResource.equals("jbinterrupt" + this.jobNum)) {
			switch (Kernel.getResources().get("jbinterrupt" + this.jobNum).getInfo().substring(0, 2)) {
				case "io":
					
					//TODO input output. 
					
					
					return;
					
				case "pi":	
				case "si":
					
					//TODO create resource for interrupt process 
					
					return;
				case "ti":
					return ;
					
					
			}
			
			Kernel.getResources().destroy(this.missingResource);
		}
		
		//TODO find more elegant solution
		// case jobgovstart
		
		
	}
	
}
