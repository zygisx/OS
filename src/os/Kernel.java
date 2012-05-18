package os;

import java.util.ArrayList;
import java.util.Iterator;

import processes.*;

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
	private static ArrayList<processes.Process> processes = new ArrayList<>(); 
	private static TaskManager taskManager = new TaskManager(); //FIXME
	
	private static boolean isSystemOn = true; 
	
	
	public static void RunOS() {
		
		// at first we start start/stop process.
		Kernel.createProcess(new StartStop("startstop"));
		
		// jus for now for testing purpose
		long time = System.currentTimeMillis();
		while (isSystemOn) {
			
			// at first call resource manager which would be implemented in TaskManager class 
			
			// ask task manager for process with highest priority
			processes.Process p = taskManager.getCurrentProcess();
			
			// give processor to process and return when process blocked
			p.run(); // not yet implements
			
			// only for testing, program runs only 10 seconds
			if ((time - System.currentTimeMillis()) > 10000) {
				isSystemOn = false;
			}
			
		}
		
	}
	
	
	public static void createProcess(processes.Process newProcess) {
		taskManager.createProcess(newProcess);
		processes.add(newProcess);
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
