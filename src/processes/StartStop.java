package processes;

import os.Constants;

public class StartStop extends Process {

	
	
	public StartStop(String id) {
		super(id, null);  // start/stop has no parents. 
		super.isSupervisorMode = true;	// true defines that it is system process
		super.priority = Constants.MAX_PRIORITY; 
	}
	
	
	@Override
	public void run() {
		
		// system resources initialization
		
		// initialize static resources
		
		// system processes initialization
		
		
		// block until MOS finishes its work	
		
		
		// destroy processes & resources
	}


	
}
