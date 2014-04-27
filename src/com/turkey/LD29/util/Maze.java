package com.turkey.LD29.util;

import java.util.ArrayList;
import java.util.Random;

public class Maze
{
	private int xSize; private int ySize;
	private int width; private int height;
	private int xWallScale, yWallScale;
	private int[][] map;
	private int[] pixels;
	private ArrayList<Location> walls = new ArrayList<Location>();
	private Random r = new Random();

	private int currentX = 1;
	private int currentY = 1;

	private int nonWall = 0;
	private int wall = 1;

	private boolean generated = false;

	public int[] getPixels()
	{
		return pixels;
	}

	public void generate(boolean multiple, int x1, int y1, int x2, int y2)
	{
		xSize = x1;
		ySize = y1;
		xWallScale = x2;
		yWallScale = y2;
		map = new int[xSize][ySize];
		for (int y = 0; y < ySize; y++)
		{
			for (int x = 0; x < xSize; x++)
			{
				map[x][y] = wall;
			}
		}

		map[1][1] = nonWall;
		currentX = 1;
		currentY = 1;
		Location current = new Location(currentX, currentY);
		Location north = current.add(0, -1);
		Location east = current.add(1, 0);
		Location south = current.add(0, 1);
		Location west = current.add(-1, 0);

		if ((north.getY() > 0) && (map[north.getX()][north.getY()] == wall))
		{
			if(multiple)
				walls.add(north);
			else if((map[north.getX()][(north.getY() - 1)] == wall))
				walls.add(north);
		}
		if ((east.getX()< xSize) && (map[east.getX()][east.getY()] == wall))
		{
			if(multiple)
				walls.add(east);
			else if((map[(east.getX() + 1)][east.getY()] == wall))
				walls.add(east);
		}
		if ((south.getY()< ySize) && (map[south.getX()][south.getY()] == wall))
		{
			if(multiple)
				walls.add(south);
			else if((map[south.getX()][(south.getY() + 1)] == wall))
				walls.add(south);
		}
		if ((west.getX() > 0) && (map[west.getX()][west.getY()] == wall))
		{
			if(multiple)
				walls.add(west);
			else if((map[(west.getX() - 1)][west.getY()] == wall))
				walls.add(west);
		}

		while (walls.size() > 0)
		{
			int randomLoc = r.nextInt(walls.size());
			currentX = ((Location)walls.get(randomLoc)).getX();
			currentY = ((Location)walls.get(randomLoc)).getY();
			current = new Location(currentX, currentY);
			north = current.add(0, -1);
			east = current.add(1, 0);
			south = current.add(0, 1);
			west = current.add(-1, 0);

			if (!checkwalls(current))
			{
				map[currentX][currentY] = nonWall;
				walls.remove(randomLoc);

				if ((north.getY() - 1 > 0) && (map[north.getX()][north.getY()] == wall))
				{
					if(multiple)
						walls.add(north);
					else if((map[north.getX()][(north.getY() - 1)] == wall))
						walls.add(north);
				}
				if ((east.getX() + 1 < xSize) && (map[east.getX()][east.getY()] == wall))
				{
					if(multiple)
						walls.add(east);
					else if((map[(east.getX() + 1)][east.getY()] == wall))
						walls.add(east);
				}
				if ((south.getY() + 1 < ySize) && (map[south.getX()][south.getY()] == wall))
				{
					if(multiple)
						walls.add(south);
					else if((map[south.getX()][(south.getY() + 1)] == wall))
						walls.add(south);
				}
				if ((west.getX() - 1 > 0) && (map[west.getX()][west.getY()] == wall))
				{
					if(multiple)
						walls.add(west);
					else if((map[(west.getX() - 1)][west.getY()] == wall))
						walls.add(west);
				}
			}
			else
			{
				walls.remove(randomLoc);
			}
		}
		map[18][13] = nonWall;
		boolean Inaccessible = true;
		int i = 1;
		while (Inaccessible)
		{
			map[(18 - i)][13] = nonWall;
			map[18][(13 - i)] = nonWall;
			i++;
			if ((map[(18 - i)][13] == nonWall) || (map[18][(13 - i)] == nonWall) || (map[(18 - i)][12] == nonWall) || (map[17][(13 - i)] == nonWall))
			{
				Inaccessible = false;
			}
		}
		if(multiple)
		{
			for (int y = 1; y < ySize - 1; y++)
			{
				for (int x = 1; x < xSize - 1; x++)
				{
					if(isWall(x, y) && numWallsAround(new Location(x,y)) == 2)
					{
						map[x][y] = nonWall;
					}
				}
			}
		}
		height = ySize*yWallScale;
		width = xSize*xWallScale;
		pixels = new int[height * width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if(map[x/xWallScale][y/yWallScale] == wall)
				{
					pixels[width * y + x] = 0x000000;
				}
				else
				{
					pixels[width * y + x] = 0xC0C0C0;
				}

			}
		}
		generated = true;
	}

	private boolean checkwalls(Location loc)
	{
		Location north = loc.add(0, -1);
		Location east = loc.add(1, 0);
		Location south = loc.add(0, 1);
		Location west = loc.add(-1, 0);

		int yes = 0;
		if (north.getY() >= 0 && map[north.getX()][north.getY()] == nonWall)
			yes++;
		if (east.getX() < xSize && map[east.getX()][east.getY()] == nonWall)
			yes++;
		if (south.getY() < ySize && map[south.getX()][south.getY()] == nonWall)
			yes++;
		if (west.getX() >= 0 && map[west.getX()][west.getY()] == nonWall)
			yes++;
		return yes > 1;
	}

	private int numWallsAround(Location loc)
	{
		Location north = loc.add(0, -1);
		Location east = loc.add(1, 0);
		Location south = loc.add(0, 1);
		Location west = loc.add(-1, 0);

		int yes = 0;
		if (north.getY() >= 0 && map[north.getX()][north.getY()] == nonWall)
			yes++;
		if (east.getX() < xSize && map[east.getX()][east.getY()] == nonWall)
			yes++;
		if (south.getY() < ySize && map[south.getX()][south.getY()] == nonWall)
			yes++;
		if (west.getX() >= 0 && map[west.getX()][west.getY()] == nonWall)
			yes++;
		return yes;
	}

	public boolean isGenrated()
	{
		return generated;
	}

	public boolean isWall(int x, int y, int size)
	{
		int x1 = x / xWallScale;
		int x2 = (x + size) / xWallScale;
		int y1 = y / yWallScale;
		int y2 = (y + size) / yWallScale;

		if (map[x1][y1] == wall)
		{
			return true;
		}
		if (map[x1][y2] == wall)
		{
			return true;
		}
		if (map[x2][y1] == wall)
		{
			return true;
		}
		if (map[x2][y2] == wall)
		{
			return true;
		}
		return false;
	}

	public boolean isWall(int x, int y)
	{
		if (map[x][y] == wall)
		{
			return true;
		}
		return false;
	}

	public Location getFreeLoc()
	{
		int x = r.nextInt(xSize);
		int y = r.nextInt(ySize);
		boolean valid = false;
		while (!valid)
		{
			if (map[x][y] == nonWall)
			{
				valid = true;
			}
			else
			{
				x = r.nextInt(xSize);
				y = r.nextInt(ySize);
			}
		}
		return new Location(x, y);
	}

	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	
	public int getxWallScale()
	{
		return xWallScale;
	}
	public int getyWallScale()
	{
		return yWallScale;
	}
	
	public int getXSize()
	{
		return xSize;
	}
	public int getYSize()
	{
		return ySize;
	}
	
	public int[][] getMazeMap()
	{
		return map;
	}
}