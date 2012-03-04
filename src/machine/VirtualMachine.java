package machine;

public class VirtualMachine {
	
	public static final int VIRTUAL_MEMORY_SIZE = 0xff;
	private static final int WORD_SIZE = 4;
	
	private VirtualMachineRegisters registers;
	//ToDo memory ... Class or RealMachine field ?
	private char[][] memory;
	
	public VirtualMachine(VirtualMachineRegisters registers) {
		this.registers = registers;
		this.memory = new char[VIRTUAL_MEMORY_SIZE][WORD_SIZE];
		
	}
	
	public VirtualMachineRegisters getRegisters() {
		return this.registers;
	}
	
	public char[] getWord(int index) {
		
		return memory[index];
	}
	
}
