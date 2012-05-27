package os;

import java.util.Iterator;
import java.util.PriorityQueue;

import processes.Process;
import processes.Process.Status;
import processes.VirtualMachine;

public class TaskManager {
	
	private PriorityQueue<processes.Process> readyProcesses;
	private PriorityQueue<processes.Process> blockedProcesses;
	
	
	
	public TaskManager() {
		this.readyProcesses = new PriorityQueue<processes.Process>();
		this.blockedProcesses = new PriorityQueue<processes.Process>();
	}
	
	public Process getCurrentProcess() {

		//FIXME
	/*Testing*/
		
//		System.out.print("BEGIN READY: ");
//		this.readyProcesses = printVMProceses(this.readyProcesses);
//		System.out.print("BEGIN BLOCK: ");
//		this.blockedProcesses = printVMProceses(this.blockedProcesses);
		
		// check all ready processes
		this.checkReadyProcesses();

		// run resource manager. To check if some of processes get resource they needed.
		this.resourceManager();
		
		// check all blocked processes
		this.checkBlockedProcesses();
		
		return this.readyProcesses.peek();
	}
	
	private void resourceManager() {
		ResourcesList resources = Kernel.getResources();
		
		
		PriorityQueue<processes.Process> newQueue = new PriorityQueue<processes.Process>();
		int size = this.blockedProcesses.size();
		for (int j = 0; j < size; j++) {
			processes.Process proc = this.blockedProcesses.remove();
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
			newQueue.add(proc);
		}
		this.blockedProcesses = newQueue;
		
		/** 
		 * Buggy version. The iterator does not return the elements in 
		 * any particular order in PriorityQueue. 
		 * Take time time find out that....
		 * 
		 */
		//TODO delete this
//		Iterator<processes.Process> i = this.blockedProcesses.iterator();
//		while (i.hasNext()) {								// iterate through all processes
//			processes.Process proc = i.next();
//			// if process is blocked than check if needful resource is free now
//			// if so then process is ready and resource is occupy
//			if (proc.getStatus() == Status.BLOCKED) {		
//				String missingRes = proc.getMissingResource();		
//				if (missingRes != "" && resources.isExists(missingRes)) {
//					Resource r = resources.getAvailable(missingRes);
//					if (r != null) {
//						proc.setStatus(Status.READY);
//						r.occupy();
//					}
//				}
//			}
//			// with BLOCKEDS
//			//else if (proc.getStatus() == Status.BLOCKEDS) {		
//			
//		}
	}


	public void createProcess(processes.Process newProcess) {
		if (newProcess.getStatus() == Status.BLOCKED)
			this.blockedProcesses.add(newProcess);
		else if (newProcess.getStatus() == Status.READY)
			this.readyProcesses.add(newProcess);
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
		
		PriorityQueue<processes.Process> newQueue = new PriorityQueue<processes.Process>();
		while (! this.blockedProcesses.isEmpty()) {
			processes.Process proc = this.blockedProcesses.poll();
			if (proc.getStatus() == Status.READY) {		
				this.readyProcesses.add(proc);
			}
			else {
				newQueue.add(proc);
			}
		}
		this.blockedProcesses = newQueue;
	}
	

	private void checkReadyProcesses() {		
		
		PriorityQueue<processes.Process> newQueue = new PriorityQueue<processes.Process>();
		while (! this.readyProcesses.isEmpty()) {
			processes.Process proc = this.readyProcesses.poll();
			if (proc.getStatus() == Status.BLOCKED) {		
				this.blockedProcesses.add(proc);
			}
			else {
				newQueue.add(proc);
			}
		}
		this.readyProcesses = newQueue;
	}
	
	public String getWaitingListString(String id) {
		String result = "";
		try {
			Iterator<processes.Process> i = blockedProcesses.iterator();
			while (i.hasNext()) {
				processes.Process p = i.next();
				if (p.getMissingResource().equals(id)) {
					result += p.getId() + " ";
				}
			}
			
			Iterator<processes.Process> iR = readyProcesses.iterator();
			while (iR.hasNext()) {
				processes.Process p = iR.next();
				if (p.getMissingResource().equals(id)) {
					result += p.getId() + " ";
				}
			}
		} catch (java.util.ConcurrentModificationException ex) {
			System.out.println("Failed to update GUI table.");
		}
		
		return result;
	}
	
	//FIXME method for testing
	public static PriorityQueue<processes.Process> printVMProceses(PriorityQueue<processes.Process> list) {
		PriorityQueue<processes.Process> tempList = new PriorityQueue<processes.Process>();
		String res = "";
		processes.Process p = list.poll();
		while (p != null) {
			if (p instanceof VirtualMachine) {
				res += (p.getId() + " ");
			}
			tempList.add(p);
			p = list.poll();
		}
		System.out.println(res);
		return tempList;
	}
	
}
