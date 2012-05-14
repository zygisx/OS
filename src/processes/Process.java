package processes;

import java.util.ArrayList;

public class Process {
	
	public enum Status {
		READYS, READY, BLOCKEDS, BLOCKED
	}
	
	// fields - descriptor of proecess
	
	protected String id, parentProcess, priority;
	protected Status status;
	protected boolean mode;
	protected int pID, memoryBlockNumber;
	protected ArrayList resources, createdResources, currentList, childrenProcesses;
	
	
	public void askResource() {
		
	}
	
	public void releaseResource() {
		
		
	}
	
	public void createResource() {
		
		
	}
	
}
