package processes;

import exception.ProcessException;
import machine.Realmachine;
import os.Kernel;
import os.Resource;
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
		
		Kernel.getResources().get("vmrun").free();	// vmrun resource is always available
		
		machine.VirtualMachine vm = Realmachine.getVirtualMachine(VMNum);
		String command = "";
		if (vm == null) {
			throw new ProcessException("Misteke in vm. Maybe vm added to wrong place");
		}
		while (! this.timer.isInterupt()) {
			
			
			command = vm.step();
			if (command == null) {
				// Program is halted.
				
				//TODO create interrupt resource 
				Resource r = new Resource("jbinterrupt" + this.VMNum, this.id, "SI");
				
				Kernel.getResources().create(r);
				

				return;
			}
			String prefix = command.substring(0, 2);
			if (prefix.equals("GD") && prefix.equals("PD") && prefix.equals("PP")) {
				timer.IOStroke();
				//TODO implement interrupt
				
				/* if command is related with IO then we create resource for JobGovernor to process it
				 * and create resource info. Info pattern: [IO XXXX], where XXXX is command. First two chars is /(GD|PD|PP)/
				 */
				Resource r = new Resource("jbinterrupt" + this.VMNum, this.id, "IO " + command);
				Kernel.getResources().create(r);
			}
			else {
				timer.stroke();
			}
		}
	}
	
}
