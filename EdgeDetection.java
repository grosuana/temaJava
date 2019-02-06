public class EdgeDetection extends GreyScale { //class that handles modifying initial image in order to show only edges

	private int width;
	private int height;
	private int maxGradient = 0;

	public EdgeDetection(String path){ 
		super(path);
		width = this.image.getWidth();
		height = this.image.getHeight();
	}

	public void applyPrewitt(){ //applys prewitt method
		int x = this.width;
        int y = this.height;
        int maxGval = 0;
        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;

        for (int i = 1; i < x - 1; i++) {//goes through the whole image
            for (int j = 1; j < y - 1; j++) {

                int g = this.getGreyScale(i,j); //transforms it into grey scale and also applies the second derivative to it, to get the edges
				if(maxGradient < g) {
                    maxGradient = g; //this one saves the maximum value of the gradient
                }
				edgeColors[i][j] = g; //colors the pixel in the corresponding white shade; the whiter the pixel, the more probable it s an edge
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                this.image.setRGB(i, j, edgeColor);//replaces the whole image with computed pixels
            }
        }
        this.maxGradient = maxGradient;
	}

	public int getWidth(){//image width getter
		return this.width;
	}

	public int getHeight(){//image height getter
		return this.height;
	}

	public int getMaxGradient(){//image gradient getter
		return this.maxGradient;
	}
}