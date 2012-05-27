package machine;

import os.Timer;

public class RealMachineRegisters {
	private static Word R1, R2;	
	private static byte MODE;
	private static byte SF, TIMER, CH1, CH2, CH3, CH4, PI, SI, IOI, TI;
	private static int IC, PLR;
	
	static {
		setR1(new Word());
		setR2(new Word());
	}
	
	public static Word getR1() {
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) return vm.getRegisters().getR1();
		return R1;
	}
	public static Word getR2() {
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) return vm.getRegisters().getR2();
		return R2;
	}
	public static int getIC() {
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) return vm.getRegisters().getIC();
		return IC;
	}

	public static byte getMODE() {
		return MODE;
	}
	public static byte getSF() {
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) return vm.getRegisters().getSF();
		return SF;
	}
	public static int getPLR() {
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) return vm.getRegisters().getPLR();
		return PLR;
	}
	public static byte getTIMER() {
		return TIMER;
	}
	public static byte getCH1() {
		return CH1;
	}
	public static byte getCH2() {
		return CH2;
	}
	public static byte getCH3() {
		return CH3;
	}
	public static byte getCH4() {
		return CH4;
	}
	public static byte getPI() {
		return PI;
	}
	public static byte getSI() {
		return SI;
	}
	public static byte getIOI() {
		return IOI;
	}
	public static byte getTI() {
		return TI;
	}
	public static void setR1(Word r1) {
		R1 = r1;
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) vm.getRegisters().setR1(r1);
	}
	public static void setR2(Word r2) {
		R2 = r2;
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) vm.getRegisters().setR2(r2);
	}
	public static void setIC(int iC) {
		IC = iC;
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) vm.getRegisters().setIC(iC);
	}
	
	public static void setMODE(byte mODE) {
		MODE = mODE;
	}
	public static void setSF(byte sF) {
		SF = sF;
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) vm.getRegisters().setSF(sF);
	}
	
	public static void setPLR(byte pLR) {
		PLR = pLR;
		VirtualMachine vm = Realmachine.getActiveVM();
		if (vm != null) vm.getRegisters().setPLR(pLR);
	}
	
	public static void setTIMER(byte tIMER) {
	//	TIMER.reset();
	}
	public static void setCH1(byte cH1) {
		CH1 = cH1;
	}
	public static void setCH2(byte cH2) {
		CH2 = cH2;
	}
	public static void setCH3(byte cH3) {
		CH3 = cH3;
	}
	public static void setCH4(byte cH4) {
		CH4 = cH4;
	}
	public static void setPI(byte pI) {
		PI = pI;
	}
	public static void setSI(byte sI) {
		SI = sI;
	}
	public static void setIOI(byte iOI) {
		IOI = iOI;
	}
	public static void setTI(byte tI) {
		TI = tI;
	}
	
	public static String toString2()
	{
		String fullString = "|R1| " + getR1().getStringValue() + "      |R2| " + getR2().getStringValue() + "      |IC| " + getIC() + "      |PLR| " + getPLR() + "      |MODE| " + getMODE() + "\n";
		fullString += "|SF| " + getSF() + "         |TIMER| " + getTIMER() + "      |CH1| " + getCH1() + "     |CH2| " + getCH2() + "      |CH3| " + getCH3() + "\n";
		fullString += "|CH4| " + getCH4() + "        |PI| " + getPI() + "         |SI| " + getSI() + "      |IOI| " + getIOI() + "      |TI| " + getTI();
		return fullString;
	}
}

