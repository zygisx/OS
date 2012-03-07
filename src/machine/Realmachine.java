package machine;

import java.util.ArrayList;

public class Realmachine {

	/**
	 * shows from where starts code segment in virtual memory
	 */
	public static final int CODE_SEGMENT_START = 0x81; // not sure if hex OK
	public static final int REAL_MEMORY_SIZE = 0xfff;
	
	private static Word[] memory;
	private static VirtualMachine activeVirtualMachine;
	private static RealMachineRegisters registers;
	private static ArrayList<VirtualMachine> virtualMachines;
	private static Pagination paginationMechanizm;
	
	static {
		memory = new Word[REAL_MEMORY_SIZE];
		/* all memory bytes set to 0 */
		for (int i = 0; i < REAL_MEMORY_SIZE; i++) {
			memory[i] = new Word();
		}
		activeVirtualMachine = null;
		registers = new RealMachineRegisters();
		virtualMachines = new ArrayList<VirtualMachine>();
	}
	
	public static void setActiveVirtualMachine(VirtualMachine machine) {
		activeVirtualMachine = machine;
	}
	
	public static Word getWord(int index) {
		return memory[index];
	}
	
	public static void addVirtualMachine(VirtualMachine vm) {
		virtualMachines.add(vm);
	}
	
}
