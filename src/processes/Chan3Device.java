package processes;

public class Chan3Device extends Process {

	
	public Chan3Device(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
}
