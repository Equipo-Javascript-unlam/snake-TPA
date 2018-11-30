package com.Snake.Team.JavaScript;

public enum Direccion {
	izquierda(-1, 0), derecha(1, 0), abajo(0, -1), arriba(0, 1);
	final int fila, columna;

	Direccion(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	Posicion sentido() {
		return new Posicion(this.fila, this.columna);
	}
	
	public static Direccion getDirRand() {
		return numToDir((int)Math.random() * 4);
	}
	
	public static Direccion numToDir(int i)
	{
		switch(i) {
		case 0:
			return arriba;
		case 1:
			return abajo;
		case 2:
			return izquierda;
		default:
			return derecha;
		}
	}
	
	public static boolean esOpuesto(Direccion d1, Direccion d2)
	{
		switch (d1)
		{
		case izquierda:
			return d2==derecha;
		case derecha:
			return d2==izquierda;
		case arriba:
			return d2==abajo;
		default:
			return d2==arriba;
		
		}
	}
}
