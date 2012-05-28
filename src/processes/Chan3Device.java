package processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import machine.RealMachineRegisters;

import os.Kernel;
import os.Resource;

import exception.ProcessException;

public class Chan3Device extends Process {

	
	public Chan3Device (String id, String parent, int priority) {
		super(id, parent, priority);
		this.isSupervisorMode = true;	// true defines that it is system process
		this.status = Status.BLOCKED;
		this.missingResource = "chan3devicestart"; 
	}
	
	@Override
	public void run() throws ProcessException {
		RealMachineRegisters.setCH3(1);
		
		String task;
		try {
			task = this.readFile(Kernel.useTask());
		
		
			// create resource
			task = "TASK" + task;
			Kernel.getResources().create(new Resource(task, this.id));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			
			Kernel.getResources().destroy("chan3devicestart");
			Kernel.getResources().create(new Resource("chan3devicefinised", this.getId()));
			RealMachineRegisters.setCH3(0);
		}
	}
	
	
	/**
	 * efficient way to read entire file to string 
	 * code taken from:
	 * http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	 */
	private String readFile(String path) throws IOException {
		  FileInputStream stream = new FileInputStream(new File(path));
		  try {
		    FileChannel fc = stream.getChannel();
		    MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
		    /* Instead of using default, pass in a decoder. */
		    return Charset.defaultCharset().decode(bb).toString();
		  }
		  finally {
		    stream.close();
		  }
	}
}
