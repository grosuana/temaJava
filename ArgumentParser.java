//here we handle inputs


import java.util.ArrayList;

public class ArgumentParser { //clasa care se va ocupa de parsarea argumentelor
	ArrayList<String> flags;
	ArrayList<String> inputFiles;
	ArrayList<String> outputFiles;

	public ArgumentParser(String[] args){
		int flagNumber = this.howManyFlags(args);
		int argNumber = this.howManyArgs(args);

		if(flagNumber != 0){
			this.flags = new ArrayList<String>();
			for (int i = 0; i < flagNumber; i++ ) {
				this.flags.add(args[i]);
			}
		}

		if(flagNumber != 0){
			this.inputFiles = new ArrayList<String>();
			this.outputFiles = new ArrayList<String>();
			
			for (int i = 0; i < flagNumber; i++ ) {
				this.flags.add(args[i]);
			}
		}

	}

	private int howManyFlags(String[] args){
	
		int flagNumber = 0;
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0) == '-') flagNumber++;
		};
		return flagNumber;
	}

	private int howManyArgs(String[] args){
	
		int argNumber = 0;
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0) != '-') argNumber++;
		};
		return argNumber;
	}
}

interface File{
	public void isPath();
}