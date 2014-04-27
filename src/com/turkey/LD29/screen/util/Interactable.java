package com.turkey.LD29.screen.util;


import com.turkey.LD29.Images.Image;

public class Interactable
{
	protected int x;
	protected int y;
	protected int xSize;
	protected int ySize;
	protected Image selected, unselected;

	protected boolean isSelected = false;
	protected boolean isHover = true;
	protected boolean isClick = false;
	
	protected String name;
	
	protected boolean isVisible = true;

	public Interactable(int Bx, int By, int BxSize, int BySize, Image SelectedImage, Image UnSelectedImage, String n)
	{
		x = Bx;
		y = By;
		xSize = BxSize;
		ySize = BySize;
		selected = SelectedImage;
		unselected = UnSelectedImage;
		name = n;
	}

	public boolean contains(int mX, int mY)
	{
		if((mX >= x && mX < x+xSize) && (mY >= y && mY < y+ySize))
		{
			return true;
		}
		return false;
	}

	public Image getSelectedImage()
	{
		return selected;
	}

	public Image getUnSelectedImage()
	{
		return unselected;
	}

	public void setSelected(boolean toggle)
	{
		isSelected = toggle;
	}
	public boolean isSelected()
	{
		return isSelected;
	}

	public Image getCurrentImage()
	{
		return (isSelected) ? selected : unselected;
	}

	public int getHeight()
	{
		return ySize;
	}
	public int getWidth()
	{
		return xSize;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public int[] getCurrentPixelArray()
	{
		if(isSelected)
			return selected.getPixels();
		else
			return unselected.getPixels();
	}
	
	public void setHover(boolean b)
	{
		isHover = b;
	}
	public void setClick(boolean b)
	{
		isClick = b;
	}
	public boolean isHover()
	{
		return isHover;
	}
	public boolean isClick()
	{
		return isClick;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void show()
	{
		isVisible = true;
	}
	public void hide()
	{
		isVisible = false;
	}
	public boolean isVisible()
	{
		return isVisible;
	}
}
