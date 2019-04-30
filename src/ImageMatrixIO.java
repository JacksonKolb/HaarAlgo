import java.awt.Color;
import java.awt.image.BufferedImage;


public class ImageMatrixIO {
	public static BufferedImage matToImage(double[][] mat){
		int h = mat[0].length;

		int w = mat.length;

		BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

		for( int i = 0 ; i < w; i++){
			for (int j = 0 ; j < h; j++){
				int value = (int) Math.abs(mat[i][j]);
				int color;
				int c = new Color((255*value)/45,(255*value)/45,(255*value)/45).getRGB();

				img.setRGB(i, j, c);
			}
		}
		return img;
	}

	public static double[][] imageToMat(BufferedImage img){
		// Initializing the matrix
		double[][] mat = new double[img.getWidth()][img.getHeight()];

		// Filling the matrix
		for (int i = 0 ; i < img.getWidth(); i++){
			for (int j = 0 ; j < img.getHeight(); j++){
				Color grayColor = new Color(img.getRGB(i,j));
				mat[i][j] = (double) grayColor.getRed();
			}
		}

		return mat;
	}
}
