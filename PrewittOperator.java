import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class PrewittOperator {

    public static void main(String[] args) throws IOException {//main starts
      	System.out.println("Started");
      	long startTimeTotal = System.nanoTime(); //remembers when program launched in execution
      	int maxGrad = 0;
    	ArgumentError error = new ArgumentError(); //handles errors
    	if(args.length == 0){ //if user doesn t give any arguments
    		error.generalError();
    	}else{
    		long startTimeArgs = System.nanoTime();
			ArgumentParser arguments = new ArgumentParser(args);
			ArrayList<String> flags = arguments.getFlags();
			ArrayList<String> paths = arguments.getFiles();
			FileVerify fileVer = new FileVerify();
			long stopTimeArgs = System.nanoTime();
			System.out.println("Parsed arguments in " + ((stopTimeArgs-startTimeArgs)/ 1000000) + " ms.");
			if(arguments.hasThisFlag("-h") || arguments.hasThisFlag("--help")){ //in case -h option is selected, display help
				System.out.println("Use: <FLAGS> <PATHS>\nEXAMPLES:\n  -> -s <PathToInputFile.png>\n  -> -s -o <PathToInputFile.png> <PathToOutputFile.png>\n  -> -m <PathToInputFile 1> ... <PathToInputFile n>\n  -> -m -o <PathToInputFile 1> ... <PathToInputFile n> <PathToOutputFolder>\nAvailable flags:\n\t-s (--single): provide path to a single image;\n\t-m (--multiple): provide path to multiple images;\n\t-o (--output): provide output file (when used with -s) or folder (when used with -m);\n\t-g (--gradient): also returns maximum gradient value");
			}
			
			if(arguments.hasThisFlag("-s") || arguments.hasThisFlag("--single")){
				if(arguments.hasThisFlag("-o") || arguments.hasThisFlag("--output")){ //if we have a single input file and given output file
					if(paths.size() != 2) error.inconsistent();
					else{
						long startTimePic = System.nanoTime();
						String inf = paths.get(0);
						String ouf = paths.get(1);
						if(fileVer.isPathValid(inf) && fileVer.isPathImage(inf)){
					      	EdgeDetection edge = new EdgeDetection(inf);
					        edge.applyPrewitt();
					        BufferedImage image = edge.image;
							File outputfile = new File(ouf);
							maxGrad = edge.getMaxGradient();
					        ImageIO.write(image, "png", outputfile);
					        long stopTimePic = System.nanoTime();
					        System.out.println("Parsed single photo in " + ((stopTimePic-startTimePic)/ 1000000) + " ms.");
						}else error.fileNotExist();
					}
					
				}else{	//if we have a single input file and no given output file (defaults to output.png)
			      	if(paths.size() != 1) error.inconsistent();
					else{
						long startTimePic = System.nanoTime();
						String inf = paths.get(0);
						String ouf = "output.png";
						if(fileVer.isPathValid(inf) && fileVer.isPathImage(inf)){
					      	EdgeDetection edge = new EdgeDetection(inf);
					        edge.applyPrewitt();
					        BufferedImage image = edge.image;
							File outputfile = new File(ouf);
					        ImageIO.write(image, "png", outputfile);
					        maxGrad = edge.getMaxGradient();
					        long stopTimePic = System.nanoTime();
					        System.out.println("Parsed single photo in " + ((stopTimePic-startTimePic)/ 1000000) + " ms.");
						}else error.fileNotExist();
					}
				}
			}

			if(arguments.hasThisFlag("-m") || arguments.hasThisFlag("--multiple")){ //if we have multiple input files
				if(arguments.hasThisFlag("-o") || arguments.hasThisFlag("--output")){ //and also an output folder given
					if(paths.size() < 3) error.inconsistent();
					else{
						long startTimePic = System.nanoTime();
						long copy = startTimePic;
						long stopTimePic = System.nanoTime();
						String ouf = paths.get(paths.size()-1);
						if(fileVer.isPathFolder(ouf)){
							for (int i=0; i<paths.size()-1;i++ ) {
								String inf = paths.get(i);
								if(fileVer.isPathValid(inf) && fileVer.isPathImage(inf)){
					      			EdgeDetection edge = new EdgeDetection(inf);
					       			edge.applyPrewitt();
					       			BufferedImage image = edge.image;
									File outputfile = new File(ouf+"/output" + i +".png");
					      			ImageIO.write(image, "png", outputfile);
					      			maxGrad = edge.getMaxGradient();
					      			stopTimePic = System.nanoTime();
					       			System.out.println("Parsed photo number " + (i+1) +" in " + ((stopTimePic-startTimePic)/ 1000000) + " ms.");
									startTimePic = System.nanoTime();
								}else error.fileNotExist();
							
							}
							stopTimePic = System.nanoTime();
							System.out.println("Parsed all photos in " + ((stopTimePic-copy)/ 1000000) + " ms.");
						} else error.fileNotExist();
					}
				}else{//if we have multiple input files but no output folder given (defaults to ouputs/)
					if(paths.size() < 2) error.inconsistent();
					else{
						long startTimePic = System.nanoTime();
						long copy = startTimePic;
						long stopTimePic = System.nanoTime();
						new File("outputFolder").mkdirs();
						String ouf = "outputFolder";
						for (int i=0; i<paths.size();i++ ) {
							String inf = paths.get(i);
							if(fileVer.isPathValid(inf) && fileVer.isPathImage(inf)){
				      			EdgeDetection edge = new EdgeDetection(inf);
				       			edge.applyPrewitt();
				       			BufferedImage image = edge.image;
								File outputfile = new File(ouf+"/output" + i +".png");
				      			ImageIO.write(image, "png", outputfile);
				      			maxGrad = edge.getMaxGradient();
				      			stopTimePic = System.nanoTime();
					       		System.out.println("Parsed photo number " + (i+1) +" in " + ((stopTimePic-startTimePic)/ 1000000) + " ms.");
								startTimePic = System.nanoTime();
							}else error.fileNotExist();
						}
						stopTimePic = System.nanoTime();
						System.out.println("Parsed all photos in " + ((stopTimePic-copy)/ 1000000) + " ms.");
					}
				}
			}

			if(arguments.hasThisFlag("-g") || arguments.hasThisFlag("--gradient")){//displays the max grad found in the photo
				long stopTimeGrad = System.nanoTime();
			}
    	}
    	long stopTimeTotal = System.nanoTime();
        System.out.println("Finished in " + ((stopTimeTotal-startTimeTotal)/ 1000000) + " ms.");
    }

}