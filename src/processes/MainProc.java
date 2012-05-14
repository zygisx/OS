package processes;

public class MainProc extends Process{



	
	public MainProc(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;
	}

}
