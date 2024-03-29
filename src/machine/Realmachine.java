package machine;

import exception.BadFileException;
import exception.VirtualMachineProgramException;
import gui.OsFrame;
import gui.vm.MainFrame;

import java.awt.EventQueue;
import java.util.ArrayList;

import os.Kernel;

public class Realmachine {

	/**
	 * shows from where starts code segment in virtual memory
	 */
	public static final int CODE_SEGMENT_START = 0x80;
	public static final int REAL_MEMORY_SIZE = 0x1000;
	public static final int BLOCK_COUNT = 0x100;
	public static final int BLOCK_SIZE = 0x10; // block size is 0x10 = 16 not 0xf = 15
	public static final int PAGINATION_TABLE_SIZE = 0x10; //table consist of 16 blocks
	public static final int VIRTUAL_MACHINE_MEMORY_SIZE = 0x10;
	public static final int TOTAL_VIRTUAL_MACHINES = BLOCK_SIZE*PAGINATION_TABLE_SIZE;
	
	private static Word[] memory;
	private static VirtualMachine activeVirtualMachine;
	private static RealMachineRegisters registers;
	private static VirtualMachine[] virtualMachines;
	private static Pagination paginationMechanism;
	
	private static OsFrame frame = Kernel.getOsFrame();
	
	static {
		memory = new Word[REAL_MEMORY_SIZE];
		/* all memory bytes set to 0 */
		for (int i = 0; i < REAL_MEMORY_SIZE; i++) {
			memory[i] = new Word();
		}
		activeVirtualMachine = null;
		registers = new RealMachineRegisters();
		virtualMachines = new VirtualMachine[TOTAL_VIRTUAL_MACHINES];
		for (int i = 0; i < TOTAL_VIRTUAL_MACHINES; i++) {
			virtualMachines[i] = null;	//FIXME
		}
		// create pagination table and fill it with first 256 bytes from memory
		Word[] paginationTable = new Word[BLOCK_SIZE*PAGINATION_TABLE_SIZE];
		for (int i = 0; i < PAGINATION_TABLE_SIZE; i++) {
			Word[] block = getBlock(i);
			for (int j = 0; j < BLOCK_SIZE; j++) {
				paginationTable[i*BLOCK_SIZE + j] = block[j];
			}
		}
		paginationMechanism = new Pagination(paginationTable);	//FIXME NEED TEST!!!!	
	}
	
	/*
	 * main method
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new MainFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public static void setActiveVirtualMachine(int num) {
		activeVirtualMachine = virtualMachines[num];
		frame.setActiveVmLabel(num);
	}
	
	public static Word getWord(int index) {
		return memory[index];
	}
	
	public static int getBlockNum(int realAddress) {
		return realAddress / 16;
	}
	
	public static int getWordNum(int realAddress) {
		return realAddress % 16;
	}
	
	public static Word[] getBlock(int blockNum) {
		if (blockNum < (BLOCK_COUNT)) { // blockNum < 256 
			Word[] mem = new Word[BLOCK_SIZE];
			for (int i=0; i < BLOCK_SIZE; i++) {
				mem[i] = memory[blockNum*(BLOCK_SIZE) + i];
				// for testing.. To test it run test.RealMachineTest
				//System.out.println(Integer.toHexString((blockNum*BLOCK_SIZE + i))+" " + i);
			}
			return mem;
		}
		//TODO exception or smth
		return null;
	}
	
	
	
	public static void addVirtualMachine(VirtualMachine vm, int num) {
		virtualMachines[num] = vm;
		vm.getRegisters().setPLR(num);
	}
	
	public static Word[] getVirtualMachineMemory(int num) {
		return paginationMechanism.getVirtualMachineMemory(num);
	}
	
	public static VirtualMachine getVirtualMachine(int num) {
		return virtualMachines[num];
	}
	
	/** Deprecated */
	public static VirtualMachine getActiveVM() {
		return activeVirtualMachine;
	}
	
	/** Deprecated */
	public static void addVirtualMachine(VirtualMachine vm) {
		int i = 0;
		while (i < TOTAL_VIRTUAL_MACHINES && virtualMachines[i] != null)
			i++;
		if (i < TOTAL_VIRTUAL_MACHINES)
			virtualMachines[i] = vm;
	}
	
	/** Deprecated */
	public static Word[] getVirtualMachineMemory() {
		return paginationMechanism.getVirtualMachineMemory();
	}
	
	/** Deprecated */
	public static VirtualMachine initVirtualMachine(String fileName) 
			throws BadFileException { 
		Word[] mem = paginationMechanism.getVirtualMachineMemory();  // get allocated memory
		mem = Parser.load(fileName, mem);	// create code and data segments in memory
		VirtualMachine vm = 
				new VirtualMachine(new VirtualMachineRegisters(), mem); // create vm
		addVirtualMachine(vm);
		// no need to set active vm
		setActiveVirtualMachine(1);
		return vm;
	}
	
	// COMMANDS
	
