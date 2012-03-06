package machine;

public class Word extends Register {

	public static final byte WORD_SIZE = 4;
	
	public Word() {
		this.value = new char[] {
				'0', '0', '0', '0'
		};
	}
	public Word(char[] initValue) {
		if (initValue.length == 4) 
			this.value = initValue;
		//TODO else throw exception or smth
	}
}
