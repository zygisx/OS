package test;

import machine.Realmachine;
import os.Kernel;

public class MosTest {
	
	public static void main(String args[]) {
		try {
			System.out.println("System start work.");
			Kernel.addTask("././program.txt");
			Kernel.addTask("././program.txt");
			Kernel.addTask("././fibonacci.txt");
			
			Kernel.RunOS();
			
			System.out.println("System OFF.");
			
			//System.out.println(Realmachine.toString2());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
