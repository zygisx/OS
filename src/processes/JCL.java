package processes;

public class JCL extends Process {

	
	
	public JCL(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "taskincache"; // task in cache
	}

	@Override
	public void run() {
		
	}
	
}
