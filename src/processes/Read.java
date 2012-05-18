package processes;

public class Read extends Process {

	
	
	public Read(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "inputsream"; //TODO fix resource name. It don't correspond purpose
	}
	
	@Override
	public void run() {
		
	}
}
