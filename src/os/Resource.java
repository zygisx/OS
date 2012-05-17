package os;

import java.util.ArrayList;

public class Resource {

	
	private String id;
	private boolean isReusable; // kas �ia ?
	private boolean isAvailable;
	private int parenProcess; // don't know why int..?
	private ArrayList<processes.Process> waitingForResourceProcessList;
	
	// nezinau kas cia tokie
	// nuoroda � prieinamumo s�ra��
	// nuoroda � paskirstytojo objekt�

	
	public Resource(String id, int parent) {
		this.id = id;
		this.parenProcess = parent;
		this.waitingForResourceProcessList = new ArrayList<processes.Process>();
		this.isAvailable = true;
	}
	
	public void free() {
		this.isAvailable = true;
	}
	
	public void occupy() {
		this.isAvailable = false;
	}
	
	public boolean isAvailable() {
		return this.isAvailable;
	}

	public String getId() {
		return this.id;
	}
	
	
	
	
	
}
