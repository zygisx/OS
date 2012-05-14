package processes;

public class Read extends Process {

	
	
	public Read(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
}
