package machine;

public class Word extends Register {

	public static final byte WORD_SIZE = 4;
	
	public Word() {
		this.value = new char[] {
				'0', '0', '0', '0'
		};
	}
	public Word(char[] initValue) {
		if (initValue.length == WORD_SIZE) 
			this.value = initValue;
		//TODO else throw exception or smth
	}
	public Word(String initValue) {
		if (initValue.length() == WORD_SIZE) {
			this.value = initValue.toCharArray();
		}
		//TODO else throw exception or smth
	}
	
	/**
	 * 
	 * @param initValue
	 * @param hexFlag True if initValue in hex, false if in decimal
	 */
	public Word(int initValue, boolean hexFlag) { 
		if (hexFlag) {
			this.value = (Integer.toHexString(initValue)).toCharArray();
		}
	}
}
