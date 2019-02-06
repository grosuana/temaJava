//here we handle inputs from cmd line; we break the input and we separate it into flags, Ifile an Ofile
//these will be later handled

import java.util.ArrayList;

public class ArgumentParser { //this will handle argument parsing
	private ArrayList<String> flags = new ArrayList<String>();
	private ArrayList<String> files = new ArrayList<String>();
	private String[] legalFlags = {"-m", "--multiple", "-s", "--single", "-g", "--gradient", "-h", "--help"};
	private ArgumentError error = new ArgumentError();

	public ArgumentParser(String[] args){
		int flagNumber = this.howManyFlags(args);
		int argNumber = this.howManyArgs(args);
		
		if(flagNumber != 0){

			if(!this.areFlagsFirst(args)) error.flagError();
			if(hasBothSingleAndMultiple(args)) error.incompatible();
			for (int i = 0; i < flagNumber; i++ ) {
				this.flags.add(args[i]);
			}
			if(!this.checkIfFlagsAvailable()) error.flagNonExistent();
		}
	
		if(argNumber != 0){
			for(int i = 0; i < argNumber + flagNumber; i++){
				if(args[i].charAt(0) == '-') continue;
				this.files.add(args[i]);
			}
		}

	}

	public ArrayList<String> getFlags(){
		return this.flags;
	}

	public ArrayList<String> getFiles(){
		return this.files;
	}

	private boolean hasBothSingleAndMultiple(String[] args){
		int flagNum = this.howManyFlags(args);
		boolean single = false;
		boolean multiple = false;
		for (int i = 0; i < flagNum && args[i].charAt(0) == '-'; i++ ) {
			if(args[i].charAt(1) == 's') single = true;
			if(args[i].charAt(1) == 'm') multiple = true;
		}
		return single && multiple;
	}
	
	private boolean areFlagsFirst(String[] args){
		int flagNum = howManyFlags(args);
		boolean answ = true;
		for (int i = 0; i<flagNum; i++ ) {
			if(args[i].charAt(0) != '-') answ = false;
		}
		return answ;
	}

	private int howManyFlags(String[] args){ //counts how many flags did the program receive as param
	
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
	private boolean checkIfFlagsAvailable(){
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

interface File{ //interface for the File class, we define it here since it methods are directly influenced by the functionalities described above
	
	public boolean isPathValid();
	public boolean isPathImage();
}