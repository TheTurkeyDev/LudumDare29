package com.turkey.LD29.screen;

import java.util.ArrayList;

import com.turkey.LD29.Main;
import com.turkey.LD29.Images.StandAloneImage;
import com.turkey.LD29.screen.util.Interactable;
import com.turkey.LD29.subscreen.SubScreen;
import com.turkey.LD29.util.Location;


public class Screen
{

	private String name;
	public int height, width;

	public int[] pixels;

	private ArrayList<Interactable> addons = new ArrayList<Interactable>();
	private ArrayList<StandAloneImage> images = new ArrayList<StandAloneImage>();
	private ArrayList<SubScreen> subScreens = new ArrayList<SubScreen>();

	//private int offsetX = 0, offsetY = 0;


	public Screen(String n)
	{
		name = n;
		height = Main.height;
		width = Main.width;
		pixels = new int[width * height];
	}

	public void update()
	{

	}
	public void render()
	{
		renderAddonsTile();
		renderImages();
		renderSubScreens();
	}

	public void clear()
	{
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				pixels[width * y + x] = 0xfffff;
	}

	public void onClicked(Interactable clicked)
	{

	}

	public void renderAddonsTile()
	{
		for(Interactable i: addons)
		{
			if(i.isVisible())
			{
				int [] image = i.getCurrentPixelArray();
				for(int xx=0;xx<i.getWidth();xx++)
				{
					for(int yy=0;yy<i.getHeight();yy++)
					{
						if(i.getY()+yy>=0 && i.getY()+yy<height && i.getX()+xx>=0 && i.getX()+xx<width && (int)image[i.getWidth()* yy + xx] != -65328)
						{
							pixels[(i.getX()+xx)+(i.getY()+yy)*width]= image[xx + yy* i.getWidth()];
						}
					}
				}
			}
		}
	}

	public void renderImages()
	{
		for(StandAloneImage i: images)
		{
			if(i.isVisible())
			{
				int [] image = i.getImage().getPixels();
				for(int xx=0;xx<i.getImage().getWidth();xx++)
				{
					for(int yy=0;yy<i.getImage().getHeight();yy++)
					{
						if(i.getY()+yy>=0 && i.getY()+yy<height && i.getX()+xx>=0 && i.getX()+xx<width)
						{
							if(image[xx + yy* i.getImage().getWidth()]!=16777215)
								pixels[(i.getX()+xx)+(i.getY()+yy)*width]= image[xx + yy* i.getImage().getWidth()];
						}
					}
				}
			}
		}
	}

	public void renderSubScreens()
	{

		for(SubScreen ss: subScreens)
		{
			ss.render();
			int [] image = ss.pixels;
			for(int xx=0;xx<ss.getWidth();xx++)
			{
				for(int yy=0;yy<ss.getHeight();yy++)
				{
					if(ss.getY()+yy>=0 && ss.getY()+yy<height && ss.getX()+xx>=0 && ss.getX()+xx<width)
					{
						if(image[xx + yy* ss.getWidth()]!=16777215)
							pixels[(ss.getX()+xx)+(ss.getY()+yy)*width]= image[xx + yy* ss.getWidth()];
					}
				}
			}
		}
	}

	public String getName()
	{
		return name;
	}

	public void addInteractable(Interactable i)
	{
		addons.add(i);
	}
	public ArrayList<Interactable> getInteractables()
	{
		return addons;
	}

	public void addStandAloneImage(StandAloneImage si)
	{
		images.add(si);
	}
	public ArrayList<StandAloneImage> getStandAloneImage()
	{
		return images;
	}

	public void addSubScreen(SubScreen ss)
	{
		subScreens.add(ss);
	}
	public ArrayList<SubScreen> getSubScreens()
	{
		return subScreens;
	}
	
	public Interactable getInteractable(Location loc)
	{
		for(Interactable i : addons)
		{
			if(i.contains(loc.getX(), loc.getY()))
				return i;
		}
		return null;
	}
	
	public void removeAllAddons()
	{
		addons.clear();
	}
}
