package com.turkey.LD29.screen.util;

import com.turkey.LD29.Images.Image;

public class Button extends Interactable
{
	
	public Button(int Bx, int By, int BxSize, int BySize, Image SelectedImage, Image UnSelectedImage, String n)
	{
		super(Bx,By,BxSize,BySize,SelectedImage,UnSelectedImage, n);
	}
	public Button(int Bx, int By, int BxSize, int BySize, Image SelectedImage, String name)
	{
		super(Bx,By,BxSize,BySize,SelectedImage,SelectedImage, name);
	}
}
