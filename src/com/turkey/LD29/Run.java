package com.turkey.LD29;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Run extends JFrame
{
	private static final long serialVersionUID = 1L;

	public static void main(String[] args)
	{
		Run run = new Run();
		run.start();
	}

	public void start()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel game = new Main("GameAPI", 800, 600, 1);
		game.setLayout(null);
		setTitle("Maze Sweeper");
		add(game);
		ImageIcon icon = new ImageIcon ( Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Icon.png")));
		setIconImage(icon.getImage());
		setVisible(true);
		((Main) game).start();
	}

}