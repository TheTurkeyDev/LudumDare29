package com.turkey.LD29.subscreen;

import java.util.ArrayList;

import com.turkey.LD29.screen.Screen;
import com.turkey.LD29.screen.util.Interactable;


public class SubScreen
{

	protected int x, y, width, height;
	protected String name;
	protected Screen parent;
	protected boolean isVisible = false;
	
	private ArrayList<Interactable> addons = new ArrayList<Interactable>();
	
	public int[] pixels;
	
	public SubScreen(int xloc, int yloc, int w, int h, String n, Screen p)
	{
		x = xloc;
		y = yloc;
		width = w;
		height = h;
		name = n;
		parent = p;
		pixels = new int[w*h];
	}

	public void render()
	{
		for(Interactable i: addons)
		{
			int [] image = i.getCurrentPixelArray();
			for(int xx=0;xx<i.getWidth();xx++)
			{
				for(int yy=0;yy<i.getHeight();yy++)
				{
					if(i.getX()+yy>=0 && i.getY()+yy<height && i.getX()+xx>=0 && i.getX()+xx<width)
					{
						if(image[xx + yy* i.getWidth()]!=16777215)
						pixels[(i.getX()+xx)+(i.getY()+yy)*width]= image[xx + yy* i.getWidth()];
					}
				}
			}
		}
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, width, height);
	}

	public void update()
	{
	}

	public String getName()
	{
		return name;
	}


	public void onClick(int x, int y)
	{

	}

	public void onMouseMove(int x, int y)
	{

	}
	
	public void setVisible(boolean v)
	{
		isVisible = v;
	}
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
}