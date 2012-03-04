package machine;

public class RealMachineRegisters {
	private static char[] R1, R2, IC, PLR, MODE;
	private static byte SF, TIMER, CH1, CH2, CH3, CH4, PI, SI, IOI, TI;
	
	public static char[] getR1() {
		return R1;
	}
	public static char[] getR2() {
		return R2;
	}
	public static char[] getIC() {
		return IC;
	}
	public static char[] getPLR() {
		return PLR;
	}
	public static char[] getMODE() {
		return MODE;
	}
	public static byte getSF() {
		return SF;
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
	public static void setR1(char[] r1) {
		R1 = r1;
	}
	public static void setR2(char[] r2) {
		R2 = r2;
	}
	public static void setIC(char[] iC) {
		IC = iC;
	}
	public static void setPLR(char[] pLR) {
		PLR = pLR;
	}
	public static void setMODE(char[] mODE) {
		MODE = mODE;
	}
	public static void setSF(byte sF) {
		SF = sF;
	}
	public static void setTIMER(byte tIMER) {
		TIMER = tIMER;
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
}

