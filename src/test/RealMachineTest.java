package test;

import exception.BadFileException;
import machine.Realmachine;


public class RealMachineTest {

//	@Test
//	public void testGetWord() {
//		// all memory bytes set to 0
//		Word w = new Word("0000");
//		assertEquals(w.getStringValue(), Realmachine.getWord(69).getStringValue());
//		
//	}
//	@Test
//	public void testGetBlock()
//	{
//		Realmachine.getBlock(255);
//	}
	
	public static void main(String args[]) {
		try {
			Realmachine.initVirtualMachine("././program.txt");
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		RealMachineRegisters.getR1().setWordHexInt(100);
//		RealMachineRegisters.getR2().setWordHexInt(3);
//		Realmachine.DV((byte) 0000);
		System.out.println(Realmachine.toString2());
	}
}
