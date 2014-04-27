package com.turkey.LD29.screen;

import com.turkey.LD29.Images.Image;
import com.turkey.LD29.Player.Player;
import com.turkey.LD29.screen.util.Button;
import com.turkey.LD29.screen.util.Interactable;
import com.turkey.LD29.util.Location;
import com.turkey.LD29.util.Maze;
import com.turkey.LD29.util.MazeSolver;

public class GameScreen extends Screen
{
	private Maze maze;
	private MazeSolver solver;
	private Player player;

	private int[][] map;

	private int clicked = 0;
	private int clicks = 30;

	private boolean isLoaded = false;
	private int stage = 1;
	private int count = 0;
	private Image gen1 = new Image(Image.getImage("/Images/Generating1.png"));
	private Image gen2 = new Image(Image.getImage("/Images/Generating2.png"));
	private Image gen3 = new Image(Image.getImage("/Images/Generating3.png"));

	private Image unslected = new Image(Image.getImage("/Images/SquareUnSelected.png"));
	private Image slected = new Image(Image.getImage("/Images/SquareSelected.png"));

	public GameScreen(String n)
	{
		super(n);
		maze = new Maze();
		solver = new MazeSolver(maze);
		player = new Player(maze);
	}

	public void load()
	{
		isLoaded = false;
		player.setLocation(50, 50);
		maze.generate(false, 20, 15, 40, 40);
		map = new int[maze.getXSize()][maze.getYSize()];
		removeAllAddons();
		for(int y = 0; y < maze.getYSize(); y++)
		{
			for(int x = 0; x < maze.getXSize(); x++)
			{

				if(x == 0 || x + 1 == maze.getWidth()/maze.getxWallScale() ||  y == 0 ||y + 1 == maze.getHeight()/maze.getyWallScale())
				{
					map[x][y] = 1;
				}
				else if(x == 1 && y==1 || x == maze.getWidth()/maze.getxWallScale() -2 && y==maze.getHeight()/maze.getyWallScale() -2)
				{
					map[x][y] = 1;
				}
				else
				{
					map[x][y] = 0;
					Button temp = new Button(x*40, y*40, 40, 40, slected, unslected,"Button " + x + ", " + y);
					addInteractable(temp);
				}
			}
		}
		if(!solver.solve(new Location(1, 1), new Location(maze.getXSize() -2, maze.getYSize() - 2)))
		load();
		clicks = ((SettingsScreen)ScreenManager.getInstance().getScreen("Settings")).getNumClicks();
		isLoaded = true;
	}

	public void render()
	{
		if(isLoaded)
		{
			int[] mPixels =  maze.getPixels();
			int[] pPixels =  player.getPixles();
			for(int y = 0; y < height; y++)
			{
				for(int x = 0; x < width; x++)
				{
					if(map[x/maze.getxWallScale()][y/maze.getyWallScale()] == 1)
						pixels[width * y + x] = mPixels[maze.getWidth() * y + x];
				}
			}
			super.render();
			for(int y = 0; y < player.getImage().getImage().getHeight(); y++)
			{
				for(int x = 0; x < player.getImage().getImage().getWidth(); x++)
				{
					if((int)player.getPixles()[player.getImage().getImage().getWidth() * y + x] != -65328)
					{
						int color = pPixels[player.getImage().getImage().getWidth() * y + x];
						pixels[width * (y + player.getAbsoluteY()) + (x + player.getAbsoluteX())] = color;
					}
				}
			}
		}
		else
			{
			int[] pix = getStage();
			for(int y = 0; y < height; y++)
			{
				for(int x = 0; x < width; x++)
				{
						pixels[width * y + x] = pix[maze.getWidth() * y + x];

				}
			}
			count++;
			if(count > 25)
			{
				stage++;
				count=0;
			}
			if(stage > 3)
				stage = 1;
			
		}
	}

	public void update()
	{
		player.update();
	}

	public void GameOver(boolean win)
	{
		clicked = 0;
		((GameOverScreen)ScreenManager.getInstance().getScreen("GameOver")).setWin(win);
		ScreenManager.getInstance().setCurrentScreen("GameOver");
	}

	public void onClicked(Interactable i)
	{
		if(clicked < clicks)
		{
			i.hide();
			Interactable i2 = getInteractable(new Location(i.getX(), i.getY() - maze.getyWallScale()));			
			if(i2!=null)
			{
				i2.hide();
				map[i2.getX()/i2.getWidth()][i2.getY()/i2.getHeight()] = 1;
			}
			i2 = getInteractable(new Location(i.getX() + maze.getxWallScale(), i.getY()));
			if(i2!=null)
			{
				i2.hide();
				map[i2.getX()/i2.getWidth()][i2.getY()/i2.getHeight()] = 1;
			}
			i2 = getInteractable(new Location(i.getX(), i.getY() + maze.getyWallScale()));
			if(i2!=null)
			{
				i2.hide();
				map[i2.getX()/i2.getWidth()][i2.getY()/i2.getHeight()] = 1;
			}
			i2 = getInteractable(new Location(i.getX() - maze.getxWallScale(), i.getY()));
			if(i2!=null)
			{
				i2.hide();
				map[i2.getX()/i2.getWidth()][i2.getY()/i2.getHeight()] = 1;
			}
			map[i.getX()/i.getWidth()][i.getY()/i.getHeight()] = 1;
			clicked++;
		}
	}
	
	public int[] getStage()
	{
		if(stage == 1)
			return gen1.getPixels();
		else if(stage == 2)
			return gen2.getPixels();
		else
			return gen3.getPixels();
	}
}
