package com.turkey.LD29.screen;

import com.turkey.LD29.Images.Image;
import com.turkey.LD29.screen.util.Button;
import com.turkey.LD29.screen.util.Interactable;

public class GameOverScreen extends Screen
{
	private boolean win = false;
	
	private Image loseImage = new Image(Image.getImage("/Images/GameOverScreen.png"));
	private Image winImage = new Image(Image.getImage("/Images/GameWinScreen.png"));
	private Button restart = new Button(250, 300, 275, 75, new Image(Image.getImage("/Images/ReStartSelected.png")) , new Image(Image.getImage("/Images/ReStartUnSelected.png")), "Restart Button");
	private Button mainMenu = new Button(250, 400, 275, 75, new Image(Image.getImage("/Images/MainSelected.png")) , new Image(Image.getImage("/Images/MainUnSelected.png")), "MainMenu Button");
	public GameOverScreen(String n)
	{
		super(n);
		addInteractable(restart);
		addInteractable(mainMenu);
	}

	public void render()
	{
		int[] screenPixels;
		if(win)
			screenPixels = winImage.getPixels();
		else
			screenPixels = loseImage.getPixels();
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				pixels[width * y + x] = screenPixels[loseImage.getWidth() * y + x];
			}
		}
		super.render();
	}

	public void onClicked(Interactable i)
	{
		if(i.getName().equalsIgnoreCase("Restart Button"))
		{
			((GameScreen)ScreenManager.getInstance().getScreen("Game")).load();
			ScreenManager.getInstance().setCurrentScreen("Game");
		}
		if(i.getName().equalsIgnoreCase("MainMenu Button"))
		{
			ScreenManager.getInstance().setCurrentScreen("Main");
		}
	}
	
	public void setWin(boolean t)
	{
		win = t;
	}
}
