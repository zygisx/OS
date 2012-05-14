package os;

import java.util.PriorityQueue;
import processes.*;

public class TaskManager {
	
	private PriorityQueue<processes.Process> readyProcesses;
	private processes.Process currentProcess;
	
	public TaskManager(PriorityQueue<processes.Process> readyProcesses) {
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
