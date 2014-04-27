package com.turkey.LD29.screen.util;

import com.turkey.LD29.Images.Image;

public class TextBox 
{
	int x;
	int y;
	int xSize;
	int ySize;
	int indent = 0;
	boolean isSelected = false;
	Image Selected, Unselected;
	String text;
	
	public TextBox(int Bx, int By, int BxSize, int BySize, Image Image)
	{
		x = Bx;
		y = By;
		xSize = BxSize;
		ySize = BySize;
		Unselected = Image;
	}
	public TextBox(int Bx, int By, int BxSize, int BySize, Image SelectedImage, Image UnSelectedImage)
	{
		x = Bx;
		y = By;
		xSize = BxSize;
		ySize = BySize;
		Selected = SelectedImage;
		Unselected = UnSelectedImage;
	}
	public TextBox(int Bx, int By, int BxSize, int BySize, int Indent, Image SelectedImage, Image UnSelectedImage)
	{
		x = Bx;
		y = By;
		xSize = BxSize;
		ySize = BySize;
		Selected = SelectedImage;
		Unselected = UnSelectedImage;
		indent = Indent;
	}
	
	public boolean contains(int xloc, int yloc)
	{
		if((xloc > x && xloc < x + xSize) && (yloc > y && xloc < y + ySize))
		{
			return true;
		}
		return false;
	}
	
	public void addletter(String letter)
	{
		text = text + "" + letter;
	}
	public void removeletter()
	{
		text = text.substring(text.length()-1);
	}
}
