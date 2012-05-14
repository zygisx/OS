package processes;

public class Loader extends Process{

	
	public Loader(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		
	}
}
