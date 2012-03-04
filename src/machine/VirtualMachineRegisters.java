package machine;

public class VirtualMachineRegisters {
	private char[] R1;
	private char[] R2;
	private char[] IC;
	private byte SF;
	
	
	/**
	 * By default all registers values set 
	 */
	public VirtualMachineRegisters() {
		
	}
	
	public char[] getR1() {
		return R1;
	}
	public void setR1(char[] r1) {
		R1 = r1;
	}
	public char[] getR2() {
		return R2;
	}
	public void setR2(char[] r2) {
		R2 = r2;
	}
	public char[] getIC() {
		return IC;
	}
	public void setIC(char[] iC) {
		IC = iC;
	}
	public byte getSF() {
		return SF;
	}
	public void setSF(byte sF) {
		SF = sF;
	}
	
	
	
	
}
