public class PrewittOperator {

    public static void main(String[] args) {
      	
    	if(args.length != 0){
    		ArgumentParser arguments = new ArgumentParser(args);
    		System.out.println(arguments.getFlags());
    		System.out.println(arguments.getFiles());
    		
    	}
    	
    }

}