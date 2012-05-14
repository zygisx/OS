package processes;

public class JCL extends Process {

	
	public JCL(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
	
	@Override
	public void run() {
		
	}
	
}
