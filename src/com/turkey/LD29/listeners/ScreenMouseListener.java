package com.turkey.LD29.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.screen.util.Interactable;
import com.turkey.LD29.util.Location;

public class ScreenMouseListener implements MouseListener
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
	}
	
	public Location getLastClick()
	{
		return new Location(x ,y);
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{	
	}
}
