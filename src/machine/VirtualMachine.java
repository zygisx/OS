package machine;

public class VirtualMachine {

	
	private VirtualMachineRegisters registers;
	
	public VirtualMachine(VirtualMachineRegisters registers) {
		this.registers = registers;
		
	}
	
	public VirtualMachineRegisters getRegisters() {
		return this.registers;
	}
}
