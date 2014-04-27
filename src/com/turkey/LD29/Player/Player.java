package com.turkey.LD29.Player;

import java.awt.event.KeyEvent;

import com.turkey.LD29.Images.Image;
import com.turkey.LD29.Images.StandAloneImage;
import com.turkey.LD29.listeners.ScreenKeyListener;
import com.turkey.LD29.screen.GameScreen;
import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.util.Maze;

public class Player
{
	private Maze maze;
	private int xAbsolute = 50, yAbsolute = 50;
	private int xReletive = 1, yReletive = 1;
	
	private Image north = new Image(Image.getImage("/Images/PlayerNorth.png"));
	private Image east = new Image(Image.getImage("/Images/PlayerEast.png"));
	private Image south = new Image(Image.getImage("/Images/PlayerSouth.png"));
	private Image west = new Image(Image.getImage("/Images/PlayerWest.png"));
	
	private StandAloneImage playerImage = new StandAloneImage(north, xAbsolute, yAbsolute);
	
	private int speed = 1;
	
	public Player(Maze m)
	{
		maze = m;
	}
	
	public void update()
	{
		if(ScreenKeyListener.isKeyPressed(KeyEvent.VK_W))
		{
			yAbsolute-=speed;
			if(maze.isWall(xAbsolute, yAbsolute, 16))
			{
				yAbsolute+=speed;
				GameScreen gs = (GameScreen) ScreenManager.getInstance().getScreen("Game");
				gs.GameOver(false);
			}
			else
				playerImage = new StandAloneImage(north, xAbsolute, yAbsolute);
		}
		if(ScreenKeyListener.isKeyPressed(KeyEvent.VK_A))
		{
			xAbsolute-=speed;
			if(maze.isWall(xAbsolute, yAbsolute, 16))
			{
				xAbsolute+=speed;
				GameScreen gs = (GameScreen) ScreenManager.getInstance().getScreen("Game");
				gs.GameOver(false);
			}
			else
				playerImage = new StandAloneImage(east, xAbsolute, yAbsolute);
		}
		if(ScreenKeyListener.isKeyPressed(KeyEvent.VK_S))
		{
			yAbsolute+=speed;
			if(maze.isWall(xAbsolute, yAbsolute, 16))
			{
				yAbsolute-=speed;
				GameScreen gs = (GameScreen) ScreenManager.getInstance().getScreen("Game");
				gs.GameOver(false);
			}
			else
				playerImage = new StandAloneImage(south, xAbsolute, yAbsolute);
		}
		if(ScreenKeyListener.isKeyPressed(KeyEvent.VK_D))
		{
			xAbsolute+=speed;
			if(maze.isWall(xAbsolute, yAbsolute, 16))
			{
				xAbsolute-=speed;
				GameScreen gs = (GameScreen) ScreenManager.getInstance().getScreen("Game");
				gs.GameOver(false);
			}
			else
				playerImage = new StandAloneImage(west, xAbsolute, yAbsolute);
		}
		xReletive = xAbsolute/maze.getxWallScale();
		yReletive = yAbsolute/maze.getxWallScale();
		if(xReletive == maze.getXSize() - 2 && yReletive == maze.getYSize() - 2)
		{
			GameScreen gs = (GameScreen) ScreenManager.getInstance().getScreen("Game");
			gs.GameOver(true);
		}
	}
	
	public int[] getPixles()
	{
		return playerImage.getImage().getPixels();
	}
	
	public int getAbsoluteX()
	{
		return xAbsolute;
	}
	public int getAbsoluteY()
	{
		return yAbsolute;
	}
	
	public StandAloneImage getImage()
	{
		return playerImage;
	}
	
	public void setLocation(int x, int y)
	{
		xAbsolute = x;
		yAbsolute = y;
	}
}