import java.io.File; 

public class FileVerify implements FileInt{//implements interface defined in ArgumentParser
	
	public boolean isPathValid(String path){//checks if path belongs to a file
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
    		return true;
		}
		return false;
	};

	public boolean isPathImage(String path){//checks if path belongs to an image
		String extension = "";
		int i = path.lastIndexOf('.');
		if (i > 0) {
	    	extension = path.substring(i+1);
		}
		return extension.equals("png");
	};

	public boolean isPathFolder(String path){//checks if path belongs to a folder
		File f = new File(path);
		if(f.exists() && f.isDirectory()) { 
    		return true;
		}
		return false;
	};
}