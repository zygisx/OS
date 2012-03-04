package machine;

public class Realmachine {

	/**
	 * shows from where starts code segment in virtual memory
	 */
	public static final int CODE_SEGMENT_START = 0x81; // not sure if hex OK
	public static final int REAL_MEMORY_SIZE = 0xfff;
	private static final int WORD_SIZE = 4;
	private char[][] memory;
	private VirtualMachine activeVirtualMachine;
	private RealMachineRegisters registers;
	
	public Realmachine() {
		this.memory = new char[REAL_MEMORY_SIZE][WORD_SIZE];
		this.activeVirtualMachine = null;
		this.registers = new RealMachineRegisters();
	}
	
	public void setActiveVirtualMachine(VirtualMachine machine) {
		this.activeVirtualMachine = machine;
	}
	
	public char[] getWord(int index) {
		return memory[index];
	}
}
