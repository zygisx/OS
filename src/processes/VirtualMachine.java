package processes;

public class VirtualMachine extends Process {

	public VirtualMachine(String id, String parent, int priority) {
		super(id, parent, priority);
		super.isSupervisorMode = false;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
	
}
