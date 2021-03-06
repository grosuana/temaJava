class ArgumentError extends ErrorHandler { //defines specific error messages in case of argument errors
	public void fileNotExist(){
		throw new IllegalArgumentException("Files specified do not exist."); 
	}

	public void flagError(){
		throw new IllegalArgumentException("Place flags before file paths. Use -h or --h for more information."); 
	}

	public void flagNonExistent(){
		throw new IllegalArgumentException("Flag non-existent. Use -h or --h for more information."); 
	}

	public void incompatible(){
		throw new IllegalArgumentException("Cannot use both -m and -s. Use -h or --h for more information."); 
	}

	public void inconsistent(){
		throw new IllegalArgumentException("Path(s) given inconsistent with flag. Use -h or --h for more information."); 
	}
	public void generalError(){
		throw new IllegalArgumentException("Error parsing files. Use -h or --help for more information."); 
	}
}