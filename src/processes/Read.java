package processes;

public class Read extends Process {

	
	
	public Read(String id, String parent, int priority) {
		super(id, parent, priority);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
}