	public static void AD(int virtualAddress) throws VirtualMachineProgramException {
		try {
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			int result = RealMachineRegisters.getR1().getHexValue() + RealMachineRegisters.getR2().getHexValue();
			memory[realAddress].setWordHexInt(result);
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void SB(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			int result = RealMachineRegisters.getR1().getHexValue() - RealMachineRegisters.getR2().getHexValue();
			memory[realAddress].setWordHexInt(result);
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void ML(int virtualAddress) throws VirtualMachineProgramException {
		try {	
	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			int result = 0;
			int num1 = RealMachineRegisters.getR1().getHexValue();
			int num2 = RealMachineRegisters.getR2().getHexValue();
			
			for(int i = 0; i < num2; i++) {
				result = result + num1;
			}
			
			memory[realAddress].setWordHexInt(result);
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void DV(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			int num1 = RealMachineRegisters.getR1().getHexValue();
			int num2 = RealMachineRegisters.getR2().getHexValue();
			int mod = 0;
			int div = 0;
			
			while(num1 >= num2) {
				div++;
				num1 = num1 - num2;
			}
			
			mod = num1;
			
			memory[realAddress].setWordHexInt(div);
			RealMachineRegisters.getR1().setWordHexInt(mod);
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void CP() throws VirtualMachineProgramException {
		try {	
			int R1 = RealMachineRegisters.getR1().getHexValue();
			int R2 = RealMachineRegisters.getR2().getHexValue();
			
			if (R1 == R2) {
				RealMachineRegisters.setSF((byte) 0);
			} else if(R1 > R2) {
				RealMachineRegisters.setSF((byte) 1);
			} else {
				RealMachineRegisters.setSF((byte) 2);
			}
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void CS() throws VirtualMachineProgramException {
		try {	

			int R1 = RealMachineRegisters.getR1().getHexValue();
			int R2 = RealMachineRegisters.getR2().getHexValue();
			
			if(R1 >= R2) {
				RealMachineRegisters.setSF((byte) 3);
			} else {
				RealMachineRegisters.setSF((byte) 4);
			}
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void L1(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			RealMachineRegisters.getR1().setWordHexInt(memory[realAddress].getHexValue());
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void L2(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			RealMachineRegisters.getR2().setWordHexInt(memory[realAddress].getHexValue());
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void S1(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			memory[realAddress].setWordHexInt(RealMachineRegisters.getR1().getHexValue());
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void S2(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			memory[realAddress].setWordHexInt(RealMachineRegisters.getR2().getHexValue());
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void SI(int virtualAddress) throws VirtualMachineProgramException {
		try {	
			int realAddress = paginationMechanism.getRealAddress(virtualAddress);
			memory[realAddress].setWordHexInt(RealMachineRegisters.getIC());
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void JP(int address) throws VirtualMachineProgramException {
		try {	
			RealMachineRegisters.setIC(address);
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void JE(int address) throws VirtualMachineProgramException {
		try {	
			if (RealMachineRegisters.getSF() == 0) {
				RealMachineRegisters.setIC(address);
			} else {
				RealMachineRegisters.setIC(RealMachineRegisters.getIC()+1);
			}
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
		
	public static void JM(int address) throws VirtualMachineProgramException {
		try {
			if (RealMachineRegisters.getSF() == 1) {
				RealMachineRegisters.setIC(address);
			} else {
				RealMachineRegisters.setIC(RealMachineRegisters.getIC()+1);
				
			}	
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void JL(int address) throws VirtualMachineProgramException {
		try {
			if (RealMachineRegisters.getSF() == 2) {
				RealMachineRegisters.setIC(address);
			} else {
				RealMachineRegisters.setIC(RealMachineRegisters.getIC()+1);
			}	
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void JF(int address) throws VirtualMachineProgramException {
		try {
			if (RealMachineRegisters.getSF() == 3) {
				RealMachineRegisters.setIC(address);
			} else {
				RealMachineRegisters.setIC(RealMachineRegisters.getIC()+1);
			}	
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void JS(int address) throws VirtualMachineProgramException {
		try {
			if (RealMachineRegisters.getSF() == 4) {
				RealMachineRegisters.setIC(address);
			} else {
				RealMachineRegisters.setIC(RealMachineRegisters.getIC()+1);
			}	
		} catch (Exception ex) {
			throw new VirtualMachineProgramException(ex.getMessage());
		}
	}
	
	public static void PD(int virtualAddress, int num) {
		String output = "";
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		Word[] block = getBlock(getBlockNum(realAddress));
		for(Word word : block) {
			if (word.getStringValue().equals("nnnn")) {
				output += "\n";
			} else {
				output += word.getStringValue();
			}
		}
		frame.output(output, num);
	}
	
	public static void PP(int virtualAddress, int num) {
		String output = "";
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		Word[] block = getBlock(getBlockNum(realAddress));
		int wordsCount = RealMachineRegisters.getR1().getDecimalValue(), i = getWordNum(realAddress);
		int offset = wordsCount + i;
		while(i < offset && i < block.length) {
			if (block[i].getStringValue().equals("nnnn")) {
				output += "\n";
			} else {
				output += block[i].getStringValue();
			}	
			i++;
		}
		frame.output(output, num);
	}
	
	public static void GD(int virtualAddress, int num) {
		String inputWord = null;
		String s = frame.input(num);
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		Word[] block = getBlock(getBlockNum(realAddress));
		int wordsCount, i = 0;
		if(s.length() % 4 > 0) {
			wordsCount = (s.length() / 4) + 1;
		} else {
			wordsCount = s.length() / 4;
		}
		while(i < wordsCount && i < block.length) {
			if(s.length() >= 4){
				inputWord = s.substring(0, 4);
				s = s.substring(4);
			} else {
				inputWord = s;
			}
			block[i].setWordString(inputWord);
			i++;
		}

	}

	
	/** Deprecated */
//	public static MainFrame getFrame() {
//		return frame;
//	}
	
	public static String toString2()
	{
		int i = 0;
		String fullString;
		fullString = "Registers \n";
		fullString += registers.toString() + "\n";
		fullString += "Memory:\n";
		for(Word word : memory) {
			fullString += String.format("%04x", i);
			fullString += "| " + word.getStringValue() + "\n";
			i++;
		}
		
		return fullString;
		
	}

	
	
}
