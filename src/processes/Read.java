package processes;

public class Read extends Process {
	
	
	private String filename;
	
	public Read(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "inputsream"; //TODO fix resource name. It don't correspond purpose
	}
	
	public void setFileName(String fileName) {
		this.filename = fileName;
	}
	
	@Override
	public void run() {
		
		switch (this.missingResource) {
			/*
			case inputStream:
				
			case supervizorinës atminties resursas:
			
			
			
			Klausimas??? Kaip jam paduoti kokius failus skaityt, per resursa ? Ar kaþkaip ?
			
			*/
		}
	}
}
