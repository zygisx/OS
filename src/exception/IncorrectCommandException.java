package exception;

public class IncorrectCommandException extends VirtualMachineProgramException {


	private static final long serialVersionUID = 1L;

	public IncorrectCommandException(String message) {
		super(message);
	}
}
