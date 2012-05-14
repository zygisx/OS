package processes;

public class JobGovernor extends Process {

	
	public JobGovernor(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = false;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
	
}
