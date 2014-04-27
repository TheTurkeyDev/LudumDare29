package com.turkey.LD29.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.screen.util.Interactable;



public class ScreenMouseMotionListener extends MouseMotionAdapter
{
	private static int x = 0;
	private static int y = 0;
	public void mouseMoved(MouseEvent e) 
	{
		x = e.getX() + 5;
		y = e.getY() + 20;
		for(Interactable i: ScreenManager.getInstance().getCurrentScreen().getInteractables())
		{
			if(i.contains(x, y))
			{
				if(i.isHover())
					i.setSelected(true);
			}
			else
			{
				if(i.isHover())
					i.setSelected(false);
			}
		}
	}
	
	public static int getX()
	{
		return x;
	}
	public static int getY()
	{
		return y;
	}
}
