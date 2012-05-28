package processes;

import exception.ProcessException;
import os.Kernel;
import os.Resource;
import machine.*;

public class Loader extends Process{

	
	public Loader(String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "loaderstart"; 
	}
	
	@Override
	public void run() throws ProcessException {
		
		switch (this.missingResource) {
			
		case "loaderstart":
			
			
			Kernel.getResources().destroy("loaderstart");
			this.missingResource = "vmemory";
			return;
			
		case "vmemory":
			
			Resource dataRes = null, codeRes = null;
			
			try {
				// get memory from realMachine			
				Word[] mem = Realmachine.getVirtualMachineMemory(Integer.parseInt(this.receivedResource.getInfo()));
				
				
				// get data and code from resources list
				dataRes = Kernel.getResources().getStartsWith("DATA");
				String data = dataRes.getId().substring(4);
				
				codeRes = Kernel.getResources().getStartsWith("CODE");
				String code = codeRes.getId().substring(4);
				
				
				// load data and code to memory
				Parser.loadData(data, mem);
				Parser.loadCode(code, mem);
				
				// create resource 
				//Kernel.getResources().create(new Resource("loaderfinish", this.id));
				Kernel.getResources().create(new Resource("taskinmemory", this.id));
				Kernel.getResources().create(new Resource("taskinmemory_true", this.id, this.receivedResource.getInfo()));
		
				
				
			} catch (Exception ex) {
				throw new ProcessException(ex.getMessage());
			} finally {
				//destroy data and code resources
				Kernel.getResources().destroy(dataRes.getId());
				Kernel.getResources().destroy(codeRes.getId());
				
				this.missingResource = "loaderstart";
			}
		
			
			return;
			
		}
	}
}
