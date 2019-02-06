import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PrewittOperator {

    public static void main(String[] args) throws IOException {
      	System.out.println("Started");
      	EdgeDetection edge = new EdgeDetection("engine.png");
        edge.applyPrewitt();
        BufferedImage image = edge.image;
		 File outputfile = new File("sobel.png");
        ImageIO.write(image, "png", outputfile);

        
    	if(args.length != 0){
    		ArgumentParser arguments = new ArgumentParser(args);
    		System.out.println(arguments.getFlags());
    		System.out.println(arguments.getFiles());
    		
    	}


    	System.out.println("max : " + edge.getMaxGradient());
        System.out.println("Finished");
    }

}