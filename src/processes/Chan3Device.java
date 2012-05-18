package processes;

public class Chan3Device extends Process {

	
	public Chan3Device (String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
	}
	
	@Override
	public void run() {
		
	}
}
