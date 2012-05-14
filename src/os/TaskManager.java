package os;

import java.util.PriorityQueue;

public class TaskManager {
	
	private PriorityQueue<Process> readyProcesses;
	private Process currentProcess;
	
	public TaskManager(PriorityQueue<Process> readyProcesses) {
		if(readyProcesses != null) {
			this.readyProcesses = readyProcesses;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public void run() {
		while (readyProcesses.size() > 0) {
			currentProcess = readyProcesses.poll();
			currentProcess.run();
		}
	}
	
}
