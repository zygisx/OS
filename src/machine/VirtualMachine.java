package machine;

public class VirtualMachine {
	
	public static final int VIRTUAL_MEMORY_SIZE = 0xff;
	
	private VirtualMachineRegisters registers;
	//ToDo memory ... Class or RealMachine field ?
	private Word[] memory;
	
	public VirtualMachine(VirtualMachineRegisters registers, Word[] memory) {
		this.registers = registers;
		this.memory = memory;
		
	}
	
	public VirtualMachineRegisters getRegisters() {
		return this.registers;
	}
	
	public Word getWord(int index) {
		
		return memory[index];
	}
	
}
