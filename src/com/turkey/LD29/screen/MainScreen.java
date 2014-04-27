package com.turkey.LD29.screen;

import com.turkey.LD29.Images.Image;
import com.turkey.LD29.screen.util.Button;
import com.turkey.LD29.screen.util.Interactable;

public class MainScreen extends Screen
{
	private Image mainImage = new Image(Image.getImage("/Images/MainScreen.png"));
	private Button start = new Button(250, 300, 275, 75, new Image(Image.getImage("/Images/StartSelected.png")) , new Image(Image.getImage("/Images/StartUnSelected.png")), "Start Button");
	public MainScreen(String n)
	{
		super(n);
		super.addInteractable(start);
	}
	
	public void render()
	{
		int[] screenPixels = mainImage.getPixels();
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
					pixels[width * y + x] = screenPixels[mainImage.getWidth() * y + x];
			}
		}
		super.render();
	}
	
	public void onClicked(Interactable i)
	{
		if(i.getName().equalsIgnoreCase("Start Button"))
		{
			ScreenManager.getInstance().setCurrentScreen("Game");
			((GameScreen)ScreenManager.getInstance().getScreen("Game")).load();
		}
	}
}
