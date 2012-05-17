package os;

import java.util.ArrayList;

public class Resource {

	
	private String id;
	private boolean isReusable; // kas èia ?
	private boolean isAvailable;
	private int parenProcess; // don't know why int..?
	private ArrayList<processes.Process> waitingForResourceProcessList;
	
	// nezinau kas cia tokie
	// nuoroda á prieinamumo sàraðà
	// nuoroda á paskirstytojo objektà

	
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
