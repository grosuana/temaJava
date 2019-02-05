abstract public class ErrorHandler { //abstract class which will hanlde error messages to the user
	public void generalError(){
		throw new IllegalArgumentException("Use -h or --help for more information."); 
	}
}