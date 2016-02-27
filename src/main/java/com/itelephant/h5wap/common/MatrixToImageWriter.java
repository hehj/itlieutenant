package com.itelephant.h5wap.common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.google.zxing.common.BitMatrix;
/***
 * 生成二维码的工具类
 * 
 * 基于Google zxing
 * 
 * @author HeHangjie
 *
 */
public final class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriter() {
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}

	public static void writeToStream(BitMatrix matrix, String format,
			OutputStream stream,String logoPath,int xy) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		Graphics2D gs = image.createGraphics();  
        
        //载入logo  
        Image img = ImageIO.read(new File(logoPath));  
        gs.drawImage(img, xy, xy, null);  
        gs.dispose();  
        img.flush();  
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format "
					+ format);
		}
	}

}