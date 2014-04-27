package com.turkey.LD29;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JPanel;

import com.turkey.LD29.listeners.ScreenKeyListener;
import com.turkey.LD29.listeners.ScreenMouseListener;
import com.turkey.LD29.listeners.ScreenMouseMotionListener;
import com.turkey.LD29.screen.GameOverScreen;
import com.turkey.LD29.screen.GameScreen;
import com.turkey.LD29.screen.MainScreen;
import com.turkey.LD29.screen.ScreenManager;
import com.turkey.LD29.screen.SettingsScreen;


public class Main extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	private static Main api;

	public static int width, height, scale;

	private Thread thread;

	private String frameName;

	private boolean running = false;

	private BufferedImage image;
	private int[] pixels;

	private ScreenManager sm;

	private int gFrames = 0;
	private int gUpdates = 0;

	public Main(String name, int w, int h, int s)
	{
		width = w;
		height = h;
		scale = s;
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		frameName = name;
		super.setName(frameName);
		super.setFocusable(true);
		super.requestFocusInWindow();
		super.addKeyListener(new ScreenKeyListener());
		super.addMouseListener(new ScreenMouseListener());
		super.addMouseMotionListener(new ScreenMouseMotionListener());
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		sm = new ScreenManager();
		api = this;

		setUp();
	}

	private void setUp()
	{
		sm.addScreen(new MainScreen("Main"));
		sm.addScreen(new GameScreen("Game"));
		sm.addScreen(new GameOverScreen("GameOver"));
		sm.addScreen(new SettingsScreen("Settings"));
		
		sm.setCurrentScreen("Main");
	}

	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join();
		} catch (InterruptedException e){e.printStackTrace();}
	}

	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				update();
				updates++;
				delta--;
			}
			repaint();
			frames++;

			if(System.currentTimeMillis() - timer > 1000)
			{
				gFrames = frames;
				gUpdates = updates;
				timer += 1000;
				updates = 0;
				frames = 0;
			}
			try
			{
				Thread.sleep(15);
			} catch (InterruptedException e){e.printStackTrace();}
		}
	}

	public void update()
	{
		sm.getCurrentScreen().update();
	}

	public void paint(Graphics g)
	{
		try
		{
			sm.getCurrentScreen().clear();
			sm.getCurrentScreen().render();
		}catch(NullPointerException e){if(sm.getCurrentScreen()== null)System.out.println("Screen is null!!"); else e.printStackTrace();}

		int[] pix = sm.getCurrentScreen().pixels;
		for(int y = 0; y < sm.getCurrentScreen().height; y++)
		{
			for(int x = 0; x < sm.getCurrentScreen().width; x++)
			{
				pixels[width * y + x] = pix[width * y + x];
			}
		}


		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if(ScreenKeyListener.isKeyPressed(KeyEvent.VK_1))
		{
			g.setColor(Color.WHITE);
			g.drawString(gUpdates + " ups, " + gFrames + " Frames", 0, 10);
		}
		g.dispose();
	}

	public ScreenManager getScreenManager()
	{
		return sm;
	}

	public static Main getAPI()
	{
		return api;
	}
}