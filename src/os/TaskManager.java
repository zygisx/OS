package os;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import processes.Process;
import processes.Process.Status;

public class TaskManager {
	
	private PriorityQueue<processes.Process> readyProcesses;
	private PriorityQueue<processes.Process> blockedProcesses;
	private processes.Process currentProcess;

	
	
	
	
	
	public TaskManager() {
		this.readyProcesses = new PriorityQueue<processes.Process>();
		this.blockedProcesses = new PriorityQueue<processes.Process>();
	}
	
	public Process getCurrentProcess() {
		
		// check all ready processes
		this.checkReadyProcesses();
		// run resource manager. To check if some of processes get resource they needed.
		this.resourceManager();
		// check all blocked processes
		this.checkBlockedProcesses();
		
		// check all ready processes
		
		//FIXME leave this for testing purpose
		this.checkReadyProcesses1();
		
		return this.readyProcesses.peek();
	}
	
	
	
	private void resourceManager() {
		ResourcesList resources = Kernel.getResources();
		
		
		Iterator<processes.Process> i = this.blockedProcesses.iterator();
		while (i.hasNext()) {								// iterate through all processes
			processes.Process proc = i.next();
			// if process is blocked than check if needful resource is free now
			// if so then process is ready and resource is occupy
			if (proc.getStatus() == Status.BLOCKED) {		
				String missingRes = proc.getMissingResource();		
				if (missingRes != "" && resources.isExists(missingRes)) {
					Resource r = resources.getAvailable(missingRes);
					if (r != null) {
						proc.setStatus(Status.READY);
						r.occupy();
					}
				}
			}
			// with BLOCKEDS
			//else if (proc.getStatus() == Status.BLOCKEDS) {		
			
		}
	}


	public void createProcess(processes.Process newProcess) {
		if (newProcess.getStatus() == Status.BLOCKED)
			this.blockedProcesses.add(newProcess);
		else if (newProcess.getStatus() == Status.READY)
			this.readyProcesses.add(newProcess);
		//TODO work with stopped processes
	}
	
	public void removeProcess(String id) {
		Iterator<processes.Process> i = this.blockedProcesses.iterator();
		while (i.hasNext()) {
			processes.Process p = i.next();
			if (p.getId().equals(id)) {
				i.remove();
			}
		}
		
		i = this.readyProcesses.iterator();
		while (i.hasNext()) {
			processes.Process p = i.next();
			if (p.getId().equals(id)) {
				i.remove();
			}
		}
		
		
	}
	
	private void checkBlockedProcesses() {

		Iterator<processes.Process> i = this.blockedProcesses.iterator();
		
		while (i.hasNext()) {
			processes.Process proc = i.next();
			if (proc.getStatus() == Status.READY) {
				this.readyProcesses.add(proc);
				i.remove();
			}
			//TODO add BLOCKEDS
		}
		//FIXME chenge to smth like checkReadyProcesses()
	}
	

	private void checkReadyProcesses() {
		// TODO Auto-generated method stub
		
		Iterator<processes.Process> i = this.readyProcesses.iterator();
		
		while (i.hasNext()) {
			processes.Process proc = i.next();
			if (proc.getStatus() == Status.BLOCKED) {
				this.blockedProcesses.add(proc);
				i.remove();
			}
			//TODO add BLOCKEDS
		}
	}
	
	private void checkReadyProcesses1() {
		// TODO Auto-generated method stub
		
		Iterator<processes.Process> i = this.readyProcesses.iterator();
		
		while (i.hasNext()) {
			processes.Process proc = i.next();
			if (proc.getStatus() == Status.BLOCKED) {
				this.blockedProcesses.add(proc);
				i.remove();
				System.out.println("BUNA IR TAIP KARTAIS");
			}
			//TODO add BLOCKEDS
		}
	}

	
	public int getReadyProcessesCount() {
		return readyProcesses.size();
	}

	
}
