package processes;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Process {
	
	public enum Status {
		READYS, READY, BLOCKEDS, BLOCKED
	}
	
	// fields - descriptor of proecess
	
	protected String id, parentProcess;
	protected Status status;
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
