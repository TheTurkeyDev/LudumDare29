package com.turkey.LD29.Images;

public class StandAloneImage
{
	private Image image;
	private int x = 0, y = 0;
	private boolean isVisible = true;
	
	public StandAloneImage(Image i, int ix, int iy)
	{
		image = i;
		x = ix;
		y= iy;
	}
	
	public void hide()
	{
		isVisible = false;
	}
	
	public void show()
	{
		isVisible = true;
	}
	
	public boolean isVisible()
	{
		return isVisible;
	}
	
	
	public Image getImage()
	{
		return image;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	
}
