package com.turkey.LD29.util;

import java.util.ArrayList;
import java.util.Random;

import com.turkey.LD29.screen.GameScreen;
import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.util.Location.Direction;

public class MazeSolver
{
	private Maze maze;
	private int x = 1,y = 1;

	private ArrayList<Location> nogo = new ArrayList<Location>();
	private ArrayList<Location> visited = new ArrayList<Location>();

	private Direction last = null;

	public MazeSolver(Maze m)
	{
		maze = m;
	}

	public boolean solve(Location start, Location end)
	{
		nogo.clear();
		visited.clear();
		x = start.getX();
		y = start.getY();
		int endX = end.getX();
		int endY = end.getY();
		visited.add(new Location(x, y));
		long startTime = System.currentTimeMillis();
		while(startTime + 10000 > System.currentTimeMillis())
		{
			move(choseDir());
			if(x == endX && y == endY)
				return true;
		}
		return false;
	}

	public Direction choseDir()
	{
		ArrayList<Direction> primary = new ArrayList<Direction>();
		ArrayList<Direction> secondary = new ArrayList<Direction>();
		Location above = new Location(x, y - 1);
		Location right = new Location(x + 1, y);
		Location down = new Location(x, y + 1);
		Location left = new Location(x - 1, y);
		if(!maze.isWall(above.getX(), above.getY()))
		{
			if(!isNoGo(above))
			{
				if(last == Direction.South || hasVisited(above))
					secondary.add(Direction.North);
				else
					primary.add(Direction.North);
			}
		}
		if(!maze.isWall(right.getX(), right.getY()))
		{
			if(!isNoGo(right))
			{
				if(last == Direction.West || hasVisited(right))
					secondary.add(Direction.East);
				else
					primary.add(Direction.East);
			}
		}
		if(!maze.isWall(down.getX(), down.getY()))
		{
			if(!isNoGo(down))
			{
				if(last == Direction.North || hasVisited(down))
					secondary.add(Direction.South);
				else
					primary.add(Direction.South);
			}
		}
		if(!maze.isWall(left.getX(), left.getY()))
		{
			if(!isNoGo(left))
			{
				if(last == Direction.East || hasVisited(left))
					secondary.add(Direction.West);
				else
					primary.add(Direction.West);
			}
		}

		if(primary.size() == 0)
		{
			primary.addAll(secondary);
			secondary.clear();
		}
		if(primary.size() == 1 && secondary.size() == 0)
		{
			nogo.add(new Location(x,y));
			return primary.get(0);
		}
		if(primary.size() == 2 && primary.contains(last))
		{
			primary.remove(last);
		}

		try{
		Random r = new Random();
		return primary.get(r.nextInt(primary.size()));
		}catch(IllegalArgumentException e){
			((GameScreen)ScreenManager.getInstance().getScreen("Game")).load();
		}
		return null;
	}

	public void move(Direction dir)
	{
		if(dir == null)
			return;
		if(dir.equals(Direction.North))
		{
			y--;
			last = Direction.North;
			visited.add(new Location(x , y));
		}
		else if(dir.equals(Direction.East))
		{
			x++;
			last = Direction.East;
			visited.add(new Location(x , y));
		}
		else if(dir.equals(Direction.South))
		{
			y++;
			last = Direction.South;
			visited.add(new Location(x , y));
		}
		else if(dir.equals(Direction.West))
		{
			x--;
			last = Direction.West;
			visited.add(new Location(x , y));
		}
	}

	public boolean hasVisited(int xloc, int yloc)
	{
		for(Location loc: visited)
			if(loc.equals(new Location(xloc, yloc)))
				return true;
		return false;
	}
	public boolean hasVisited(Location loc)
	{
		for(Location loc2: visited)
			if(loc2.equals(new Location(loc.getX(), loc.getY())))
				return true;
		return false;
	}
	public boolean isNoGo(Location loc)
	{
		for(Location loc2: nogo)
			if(loc2.equals(new Location(loc.getX(), loc.getY())))
				return true;
		return false;
	}
}
