package test;

import machine.Realmachine;
import os.Kernel;

public class MosTest {
	
	public static void main(String args[]) {
		try {
			System.out.println("System start work.");
			Kernel.addTask("././CountTo10.txt");
			Kernel.addTask("././InfiniteLoop.txt");
			Kernel.addTask("././CountTo10.txt");
			Kernel.addTask("././CountTo10.txt");
			Kernel.addTask("././CountTo10.txt");
			
			Kernel.RunOS();
			
			System.out.println("System OFF.");
			
			//System.out.println(Realmachine.toString2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
