package com.Snake.Team.JavaScript;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	static final int pared = -1;
	public List<Snake> serpientes;
	public List<Fruta> frutas;
	int [][]tablero;
	int filas, columnas;
	
	public Tablero(int largo, int ancho, int cantidadDeFrutas, int cantidadDeViboras) {
		this.filas = ancho;
		this.columnas = largo;
		serpientes = new ArrayList<>();
		frutas = new ArrayList<>();
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
	
	public void colocarVibora(Posicion pos, String nom) {
		serpientes.add(new Snake(pos, nom));
	}
	
	public void colision() {
		for(Snake s : serpientes) {
			if(s.getPosicion().getX() == pared || s.getPosicion().getY() == pared) {
				System.out.println("La serpiente " + s.getNombreJugador() + " ha chocado contra la pared");
			}
			
			
		}
	}
}
