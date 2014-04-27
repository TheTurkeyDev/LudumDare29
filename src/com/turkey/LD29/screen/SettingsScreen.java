package com.turkey.LD29.screen;

import com.turkey.LD29.Images.Image;
import com.turkey.LD29.screen.util.Button;
import com.turkey.LD29.screen.util.Interactable;

public class SettingsScreen extends Screen
{
	private Image background = new Image(Image.getImage("/Images/SettingsScreen.png"));
	private Button easy = new Button(250, 150, 275, 75, new Image(Image.getImage("/Images/EasySelected.png")) , new Image(Image.getImage("/Images/EasyUnSelected.png")), "Easy Button");
	private Button medium = new Button(250, 315, 275, 75, new Image(Image.getImage("/Images/IntermediateSelected.png")) , new Image(Image.getImage("/Images/IntermediateUnSelected.png")), "Medium Button");
	private Button hard = new Button(250, 475, 275, 75, new Image(Image.getImage("/Images/HardSelected.png")) , new Image(Image.getImage("/Images/HardUnSelected.png")), "Hard Button");
	private Button mainMenu = new Button(250, 60, 275, 75, new Image(Image.getImage("/Images/MainSelected.png")) , new Image(Image.getImage("/Images/MainUnSelected.png")), "MainMenu Button");

	private Interactable selected = easy;

	public SettingsScreen(String n)
	{
		super(n);
		easy.setHover(false);
		medium.setHover(false);
		hard.setHover(false);
		easy.setClick(true);
		medium.setClick(true);
		hard.setClick(true);
		easy.setSelected(true);
		addInteractable(easy);
		addInteractable(medium);
		addInteractable(hard);
		addInteractable(mainMenu);
	}

	public void render()
	{
		int[] screenPixels = background.getPixels();
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				pixels[width * y + x] = screenPixels[background.getWidth() * y + x];
			}
		}
		super.render();
	}

	public void onClicked(Interactable i)
	{
		if(i.getName().equalsIgnoreCase("Easy Button"))
		{
			medium.setSelected(false);
			hard.setSelected(false);
			selected = easy;
		}
		else if(i.getName().equalsIgnoreCase("Medium Button"))
		{
			easy.setSelected(false);
			hard.setSelected(false);
			selected = medium;
		}
		else if(i.getName().equalsIgnoreCase("Hard Button"))
		{
			easy.setSelected(false);
			medium.setSelected(false);
			selected = hard;
		}
		else if(i.getName().equalsIgnoreCase("MainMenu Button"))
		{
			ScreenManager.getInstance().setCurrentScreen("Main");
		}
	}

	public int getNumClicks()
	{
		if(selected.getName().equalsIgnoreCase("Easy Button"))
			return 30;
		else if(selected.getName().equalsIgnoreCase("Medium Button"))
			return 20;
		else if(selected.getName().equalsIgnoreCase("Hard Button"))
			return 10;
		return 30;
	}
}
