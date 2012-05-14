package processes;

import mos.Constants;

public class StartStop extends Process {

	
	
	public StartStop(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		super.priority = Constants.MAX_PRIORITY;
	}
}
