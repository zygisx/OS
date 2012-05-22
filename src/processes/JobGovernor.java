package processes;

public class JobGovernor extends Process {

	
	public JobGovernor(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = false;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
	
}
