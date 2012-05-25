package processes;

import exception.ProcessException;
import machine.Realmachine;
import os.Timer;

public class VirtualMachine extends Process {

	private Timer timer;
	private final int VMNum;
	
	public VirtualMachine(String id, String parent, int priority) {
		super(id, parent, priority);
		this.status = Status.BLOCKED;
		this.isSupervisorMode = false;	// true defines that it is system process
		this.timer = new Timer();
		this.VMNum = Integer.parseInt(id.substring(2));
		
		this.missingResource = "vmrun";  // this resource is always free.
	}
	
	@Override
	public void run() throws ProcessException {
		
		machine.VirtualMachine vm = Realmachine.getVirtualMachine(VMNum);
		String command = "";
		if (vm == null) {
			throw new ProcessException("Misteke in vm. Maybe vm added to wrong place");
		}
		while (! this.timer.isInterupt()) {
			
			
			command = vm.step();
			if (command.equals("gd") && command.equals("pd")) {
				timer.IOStroke();
				
				//TODO implement interrupt
			}
			else {
				timer.stroke();
			}
		}
		
		
	}
	
}
