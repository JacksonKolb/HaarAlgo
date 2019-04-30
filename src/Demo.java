import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Demo {

	public static void main(String[] args) throws IOException {

		// With an image :

		File inputImage = new File("mona.jpg");
		BufferedImage image = ImageIO.read(inputImage);

		double[][] imageData = ImageMatrixIO.imageToMat(image);
		HaarWavelet2D mat = new HaarWavelet2D(imageData);

		mat.standardDecomposition();
		double[][] result = mat.getMatrix();
		double [][] data = new double[result.length][result[0].length];
		int count = 0;
		result = Arrays.copyOfRange(result, result.length / 2 - 1  , result.length);
		for( double[] item: result){
			data[count] = Arrays.copyOfRange(item,item.length/2-1,item.length);
			count++;
		}


		ImageIO.write(ImageMatrixIO.matToImage(data), "png", new File("output.png"));

	}

}
