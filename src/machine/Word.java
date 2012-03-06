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
		if (initValue.length() >= WORD_SIZE) {
			initValue = initValue.substring(0, WORD_SIZE);
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
		String formatString;
		if (hexFlag) {
			formatString = "%0" + WORD_SIZE + "x"; 
		}
		else {
			formatString = "%0" + WORD_SIZE + "d";
		}
		String formattedValue = String.format(formatString, initValue);
		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = formattedValue.toCharArray();
	}
	
	
	public String getStringValue() {
		return new String(this.value);
	}
	
	
	
}
