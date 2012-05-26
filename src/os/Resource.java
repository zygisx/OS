package os;

import java.util.ArrayList;

public class Resource {

	
	private String id;
	private boolean isReusable; // kas èia ?
	private boolean isAvailable;
	private String parentProcess; // don't know why int..? I change it to string
	private ArrayList<processes.Process> waitingForResourceProcessList;
	private String info;
	
	// nezinau kas cia tokie
	// nuoroda á prieinamumo sàraðà
	// nuoroda á paskirstytojo objektà

	
	public Resource(String id, String parent) {
		this(id, parent, "");
	}
	
	public Resource(String id, String parent, String info) {
		this.id = id;
		this.parentProcess = parent;
		this.waitingForResourceProcessList = new ArrayList<processes.Process>();
		this.isAvailable = true;
		this.info = info;
	}
	
	public void free() {
		
		//FIXME
		System.out.println("\t" + this.id + " released.");
		
		this.isAvailable = true;
	}
	
	public void occupy() {
		
		//FIXME
		System.out.println("\t" + this.id + " occupied.");
		
		this.isAvailable = false;
	}
	
	public boolean isAvailable() {
		return this.isAvailable;
	}

	public String getId() {
		return this.id;
	}

	public String getInfo() {
		return this.info;
	}
	
	
	public String getParent() {
		return this.parentProcess;
	}
	
	public String getAvailability() {
		return Boolean.toString(isAvailable);
	}
	
	
}
