package test;

import static org.junit.Assert.*;

import machine.*;

import org.junit.Test;

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
		Realmachine.initVirtualMachine();
		RealMachineRegisters.setR1(new Word());
		RealMachineRegisters.setR2(new Word());
		RealMachineRegisters.getR1().setWordHexInt(2);
		RealMachineRegisters.getR2().setWordHexInt(5);
		Realmachine.ML((byte) 00);
		System.out.println(Realmachine.toString2());
	}
}
