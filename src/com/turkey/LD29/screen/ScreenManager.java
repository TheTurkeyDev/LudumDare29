package com.turkey.LD29.screen;

import java.util.ArrayList;


public class ScreenManager
{
	private Screen currentScreen;
	public static ScreenManager sm;
	
	public ArrayList<Screen> screens = new ArrayList<Screen>();
	
	public ScreenManager()
	{
		sm = this;
	}
	
	public Screen getCurrentScreen()
	{
		return currentScreen;
	}
	
	public void setCurrentScreen(Screen newScreen)
	{
		currentScreen = newScreen;
	}
	public boolean setCurrentScreen(String newScreen)
	{
		for(Screen s: screens)
		{
			if(s.getName().equalsIgnoreCase(newScreen))
			{
				currentScreen = s;
				return true;
			}
		}
		return false;
	}
	
	public static ScreenManager getInstance()
	{
		return sm;
	}
	
	public void addScreen(Screen screen)
	{
		screens.add(screen);
	}
	
	public Screen getScreen(String name)
	{
		for(Screen s: screens)
		{
			if(s.getName().equalsIgnoreCase(name))
			{
				return s;
			}
		}
		return null;
	}
}
