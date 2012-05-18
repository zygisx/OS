package processes;

public class Loader extends Process{

	
	public Loader(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "loaderpackage"; // silly name
	}
	
	@Override
	public void run() {
		
	}
}
