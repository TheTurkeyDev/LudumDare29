package com.turkey.LD29.Images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image 
{
	private BufferedImage image;
	private int width, height;
	
	public Image(BufferedImage img)
	{
		image = img;
		width = img.getWidth();
		height = img.getHeight();
	}
	
	public static BufferedImage getImage(String path){
		try {
			return ImageIO.read(Image.class.getResource(path));
		} catch (IOException e) {
			return null;
		}
	}
	
	public int[] getPixels()
	{
		int w = image.getWidth(), h=image.getHeight();
		return image.getRGB(0, 0, w, h, new int[w*h], 0, w);
	}
	
	public int[] getSubPixels(int x, int y, int xsize, int ysize)
	{
		int w = image.getWidth(), h=image.getHeight();
		return image.getRGB(x, y, xsize, ysize, new int[w*h], 0, xsize);
	}
	
	public BufferedImage getSubImage(int x, int y, int xsize, int ysize)
	{
		return image.getSubimage(x, y, xsize, ysize);
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
}
