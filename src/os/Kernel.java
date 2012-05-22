package os;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import processes.*;
import processes.Process.Status;

/**
 * main class for main loop
 * 
 * @author 
 *
 */
public class Kernel {

	// I guess this class should work like RealMachine in previous project.
	// It holds all os information, processes lists taskManeger resources list.
	// Here should be main loop for running os. 
	
	/**
	 * object that holds all resources list
	 */
	private static ResourcesList resourceList = new ResourcesList();
	private static ArrayList<processes.Process> processes = new ArrayList<processes.Process>(); 
	private static TaskManager taskManager = new TaskManager(); //FIXME
	
	private static boolean isSystemOn = true; 
	private static Queue<String> tasks = new LinkedList<String>();
	
	public static void RunOS() {
		
		// at first we start start/stop process.
		
		Kernel.createProcess(new StartStop("startstop"));
		
		// just for now for testing purpose
		long time = System.currentTimeMillis();
		while (isSystemOn) {
			
			// at first call resource manager which would be implemented in TaskManager class 
			
			// ask task manager for process with highest priority
			processes.Process p = taskManager.getCurrentProcess();
			
			if (p != null) {   // p == null when no ready processes are available
				
				// process returns when it hasn't got needful resource 
				// so we block it
				p.setStatus(Status.BLOCKED);
				
				// give processor to process and return when process blocked
				
				p.run(); 
				
				System.out.println(p.getId());
				
			}
			else {
				resourceList.create(new Resource("mosworkend", null));
			}
			
			
			
			// only for testing, program runs only 5 seconds
			
			if ((System.currentTimeMillis() - time) > 5000) {
				// after five seconds i create resource mosworkend and than startstop can continue
				resourceList.create(new Resource("mosworkend", null));
				//isSystemOn = false;
			}
			
		}
		System.out.println("Mos successfully ended work");
		
	}
	
	public static void addTask(String task) {
		tasks.add(task);
	}
	
	public static String useTask() {
		return tasks.poll();
	}
	
	public static boolean isTaskQueueEmpty() {
		return tasks.isEmpty();
	}
	
	
	public static void createProcess(processes.Process newProcess) {
		taskManager.createProcess(newProcess);
		processes.add(newProcess);
	}
	
	public static void removeProcess(String id) {
		taskManager.removeProcess(id);
		
		/* remove process */
		Iterator<processes.Process> i = processes.iterator();
		while (i.hasNext()) {
			processes.Process p = i.next();
			if (p.getId().equals(id)) {
				processes.remove(p);
			}
		}
	}
	
	public static void turnOffSystem() {
		isSystemOn = false;
	}
	
	
	/*
	 * getters
	 */
	
	public static ResourcesList getResources() {
		return resourceList;
	}
	
	
	public static Iterator<processes.Process> getProcessesIterator() {
		return processes.iterator();
	}
	
	
	
	
	
}
