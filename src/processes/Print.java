package processes;

public class Print extends Process {

	
	public Print(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
}
