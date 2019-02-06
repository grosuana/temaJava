public class EdgeDetection extends GreyScale { 

	private int width;
	private int height;
	private int maxGradient = 0;

	public EdgeDetection(String path){
		super("engine.png");
		
		width = this.image.getWidth();
		height = this.image.getHeight();
	}

	public void applyPrewitt(){
		int x = this.width;
        int y = this.height;
        int maxGval = 0;
        int[][] edgeColors = new int[x][y];
        int maxGradient = -1;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                int g = this.getGreyScale(i,j);
				if(maxGradient < g) {
                    maxGradient = g;
                }
				edgeColors[i][j] = g;
            }
        }

        double scale = 255.0 / maxGradient;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                int edgeColor = edgeColors[i][j];
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                this.image.setRGB(i, j, edgeColor);
            }
        }
        this.maxGradient = maxGradient;
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	public int getMaxGradient(){
		return this.maxGradient;
	}
}