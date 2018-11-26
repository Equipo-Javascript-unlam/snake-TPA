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
		int i = (int) (Math.random() * 4) + 1;
				
		switch(i) {
		case 1:
			return arriba;
		case 2:
			return abajo;
		case 3:
			return izquierda;
		default:
			return derecha;
		}
	}
}
