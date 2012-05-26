package processes;

import java.util.Iterator;

import exception.ProcessException;
import machine.Realmachine;
import machine.VirtualMachine;
import machine.VirtualMachineRegisters;
import os.Constants;
import os.Kernel;
import os.Resource;

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
			Kernel.createProcess(new processes.VirtualMachine("VM" + this.jobNum, this.id, Constants.USER_PROCESS_PRIORITY));
			Realmachine.addVirtualMachine(
					new VirtualMachine(
							new VirtualMachineRegisters(), 
							Realmachine.getVirtualMachineMemory(this.jobNum)
							), 
					this.jobNum);
			
			
			Kernel.getResources().destroy(this.firstMissingRes);
			
			this.missingResource = "jbinterrupt" + this.jobNum;
		}
		else if (this.missingResource.equals("jbinterrupt" + this.jobNum)) {
			switch (Kernel.getResources().get("jbinterrupt" + this.jobNum).getInfo().substring(0, 2)) {
				case "IO":
					
					//TODO input output. 
					
					
					return;
					
				case "PI":	
				case "SI":
					
					// possible that vm which going to be destroyed is ready and holds processor resource, 
					// so we have to release it
					if (this.checkIfVMReady()) {
						Kernel.getResources().get("vmrun").free();
					}
					Kernel.removeProcess("VM" + this.jobNum);
					Resource[] memoryResources = Kernel.getResources().getAll("vmmemory");
					for (Resource r : memoryResources) {
						if (r != null && Integer.parseInt(r.getId()) == this.jobNum) {
							r.free();
							break;
						}
					}
					//TODO create resource for interrupt process 
					Kernel.getResources().create(new Resource("interrupt", this.id, "" + this.jobNum));
					
					//remove used resource
					Kernel.getResources().destroy("jbinterrupt" + this.jobNum);
					
					
					return;
				case "TI":
					return ;
					
					
			}
			
			Kernel.getResources().destroy(this.missingResource);
		}
		
		//TODO find more elegant solution
		// case jobgovstart
		
		
	}
	
	
	public boolean checkIfVMReady() {
		Iterator<processes.Process> i = Kernel.getProcessesIterator();
		while (i.hasNext()) {
			processes.Process proc = i.next();
			if (proc.getId().equals("VM" + this.jobNum) && proc.getStatus() == Status.READY) {
				return true;
			}
		}
		
		return false;
	}
	
}
