package machine;

public class VirtualMachineRegisters {
	private Word R1;
	private Word R2;
	private int IC, PLR;
	private byte SF;
	
	
	/**
	 * By default all registers values are zero
	 */
	public VirtualMachineRegisters() {
		this.R1 = new Word();
		this.R2 = new Word();
	}
	
	public int getPLR() {
		return PLR;
	}
	
	public void setPLR(int pLR) {
		PLR = pLR;
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
	public int getIC() {
		return this.IC;
	}
	public void setIC(int iC) {
		this.IC = iC;
	}
	public byte getSF() {
		return this.SF;
	}
	public void setSF(byte sF) {
		this.SF = sF;
	}
	
}
