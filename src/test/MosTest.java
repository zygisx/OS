package test;

import os.Kernel;

public class MosTest {
	
	public static void main(String args[]) {
		try {
			System.out.println("System start work.");
			Kernel.RunOS();
			System.out.println("System OFF.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
