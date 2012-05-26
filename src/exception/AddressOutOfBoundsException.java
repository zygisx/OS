package exception;

public class AddressOutOfBoundsException extends VirtualMachineProgramException {


	private static final long serialVersionUID = 1L;

	public AddressOutOfBoundsException(String message) {
		super(message);
	}
}
