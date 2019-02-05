class argumentError extends ErrorHandler {
	public void fileNotExist(){
		throw new IllegalArgumentException("Files specified do not exist."); 
	}
}