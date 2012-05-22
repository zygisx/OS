package os;

import java.util.ArrayList;

public class Resource {

	
	private String id;
	private boolean isReusable; // kas �ia ?
	private boolean isAvailable;
	private String parenProcess; // don't know why int..? I change it to string
	private ArrayList<processes.Process> waitingForResourceProcessList;
	private String info;
	
	// nezinau kas cia tokie
	// nuoroda � prieinamumo s�ra��
	// nuoroda � paskirstytojo objekt�

	
	public Resource(String id, String parent) {
		this(id, parent, "");
	}
	
	public Resource(String id, String parent, String info) {
		this.id = id;
		this.parenProcess = parent;
		this.waitingForResourceProcessList = new ArrayList<processes.Process>();
		this.isAvailable = true;
		this.info = info;
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

	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
