package processes;

import java.util.ArrayList;

public class Process {
	
	// fields - descriptor of proecess
	
	protected String id, status, parentProcess;
	protected boolean isSupervisorMode;
	protected int pID, memoryBlockNumber, priority;
	protected ArrayList resources, createdResources, currentList, childrenProcesses;
	
	public Process(String id, String parent) {
		this.id = id;
		this.parentProcess = parent;
	}
	
	
	public void askResource() {
		
	}
	
	public void releaseResource() {
		
		
	}
	
	public void createResource() {
		
		
	}
	
}
