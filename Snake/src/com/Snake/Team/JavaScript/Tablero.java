package com.Snake.Team.JavaScript;

import java.util.List;

public class Tablero {
	static final int pared = -1;
	List<Snake> serpientes;
	List<Fruta> frutas;
	int [][]tablero;
	int filas, columnas;
	
	public Tablero(int largo, int ancho, int cantidadDeFrutas, int cantidadDeViboras) {
		this.filas = ancho;
		this.columnas = largo;
		inicializarTablero();
	}
	
	private void inicializarTablero() {
		tablero = new int[filas][columnas];
		
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (c == 0 || c == columnas - 1 || f == 0 || f == filas - 1)
                    tablero[f][c] = pared;
            }
        }
	}
	
}
