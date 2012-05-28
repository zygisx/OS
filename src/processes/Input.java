package processes;

import os.Kernel;
import machine.RealMachineRegisters;
import machine.Realmachine;
import exception.ProcessException;
import exception.VirtualMachineProgramException;

public class Input extends Process {


	public Input(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "inputstart";
	}

	@Override
	public void run() throws ProcessException {
		RealMachineRegisters.setCH2((byte) 1);
		String[] info = Kernel.getResources().get("inputstart").getInfo().split(" ");
		
		int vmNum = Integer.parseInt(info[0]);
		String command = info[1];			
		
		Realmachine.setActiveVirtualMachine(vmNum); // to avoid bugs
		
		try {
			Realmachine.getActiveVM().processCommand(command, vmNum);
		} catch (VirtualMachineProgramException e) {
			throw new ProcessException(e.getMessage());
		}
		Realmachine.getActiveVM().increaseIc();
		
		
		Kernel.getResources().destroy("inputstart");

		RealMachineRegisters.setCH2((byte) 1);
	}

}
