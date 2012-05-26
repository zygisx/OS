package os;

import gui.OsFrame;
import gui.ProcessesPanel;
import gui.vm.MainFrame;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import exception.ProcessException;

import processes.*;
import processes.Process;
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
	
	private static OsFrame osFrame;
	private static boolean stepMode = true;
	private static boolean nextStep = false;
	
	public static void RunOS() {
		Kernel.launchOsFrame();
		
		boolean tempFlagForDinamicTask = false;
		
		if(stepMode) {

//			waitForStep();
		
			// at first we start start/stop process.
			
			Kernel.createProcess(new StartStop("startstop"));
			// just for now for testing purpose
			long time = System.currentTimeMillis();
			while (isSystemOn) {
				
				// at first call resource manager which would be implemented in TaskManager class 
				
				// ask task manager for process with highest priority
				processes.Process p = taskManager.getCurrentProcess();
				
				if (p != null) {   // p == null when no ready processes are available
					
					
					
					// give processor to process and return when process blocked
					System.out.println("PROCESS " + p.getId().toUpperCase() + " RUNNING");
					
					try {
						waitForStep();
						p.run();
						
					}
					catch (ProcessException ex) {
						System.out.println(ex.getMessage());
					}
					
					System.out.println("PROCESS " + p.getId().toUpperCase() + " FINESHED");
					// process returns when it hasn't got needful resource 
					// so we block it
					p.setStatus(Status.BLOCKED);
					
					
					
				}
				else {
					resourceList.create(new Resource("mosworkend", null));
				}


				
//				if ((System.currentTimeMillis() - time) > 1000 * 60) {
//					// after five seconds i create resource mosworkend and than startstop can continue
//					resourceList.create(new Resource("mosworkend", null));
//					System.out.println("PABAIGA");
//					//isSystemOn = false;
//				}
				
				

				// only for testing, program runs only 5 seconds
//				if (! tempFlagForDinamicTask && (System.currentTimeMillis() - time) > 100) {
//					
//					Kernel.addTask("././CountTo10.txt");
//					Kernel.addTask("././InfiniteLoop.txt");
//					Kernel.addTask("././InfiniteLoop.txt");
//					tempFlagForDinamicTask = true;
//				}
//				if ((System.currentTimeMillis() - time) > 250) {
//					// after five seconds i create resource mosworkend and than startstop can continue
//					resourceList.create(new Resource("mosworkend", null));
//					System.out.println("PABAIGA");
//					//isSystemOn = false;
//				}

				
			}

			

			System.out.println("Mos successfully ended work");
		
		}
		
	}
	
	
	/**
	 * for adding tasks in run time
	 * Danger! not work if you add tasks with this method on system start.
	 */
	public static void addTaskDynamically(String task) {
		tasks.add(task);
		if (getResources().get("filename") == null) {
			getResources().create(new Resource("filename", null));
		}
	}
	/**
	 * For adding task on system start.
	 */
	public static void addTask(String task) {
		tasks.add(task);
		System.out.println("VISKAS");
	}
	
	
	public static String useTask() {
		return tasks.poll();
	}
	
	public static boolean isTaskQueueEmpty() {
		return tasks.isEmpty();
	}
	
	
	public static void createProcess(processes.Process newProcess) {
		
		waitForStep();
		
		System.out.println("\tProcess " + newProcess.getId() + " created");
		
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
				i.remove();
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
	
	private static void launchOsFrame() {
		osFrame = new OsFrame();
		osFrame.setVisible(true);
	}
	
	
	public static int getProcessesCount() {
		return processes.size();
	}


	public static String getProcessesListValue(int row, int col) {
		String result = null;
		
		Process process = processes.get(row);
		
		switch(col) {
		
			case 0:
				result = process.getId();
				break;
			case 1:
				result = process.getParent();
				break;
			case 2:
				result = process.getMissingResource();
				break;
			case 3:
				result = process.getStatus().toString();
				break;
			case 4:
				result = Boolean.toString(process.getSuperVisorValue());
				break;
			case 5:
				result = Integer.toString(process.getPriority());
				break;
		}
		
		return result;
	}
	
	public static String getResourcesListValue(int row, int col) {
		String result = null;
	
		if (row <= (resourceList.getCount() -1)) {
		
			Resource resource = resourceList.get(row);
			
			switch(col) {
			
				case 0:
					result = resource.getId();
					break;
					
				case 1:
					result = resource.getParent();
					break;
					
				case 2:
					result = taskManager.getWaitingListString(resource.getId());
					break;
				
				case 3:
					result = resource.getAvailability();
					break;
					
				case 4:
					result = resource.getInfo();
					break;
			
			}
		
		}
		
		return result;
	}
	
	public static int getResourcesCount() {
		return resourceList.getCount();
	}

	public static void waitForStep() {
		
		while(!nextStep) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Kernel.nextStep = false;
		Kernel.osFrame.update();
	}
	
	public static void setNextStep(boolean value) {
		Kernel.nextStep = value;
	}
	
	public static OsFrame getOsFrame() {
		return osFrame;
	}
	
}
