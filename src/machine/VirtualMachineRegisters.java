package machine;

public class VirtualMachineRegisters {
	private Word R1;
	private Word R2;
	private byte IC;
	private byte SF;
	
	
	/**
	 * By default all registers values are zero
	 */
	public VirtualMachineRegisters() {
		this.R1 = new Word();
		this.R2 = new Word();
	}
	
	public Word getR1() {
		return this.R1;
	}
	public void setR1(Word r1) {
		this.R1 = r1;
	}
	public Word getR2() {
		return this.R2;
	}
	public void setR2(Word r2) {
		this.R2 = r2;
	}
	public byte getIC() {
		return this.IC;
	}
	public void setIC(byte iC) {
		this.IC = iC;
	}
	public byte getSF() {
		return this.SF;
	}
	public void setSF(byte sF) {
		this.SF = sF;
	}
	
}
