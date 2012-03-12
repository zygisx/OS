package machine;

public class VirtualMachine {
	
	public static final int VIRTUAL_MEMORY_SIZE = 0x100;
	
	private VirtualMachineRegisters registers;
	private Word[] memory;
	
	public VirtualMachine(VirtualMachineRegisters registers, Word[] memory) {
		this.registers = registers;
		this.memory = memory;
		
	}
	
	public VirtualMachineRegisters getRegisters() {
		return this.registers;
	}
	
	public Word getWord(int index) {
		return this.memory[index];
	}
	
	
	public void processCommand(String command) {
		String commandPart;
		byte address = 0;
		commandPart = command.substring(0, 1);
		if (command.length() >= 4) {
			address = Byte.parseByte(command.substring(2,3));
		}
		
		switch (commandPart) {
		case "AD":
			Realmachine.AD(address);
		break;
		case "SB":
			Realmachine.SB(address);
		break;
		case "ML":
			Realmachine.ML(address);
		break;
		case "DV":
			Realmachine.DV(address);
		break;
		case "CP":
			Realmachine.CP();
		break;
		case "CS":
			Realmachine.CS();
		break;
		case "L1":
			Realmachine.L1(address);
		break;
		case "L2":
			Realmachine.L2(address);
		break;
		case "S1":
			Realmachine.S1(address);
		break;
		case "S2":
			Realmachine.S2(address);
		break;
		case "SI":
			Realmachine.SI(address);
		break;
		case "JP":
			Realmachine.JP(address);
		break;
		case "JE":
			Realmachine.JE(address);
		break;
		case "JM":
			Realmachine.JM(address);
		break;
		case "JL":
			Realmachine.JL(address);
		break;
		case "JF":
			Realmachine.JF(address);
		break;
		case "JS":
			Realmachine.JS(address);
		break;
		}
	}
	
}
