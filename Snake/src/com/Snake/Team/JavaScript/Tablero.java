package com.Snake.Team.JavaScript;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private final int IDFRUTA = 3;
	private final int IDPU = 4;
	static final int pared = -1;
	public List<Snake> serpientes;
	public List<Fruta> frutas;
	public List<PowerUp> powerUps;
	int [][]tablero;
	int filas, columnas;
	
	public Tablero(int largo, int ancho, int cantidadDeFrutas, int cantidadDeViboras) {
		this.filas = ancho;
		this.columnas = largo;
		serpientes = new ArrayList<>();
		frutas = new ArrayList<>();
		powerUps = new ArrayList<>();
		inicializarTablero();
		
		for(int i = 0; i < cantidadDeFrutas; i ++) {
			colocarFrutas();
		}
		//solo se agregan 2 para probar
		for(int i = 0; i < 2; i ++) {
			colocarPowerUp();
		}
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
	
	private Posicion generarPosicion() {
		int col = (int)(Math.random() * (this.filas - 1)) + 1;
		int fil = (int)(Math.random() * (this.columnas - 1)) + 1;
		
		while(tablero[fil][col] != 0) {
			col = (int)(Math.random() * (this.filas - 1)) + 1;
			fil = (int)(Math.random() * (this.columnas - 1)) + 1;
		}
		
		Posicion pos = new Posicion(fil, col);
		
		return pos;
	}
	
	private int buscarFruta(int f, int c) {
		Posicion pos = new Posicion(f, c);
		int i = 0;
		
		for(Fruta fruta : frutas) {
			if(fruta.devolverPosicion() == pos)
				break;
			
			i ++;
		}
		
		return i;
	}
	
	public void colocarVibora(Posicion pos, String nom) {
		serpientes.add(new Snake(pos, nom));
	}
	
	public void colocarFrutas() {
		Posicion pos = generarPosicion();
		frutas.add(new Fruta(pos));
		tablero[(int)pos.getX()][(int)pos.getY()] = IDFRUTA;
	}
	
	public void colocarPowerUp() {
		Posicion pos = generarPosicion();
		powerUps.add(new PowerUp(pos));
		tablero[(int)pos.getX()][(int)pos.getY()] = IDPU;
	}
	
	public void colision() {
		int fila, columna;
		
		for(Snake s : serpientes) {
			fila = (int) s.getPosicion().getX();
			columna = (int) s.getPosicion().getY();
			
			if(tablero[fila][columna] == pared) {
				System.out.println(s.getNombreJugador() + " ha chocado contra la pared");			
			}
			
			if(tablero[fila][columna] == IDFRUTA) {
				tablero[fila][columna] = 0;
				int i = buscarFruta(fila, columna);
				s.comerConsumible(frutas.get(i));
				frutas.remove(i);
				
			}
		}
	}
}
