package machine;

public class Word extends Register {

	public static final byte WORD_SIZE = 4;
	
	

	public Word() {
		this.value = new char[] {
				'0', '0', '0', '0'
		};
	}
	public Word(char[] initValue) {
		this.setValue(initValue);
	}
	public Word(String initValue) {
		this.setWordString(initValue);
	}
	
	/**
	 * 
	 * @param initValue
	 * @param hexFlag True if initValue in hex, false if in decimal
	 */
	public Word(int initValue, boolean hexFlag) { 
		if (hexFlag) {
			this.setWordDecInt(initValue); 
		}
		else {
			this.setWordDecInt(initValue);
		}
	}
	
	@Override
	public void setValue(char[] value) {
		if (value.length == WORD_SIZE) 
			super.setValue(value);
		//TODO else throw exception or smth
	}
	
	public void setWordString(String value) {
		if (value.length() >= WORD_SIZE) {
			value = value.substring(0, WORD_SIZE);
			this.value = value.toCharArray();
		}
		//TODO else throw exception or smth
	}
	
	public void setWordDecInt(int value) {
		String formatString = "%0" + WORD_SIZE + "d";
		String formattedValue = String.format(formatString, value);
		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = formattedValue.toCharArray();
	}
	
	public void setWordHexInt(int value) {
		String formatString = "%0" + WORD_SIZE + "x";
		String formattedValue = String.format(formatString, value);
		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = formattedValue.toCharArray();
	}
	
	public String getStringValue() {
		return new String(this.value);
	}
	
	
	
}
