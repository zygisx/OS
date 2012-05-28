package machine;

import os.Kernel;


public class RealMachineRegisters {
	private static Word R1, R2;	
	private static char MODE;
	private static byte SF;
	private static int CH3, CH1, CH2, CH4, PI, SI, IOI, TI;
	private static int IC, PLR;
	private static Timer TIMER;
	
	static {
		setR1(new Word());
		setR2(new Word());
		TIMER = new Timer();
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

	public static char getMODE() {
		if (Kernel.getCurrentProcess() != null) {
			return (Kernel.getCurrentProcess().isSupervizorMode()) ? 
					'S' :
					'U';
		}
		else return 'S';
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
	public static int getTIMER() {
		return TIMER.getTime();
	}
	public static Timer getTimer() {
		return TIMER;
	}
	public static int getCH1() {
		return CH1;
	}
	public static int getCH2() {
		return CH2;
	}
	public static int getCH3() {
		return CH3;
	}
	public static int getCH4() {
		return CH4;
	}
	public static int getPI() {
		return PI;
	}
	public static int getSI() {
		return SI;
	}
	public static int getIOI() {
		return IOI;
	}
	public static int getTI() {
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
	
	public static void setMODE(char mODE) {
		// no need to set mode
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
		TIMER.reset();
	}
	public static void setCH1(int cH1) {
		CH1 = cH1;
	}
	public static void setCH2(int cH2) {
		CH2 = cH2;
	}
	public static void setCH3(int i) {
		CH3 = i;
	}
	public static void setCH4(int cH4) {
		CH4 = cH4;
	}
	public static void setPI(int pI) {
		PI = pI;
	}
	public static void setSI(int sI) {
		SI = sI;
	}
	public static void setIOI(int iOI) {
		IOI = iOI;
	}
	public static void setTI(int tI) {
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

