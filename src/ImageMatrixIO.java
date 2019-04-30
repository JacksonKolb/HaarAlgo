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
				if (value > 5){
					color = 0xFFFFFF;
				}
				else {
					color = 0x00;
				}
				img.setRGB(i, j, color);
			}
		}
		return img;
	}

	public static double[][] imageToMat(BufferedImage img){
		// Initializing the matrix
		double[][] mat = new double[img.getWidth()][img.getHeight()];
		System.out.println(img.getWidth() + "width");
		System.out.print(img.getHeight());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();


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
