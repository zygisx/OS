package machine;

public class Word extends Register {

	public static final byte WORD_SIZE = 4;
	
	private boolean hexValue = true; // by default word holds hex values
	/* constructors */
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
			this.setWordHexInt(initValue); 
		}
		else {
			this.setWordDecInt(initValue);
		}
	}
	
	/* setters */
	
	@Override
	public void setValue(char[] value) {
		if (value.length == WORD_SIZE) 
			super.setValue(value);
		//TODO else throw exception or smth
	}
	
	public void setWordString(String value) {
		String formatString = "%1$#" + WORD_SIZE + "s";
//		System.out.println("|" + value + "|");
//		String formattedValue = String.format(formatString, value);
//		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = value.toCharArray();
	}
	
	public void setWordDecInt(int value) {
		String formatString = "%0" + WORD_SIZE + "d";
		String formattedValue = String.format(formatString, value);
		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = formattedValue.toCharArray();
		this.hexValue = false;
	}
	
	public void setWordHexInt(int value) {
		String formatString = "%0" + WORD_SIZE + "x";
		String formattedValue = String.format(formatString, value);
		formattedValue = formattedValue.substring(0, WORD_SIZE);
		this.value = formattedValue.toCharArray();
		this.hexValue = true;
	}
	
	/* getters */
	public String getStringValue() {
		return new String(this.value);
	}
	
	public int getDecimalValue() {
		String string = this.getStringValue().replaceAll(" ", "");
		if (hexValue)
			return Integer.parseInt(string, 16);
		else
			return Integer.parseInt(string);
	}
	/* For now i finally understood that these methods are identical :D 
	 * Firstly i thought both are necessary */ 
	public int getHexValue() {
		String string = this.getStringValue().replaceAll(" ", "");
		if (hexValue)
			return Integer.parseInt(string, 16);
		else 
			return Integer.parseInt(string);
	}
	
	public void setHexValues(boolean b) {
		this.hexValue = b;
	}
	public boolean isHexValue() {
		return this.hexValue;
	}
}
