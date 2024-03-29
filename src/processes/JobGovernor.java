package processes;

import java.util.Iterator;

import exception.ProcessException;
import exception.VirtualMachineProgramException;
import machine.RealMachineRegisters;
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
	public void run() throws ProcessException, VirtualMachineProgramException{
		
		if (this.missingResource.equals(this.firstMissingRes)) {
			Kernel.createProcess(new processes.VirtualMachine("VM" + this.jobNum, this.id, Constants.USER_PROCESS_PRIORITY));
			Realmachine.addVirtualMachine(
					new VirtualMachine(
							new VirtualMachineRegisters(), 
							Realmachine.getVirtualMachineMemory(this.jobNum)
							), 
					this.jobNum);
			
			Kernel.getOsFrame().addVmTab(jobNum);
			
			
			Kernel.getResources().destroy(this.firstMissingRes);
			
			this.missingResource = "jbinterrupt" + this.jobNum;
		}
		else if (this.missingResource.equals("jbinterrupt" + this.jobNum)) {
			switch (Kernel.getResources().get("jbinterrupt" + this.jobNum).getInfo().substring(0, 2)) {
				case "IO":
					RealMachineRegisters.setIOI(1);
					String command = Kernel.getResources().get("jbinterrupt" + this.jobNum).getInfo().substring(3);			
					if (command.substring(0, 2).equalsIgnoreCase("GD") ) {
						Kernel.getResources().create(new Resource("inputstart", this.getId(), this.jobNum + " " + command));
					}
					else {
						Kernel.getResources().create(new Resource("printstart", this.getId(), this.jobNum + " " + command));
					}
					
					Kernel.getResources().destroy("jbinterrupt" + this.jobNum);
					return;
					
				case "PI":	
					RealMachineRegisters.setPI(1);
				case "SI":
					RealMachineRegisters.setSI(1);
					
					Kernel.getOsFrame().removeVmTab(this.jobNum);
					
					// possible that vm which going to be destroyed is ready and holds processor resource, 
					// so we have to release it
					if (this.checkIfVMReady()) {
						Kernel.getResources().get("vmrun").free();
					}
					Kernel.removeProcess("VM" + this.jobNum);
					Resource[] memoryResources = Kernel.getResources().getAll("vmemory");
					for (Resource r : memoryResources) {
						if (r != null && Integer.parseInt(r.getInfo()) == this.jobNum) {
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
					
					Kernel.getResources().destroy("jbinterrupt" + this.jobNum);
					
					RealMachineRegisters.setTI(0);
					
					return ;
					
					
			}
			
			Kernel.getResources().destroy(this.missingResource);
		}
		
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
