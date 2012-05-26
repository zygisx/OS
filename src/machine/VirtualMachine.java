package machine;

import exception.AddressOutOfBoundsException;
import exception.IncorrectCommandException;
import exception.VirtualMachineProgramException;


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
	
	
	public void processCommand(String command) throws VirtualMachineProgramException {
		String commandPart;
		int address = -1;
		if (!command.equals("HALT")) {
			commandPart = command.substring(0, 2);
			command = command.replaceAll("\\s+", "");
//			System.out.println(command);   // uncomment to see current command
			if (command.length() >= 4) {
				try {
					address = Integer.parseInt(command.substring(2, 4), 16);
				} catch (Exception ex) {
					throw new AddressOutOfBoundsException("Wrong address. " + ex.getMessage());
				}
				if (address < 0 || address > (Realmachine.CODE_SEGMENT_START - 1)) {
					throw new AddressOutOfBoundsException("Wrong address. " + address + " is forbidden");
				}
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
				Realmachine.JP(address + Realmachine.CODE_SEGMENT_START);
			break;
			case "JE":
				Realmachine.JE(address + Realmachine.CODE_SEGMENT_START);
			break;
			case "JM":
				Realmachine.JM(address + Realmachine.CODE_SEGMENT_START);
			break;
			case "JL":
				Realmachine.JL(address + Realmachine.CODE_SEGMENT_START);
			break;
			case "JF":
				Realmachine.JF(address + Realmachine.CODE_SEGMENT_START);
			break;
			case "JS":
				Realmachine.JS(address + Realmachine.CODE_SEGMENT_START);
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
			default:
				throw new IncorrectCommandException("Incorrect command in " + this.getRegisters().getIC());
			}
			
		}
		else {
			isHalted = true;
		}
		
		/*
		 * When halt is reached: isHalted = true;
		 */
	}
	
	public void run() throws VirtualMachineProgramException {
		String command = null;
		while (registers.getIC() < memory.length && !isHalted) {
			command = memory[registers.getIC()].getStringValue();
			processCommand(command);
			if (isIcChangeAvailible(command)) {
				registers.setIC(registers.getIC()+1);
			}
		}
	}
	
	public String step() throws VirtualMachineProgramException {
		String command = null;
		if(!isHalted) {
			command = memory[registers.getIC()].getStringValue().toUpperCase();
			// new lines for interrupts with IO operations
			String prefix = command.substring(0, 2);
			if (prefix.equals("GD") || prefix.equals("PD") || prefix.equals("PP")) {
				return command;
			}
			processCommand(command);
			if (isIcChangeAvailible(command)) {
				registers.setIC(registers.getIC()+1);
			}
		}
		return command;
		
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
