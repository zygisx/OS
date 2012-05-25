package processes;

import java.util.ArrayList;
import java.util.PriorityQueue;

import exception.ProcessException;

import os.Resource;

public abstract class Process implements Comparable<Process> {
	
	public enum Status {
		READYS, READY, BLOCKEDS, BLOCKED
	}
	
	// fields - descriptor of proecess
	
	protected String id, parentProcess, missingResource;
	protected Status status;
	protected boolean isSupervisorMode;
	protected int pID, memoryBlockNumber, priority;
	protected ArrayList<Resource> resources, createdResources;
	protected ArrayList<Process> currentList, childrenProcesses;
	
	public Process(String id, String parent) {
		this(id, parent, os.Constants.MIN_PRIORITY);
	}
	
	public Process(String id, String parent, int priority) {
		this.id = id;
		this.parentProcess = parent;
		this.priority = priority;
	}
	
	
	// abstract method
	public abstract void run() throws ProcessException;
	
	public void askResource() {
		
	}
	
	public void releaseResource() {
		
	}
	
	public void createResource() {
		
	}
	
	/**
	 * Setters
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Getters
	 */
	public int getPriority() {
		return this.priority;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public String getMissingResource() {
		return this.missingResource;
	}
	
	public String getId() {
		return this.id;
	}
	
	
	@Override
	public int compareTo(Process o) {
		if (this.priority > o.getPriority())
			return -1;
		else if (this.priority < o.getPriority())
			return 1;
		else return 0;
	}
	
}
