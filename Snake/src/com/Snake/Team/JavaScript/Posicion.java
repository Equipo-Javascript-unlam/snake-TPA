package com.Snake.Team.JavaScript;

import java.awt.geom.Point2D;

public class Posicion extends Point2D {
	private double x;
	private double y;

	public Posicion(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Posicion(Posicion pos) {
		this.x = pos.getX();
		this.y = pos.getY();
	}

	@Override
	public double getX() {
		
		return this.x;
	}

	@Override
	public double getY() {

		return this.y;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;

	}

	public void copy(Posicion p) {
		this.x = p.x;
		this.y = p.y;
	}

	public boolean equals(Posicion p) {
		return this.x == p.x && this.y == p.y;
	}

	public Posicion sumar(Posicion pos) {
		return new Posicion(this.x + pos.getX(), this.y + pos.getY());
	}

	public Posicion restar(Posicion pos) {
		return new Posicion(this.x - pos.getX(), this.y - pos.getY());
	}

	public Posicion getPos() {
		return new Posicion(this.x, this.y);
	}
}
