package machine;

import java.util.ArrayList;

public class Realmachine {

	/**
	 * shows from where starts code segment in virtual memory
	 */
	public static final int CODE_SEGMENT_START = 0x81;
	public static final int REAL_MEMORY_SIZE = 0x1000;
	public static final int BLOCK_SIZE = 0x10; // block size is 0x10 = 16 not 0xf = 15
	public static final int PAGINATION_TABLE_SIZE = 0x10; //table consist of 16 blocks
	public static final int VIRTUAL_MACHINE_MEMORY_SIZE = 0x10;
	
	private static Word[] memory;
	private static VirtualMachine activeVirtualMachine;
	private static RealMachineRegisters registers;
	private static ArrayList<VirtualMachine> virtualMachines;
	private static Pagination paginationMechanism;
	
	static {
		memory = new Word[REAL_MEMORY_SIZE];
		/* all memory bytes set to 0 */
		for (int i = 0; i < REAL_MEMORY_SIZE; i++) {
			memory[i] = new Word();
		}
		activeVirtualMachine = null;
		registers = new RealMachineRegisters();
		virtualMachines = new ArrayList<VirtualMachine>();
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
	
	public static void setActiveVirtualMachine(VirtualMachine machine) {
		activeVirtualMachine = machine;
	}
	
	public static Word getWord(int index) {
		return memory[index];
	}
	
	public static Word[] getBlock(int blockNum) {
		if (blockNum < (REAL_MEMORY_SIZE / BLOCK_SIZE)) { // blockNum < 256 
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
	
	public static void addVirtualMachine(VirtualMachine vm) {
		virtualMachines.add(vm);
	}
	
	public static VirtualMachine initVirtualMachine() { // not sure about return type...
		VirtualMachine vm = 
				new VirtualMachine(new VirtualMachineRegisters(), 
						paginationMechanism.getVirtualMachineMemory());
		addVirtualMachine(vm);
		return vm;
	}
	
	// COMMANDS
	
	public static void AD(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		int result = RealMachineRegisters.getR1().getHexValue() + RealMachineRegisters.getR2().getHexValue();
		memory[realAddress].setWordHexInt(result);
	}
	
	public static void SB(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		int result = RealMachineRegisters.getR1().getHexValue() - RealMachineRegisters.getR2().getHexValue();
		memory[realAddress].setWordHexInt(result);
	}
	
	public static void ML(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		int result = 0;
		int num1 = RealMachineRegisters.getR1().getHexValue();
		int num2 = RealMachineRegisters.getR2().getHexValue();
		
		for(int i = 0; i < num2; i++) {
			result = result + num1;
		}
		
		memory[realAddress].setWordHexInt(result);
	}
	
	public static void DV(byte virtualAddress) {
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
	}
	
	public static void CP() {
		int R1 = RealMachineRegisters.getR1().getHexValue();
		int R2 = RealMachineRegisters.getR2().getHexValue();
		
		if (R1 == R2) {
			RealMachineRegisters.setSF((byte) 0);
		} else if(R1 > R2) {
			RealMachineRegisters.setSF((byte) 1);
		} else {
			RealMachineRegisters.setSF((byte) 2);
		}
	}
	
	public static void CS() {
		int R1 = RealMachineRegisters.getR1().getHexValue();
		int R2 = RealMachineRegisters.getR2().getHexValue();
		
		if(R1 >= R2) {
			RealMachineRegisters.setSF((byte) 3);
		} else {
			RealMachineRegisters.setSF((byte) 4);
		}
	}
	
	public static void L1(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		RealMachineRegisters.getR1().setWordHexInt(memory[realAddress].getHexValue());
	}
	
	public static void L2(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		RealMachineRegisters.getR2().setWordHexInt(memory[realAddress].getHexValue());
	}
	
	public static void S1(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		memory[realAddress].setWordHexInt(RealMachineRegisters.getR1().getHexValue());
	}
	
	public static void S2(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		memory[realAddress].setWordHexInt(RealMachineRegisters.getR2().getHexValue());
	}
	
	public static void SI(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
		memory[realAddress].setWordHexInt(RealMachineRegisters.getIC());
	}
	
	public static void JP(byte virtualAddress) {
		int realAddress = paginationMechanism.getRealAddress(virtualAddress);
//		RealMachineRegisters.setIC(iC)
	}
	
	// toString
	
	public static String toString2()
	{
		int i = 0;
		String fullString;
		fullString = "Registers \n";
		fullString += registers.toString() + "\n";
		fullString += "Memory:\n";
		for(Word word : memory) {
			fullString += i + "| " + word.getStringValue() + "\n";
			i++;
		}
		
		return fullString;
		
	}
	
}
