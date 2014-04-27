package com.turkey.LD29.util;

public class Vector
{
	double x;
	double y;
	
	public Vector()
	{
		x = 0;
		y = 0;
	}
	public Vector(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public Vector(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
}
