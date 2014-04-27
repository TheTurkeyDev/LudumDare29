package com.turkey.LD29.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.screen.util.Interactable;
import com.turkey.LD29.util.Location;

public class ScreenMouseListener extends MouseAdapter
{
	int x = 0, y = 0;
	public void mouseClicked(MouseEvent e)
	{
		x = e.getX() + 5;
		y = e.getY() + 20;
		for(Interactable i: ScreenManager.getInstance().getCurrentScreen().getInteractables())
		{
			if(i.contains(x, y))
			{
				if(i.isClick())
				{
					i.setSelected(!i.isSelected());
				}
				ScreenManager.getInstance().getCurrentScreen().onClicked(i);
			}
		}
		/*for(Interactable i: ScreenManager.getInstance().getCurrentScreen().getInteractables())
		{
			if(i.contains(x, y))
			{
				if(i.isClick())
				{
					i.setSelected(!i.isSelected());
				}
				ScreenManager.getInstance().getCurrentScreen().onClicked(i);
			}
		}*/
	}
	
	public Location getLastClick()
	{
		return new Location(x ,y);
	}
}
