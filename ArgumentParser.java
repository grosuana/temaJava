//here we handle inputs from cmd line; we break the input and we separate it into flags, Ifile an Ofile
//these will be later handled

import java.util.ArrayList;

public class ArgumentParser { //this will handle argument parsing from cmdline
	private ArrayList<String> flags = new ArrayList<String>();
	private ArrayList<String> files = new ArrayList<String>();
	private String[] legalFlags = {"-m", "--multiple", "-s", "--single", "-g", "--gradient", "-h", "--help", "-o", "--output"}; //list with all flags that can be used
	private ArgumentError error = new ArgumentError(); //errors that we will throw

	public ArgumentParser(String[] args){
		int flagNumber = this.howManyFlags(args);
		int argNumber = this.howManyArgs(args);
		
		if(flagNumber != 0){ //checks that all flags are legal, given first as params and compatible with each other

			if(!this.areFlagsFirst(args)) error.flagError();
			if(hasBothSingleAndMultiple(args)) error.incompatible();
			for (int i = 0; i < flagNumber; i++ ) {
				this.flags.add(args[i]); //puts flags in flags arraylist
			}
			if(!this.checkIfFlagsAvailable()) error.flagNonExistent();
		}
	
		if(argNumber != 0){ //puts paths given as params from cmdline in files arraylist
			for(int i = 0; i < argNumber + flagNumber; i++){
				if(args[i].charAt(0) == '-') continue;
				this.files.add(args[i]);
			}
		}

	}

	public ArrayList<String> getFlags(){ //flags given from cmdline getter
		return this.flags;
	}

	public ArrayList<String> getFiles(){ //file paths given from cmdline getter
		return this.files;
	}

	public boolean hasThisFlag(String thisFlag){//checks if a specific flag was given
		for(String flag : this.flags){
			if(flag.equals(thisFlag)) return true;
		}
		return false;
	}

	private boolean hasBothSingleAndMultiple(String[] args){//checks that only one of --single, --multiple is given, as they are incompatible
		int flagNum = this.howManyFlags(args);
		boolean single = false;
		boolean multiple = false;
		for (int i = 0; i < flagNum && args[i].charAt(0) == '-'; i++ ) {
			if(args[i].charAt(1) == 's') single = true;
			if(args[i].charAt(1) == 'm') multiple = true;
		}
		return single && multiple;
	}
	
	private boolean areFlagsFirst(String[] args){ //checks if flags are given prior to the img paths
		int flagNum = howManyFlags(args);
		boolean answ = true;
		for (int i = 0; i<flagNum; i++ ) {
			if(args[i].charAt(0) != '-') answ = false;
		}
		return answ;
	}

	private int howManyFlags(String[] args){ //counts how many flags the program received as param
	
		int flagNumber = 0;
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0) == '-') flagNumber++;
		};
		return flagNumber;
	}

	private int howManyArgs(String[] args){ //counts how many filePaths it received as param
	
		int argNumber = 0;
		for (int i = 0; i < args.length; i++) {
			if(args[i].charAt(0) != '-') argNumber++;
		};
		return argNumber;
	}
	private boolean checkIfFlagsAvailable(){ //checks if flags given are between the available ones
		boolean answ = true;
		boolean intermediate = false;
		for (String givenFlag : this.getFlags()) {
			intermediate = false;
			for (String legalFlag : this.legalFlags) {
				if(givenFlag.equals(legalFlag)) intermediate = true;
			}
			answ = answ && intermediate;
		}
		return answ;
	}
}

interface FileInt{ //interface for the File class, we define it here since it methods are directly influenced by the functionalities described above
	
	public boolean isPathValid(String path);
	public boolean isPathImage(String path);
	public boolean isPathFolder(String path);
}