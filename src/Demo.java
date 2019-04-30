import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Demo {

	public static void main(String[] args) throws IOException {

		// With an image :

		File inputImage = new File("pic3.jpg");
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
		double[][] finalData = Arrays.copyOfRange(data, 0, data.length/2-1);
		double[][] finalData2 = Arrays.copyOfRange(finalData, 0, data.length/2-1);

		for (int i = 0 ; i < finalData.length ; i++){
			for (int j = 0 ; j < finalData[0].length ; j++){
				finalData2[i][j] = finalData[i][j] + 6;
			}
		}



		ImageIO.write(ImageMatrixIO.matToImage(finalData2), "png", new File("output.png"));

	}

}
