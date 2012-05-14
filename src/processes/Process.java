package processes;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Process {
	
	public enum Status {
		READYS, READY, BLOCKEDS, BLOCKED
	}
	
	// fields - descriptor of proecess
	
	protected String id, parentProcess, priority;
	protected Status status;
	protected boolean mode;
	protected int pID, memoryBlockNumber;
	protected ArrayList resources, createdResources, childrenProcesses;
	protected PriorityQueue<Process> currentList;
	
	
	public void askResource() {
		
	}
	
	public void releaseResource() {
		
		
	}
	
	public void createResource() {
		
		
	}
	
}
