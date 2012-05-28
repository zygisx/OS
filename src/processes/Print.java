package processes;

import machine.RealMachineRegisters;
import machine.Realmachine;
import os.Kernel;
import exception.ProcessException;
import exception.VirtualMachineProgramException;

public class Print extends Process {

	
	public Print(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "printstart";
	}
	
	@Override
	public void run() throws ProcessException {
		RealMachineRegisters.setCH2(1);
		String[] info = Kernel.getResources().get("printstart").getInfo().split(" ");
		
		int vmNum = Integer.parseInt(info[0]);
		String command = info[1];			
		
		Realmachine.setActiveVirtualMachine(vmNum); // to avoid bugs
		
		try {
			Realmachine.getActiveVM().processCommand(command, vmNum);
		} catch (VirtualMachineProgramException e) {
			throw new ProcessException(e.getMessage());
		}
		Realmachine.getActiveVM().increaseIc();
		
		
		Kernel.getResources().destroy("printstart");
		
		RealMachineRegisters.setCH4(0);
		
	}
}
