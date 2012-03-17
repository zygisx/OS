package machine;

public abstract class Register {

	protected char[] value;

	public char[] getValue() {
		return value;
	}

	public void setValue(char[] value) {
		this.value = value;
	}
}