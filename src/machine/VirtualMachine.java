package machine;


public class VirtualMachine {
	
	public static final int VIRTUAL_MEMORY_SIZE = 0x100;
	
	private VirtualMachineRegisters registers;
	private Word[] memory;
	private boolean isHalted = false;
	
	public VirtualMachine(VirtualMachineRegisters registers, Word[] memory) {
		this.registers = registers;
		this.memory = memory;
		registers.setIC(Realmachine.CODE_SEGMENT_START);
		
	}
	
	public VirtualMachineRegisters getRegisters() {
		return this.registers;
	}
	
	public Word getWord(int index) {
		return this.memory[index];
	}
	
	
	public void processCommand(String command) {
		String commandPart;
		int address = 0;
		if (!command.equals("HALT")) {
			commandPart = command.substring(0, 2);
			if (command.length() >= 4) {
				address = Integer.parseInt(command.substring(2, 4), 16);
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
			case "PD":
				Realmachine.PD(address);
			break;
			case "PP":
				Realmachine.PP(address);
			break;
			case "GD":
				Realmachine.GD(address);
			break;
			}
		}
		else {
			isHalted = true;
		}
		
		/*
		 * When halt is reached: isHalted = true;
		 */
	}
	
	public void run() {
		String command = null;
		while (registers.getIC() < memory.length && !isHalted) {
			command = memory[registers.getIC()].getStringValue();
			processCommand(command);
			Realmachine.getFrame().update();
			if (isIcChangeAvailible(command)) {
				registers.setIC(registers.getIC()+1);
			}
		}
	}
	
	public void step() {
		String command = null;
		if(!isHalted) {
			command = memory[registers.getIC()].getStringValue();
			processCommand(command);
			Realmachine.getFrame().update();
			if (isIcChangeAvailible(command)) {
				registers.setIC(registers.getIC()+1);
			}
		}
	}
	
	private boolean isIcChangeAvailible(String command) {
		if (command.contains("J")) {
			return false; 
		} else {
			return true;
		}
	}

	public boolean isHalted() {
		return isHalted;
	}

	
}
