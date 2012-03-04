package machine;

public class VirtualMachineRegisters {
	//ToDo is it ok not to have separate registers class???
	private char[] R1;
	private char[] R2;
	private char[] IC;
	private byte SF;
	
	
	/**
	 * By default all registers values are zero
	 */
	public VirtualMachineRegisters() {
		
	}
	
	public char[] getR1() {
		return this.R1;
	}
	public void setR1(char[] r1) {
		this.R1 = r1;
	}
	public char[] getR2() {
		return this.R2;
	}
	public void setR2(char[] r2) {
		this.R2 = r2;
	}
	public char[] getIC() {
		return this.IC;
	}
	public void setIC(char[] iC) {
		this.IC = iC;
	}
	public byte getSF() {
		return this.SF;
	}
	public void setSF(byte sF) {
		this.SF = sF;
	}
	
	
	
	
}
