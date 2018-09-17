package remoteobjects;

import java.util.List;
import java.util.ArrayList;

public class ResponseLogin extends RFSCommand{

	private static final long serialVersionUID = 1L;
	public List<FileMetadata> availableFiles;
	
	public ResponseLogin() {
		this.availableFiles = new ArrayList<FileMetadata>();
	}
	
	public void setAvailableFiles(List<FileMetadata> files) {
		availableFiles = new ArrayList<FileMetadata>(files);		
	}
	
	public List<FileMetadata> getAvailableFiles(){
		return this.availableFiles;
	}
	
}
