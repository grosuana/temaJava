class argumentError extends errorHandler {
	public void fileNotExist(){
		throw new IllegalArgumentException("Files specified do not exist."); 
	}
}