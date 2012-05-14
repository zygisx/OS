package processes;

import os.Constants;

public class StartStop extends Process {

	
	
	public StartStop(String id, String parent) {
		super(id, parent);
		super.isSupervisorMode = true;	// true defines that it is system process
		super.priority = Constants.MAX_PRIORITY;
	}
	
	
	@Override
	public void run() {
		
		// system resources initialization
		
		// system processes initialization
		
		
		// block until MOS finishes its work	
		
		// destroy processes & resources
	}
}
