import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GreyScale {//here we compute the grayscale equivalent of 3x3 pixel groups, and apply sobel operator to it

	public BufferedImage image;

	public GreyScale(String path){
		try{

		File file = new File(path);
        this.image = ImageIO.read(file);
    	}
    	catch(Exception e){
    		System.out.println("An error occured");
    	}
	}
	public int getGreyScale(int pixelX, int pixelY) {//we return the probability that the pixel (X,Y) is an edge
				int val00 = getGrayScale(image.getRGB(pixelX - 1, pixelY  - 1));
                int val01 = getGrayScale(image.getRGB(pixelX - 1, pixelY ));
                int val02 = getGrayScale(image.getRGB(pixelX - 1, pixelY  + 1));

                int val10 = getGrayScale(image.getRGB(pixelX, pixelY  - 1));
                int val11 = getGrayScale(image.getRGB(pixelX, pixelY ));
                int val12 = getGrayScale(image.getRGB(pixelX, pixelY  + 1));

                int val20 = getGrayScale(image.getRGB(pixelX + 1, pixelY  - 1));
                int val21 = getGrayScale(image.getRGB(pixelX + 1, pixelY ));
                int val22 = getGrayScale(image.getRGB(pixelX + 1, pixelY  + 1));//we turn gray the 3x3 square surrounding the pixel

                int gx =  ((-1 * val00) + (0 * val01) + (1 * val02)) 
                        + ((-1 * val10) + (0 * val11) + (1 * val12))
                        + ((-1 * val20) + (0 * val21) + (1 * val22));
                        												//we multiply the square by these two matrixes which will give 
                int gy =  ((-1 * val00) + (-1 * val01) + (-1 * val02))  //us the probability the middle pixel belongs to a vertical/horizontal edge
                        + ((0 * val10) + (0 * val11) + (0 * val12))
                        + ((1 * val20) + (1 * val21) + (1 * val22));

                double gval = Math.sqrt((gx * gx) + (gy * gy)); //the value is the module of ox,oy values
                int g = (int) gval;

                return g;
	}

	public static int  getGrayScale(int rgb) {//function that returns greyscale correspondend of rgb values
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;

        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        //int gray = (r + g + b) / 3;

        return gray;
    }

}