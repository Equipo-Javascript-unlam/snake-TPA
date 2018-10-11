package com.Snake.Team.JavaScript;

import java.awt.geom.Point2D;

public class Posicion extends Point2D{
	private double x;
	private double y;
	
	
	public Posicion(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public double getY() {
		
		return this.y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
		
	}
}
