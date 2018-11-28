package com.Snake.Team.JavaScript;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.Snake.Team.JavaScript.Snake.BodySnake;

public class Tablero {
	private final int IDFRUTA = 3;
	private final int IDPU = 4;
	static final int PARED = -1;
	public List<Snake> serpientes;
	public List<Fruta> frutas;
	public List<PowerUp> powerUps;
	int[][] tablero;
	int filas, columnas;

	public Tablero(int largo, int ancho, int cantidadDeFrutas) {
		this.filas = ancho;
		this.columnas = largo;
		serpientes = new ArrayList<>();
		frutas = new ArrayList<>();
		powerUps = new ArrayList<>();
		inicializarTablero();

		for (int i = 0; i < 30; i++) {
			colocarFrutas();
		}
		// solo se agregan 2 para probar
		/*
		 * for (int i = 0; i < 2; i++) { colocarPowerUp(); }
		 */
	}

	private void inicializarTablero() {
		tablero = new int[filas][columnas];

		for (int f = 0; f < filas; f++) {
			for (int c = 0; c < columnas; c++) {
				if (c == 0 || c == columnas - 1 || f == 0 || f == filas - 1)
					tablero[f][c] = PARED;
			}
		}
	}

	private boolean hayObjetoEnMapa(Posicion pos) {
		int x = (int) pos.getX();
		int y = (int) pos.getY();
		boolean hayObjeto = hayCuerpo(x, y);

		if (!hayObjeto)
			hayObjeto = (tablero[x][y] == IDFRUTA || tablero[x][y] == IDPU) ? true : false;

		return hayObjeto;
	}

	private boolean hayCuerpo(int f, int c) {
		Posicion pos = new Posicion(f, c);

		for (Snake s : serpientes) {
			for (int i = 0; i < s.getCuerpo().size(); i++)
				if (s.getCuerpo().get(i).getPosicion().equals(pos))
					return true;
		}

		return false;
	}

	private Posicion generarPosicion() {
		int col = (int) (Math.random() * (this.filas - 1)) + 1;
		int fil = (int) (Math.random() * (this.columnas - 1)) + 1;

		while (!hayCuerpo(fil, col) && tablero[fil][col] != 0) {
			col = (int) (Math.random() * (this.filas - 1)) + 1;
			fil = (int) (Math.random() * (this.columnas - 1)) + 1;
		}

		Posicion pos = new Posicion(fil, col);

		return pos;
	}

	private int buscarFruta(int f, int c) {
		Posicion pos = new Posicion(f, c);
		int i = 0;

		for (Fruta fruta : frutas) {
			if (fruta.devolverPosicion().equals(pos))
				break;
			i++;
		}
		return i;
	}

	private int buscarPowerUp(int f, int c) {
		Posicion pos = new Posicion(f, c);
		int i = 0;

		for (PowerUp powUp : powerUps) {
			if (powUp.devolverPosicion().equals(pos))
				break;
			i++;
		}
		return i;
	}

	public void colocarVibora(Posicion pos, String nom) {
		serpientes.add(new Snake(pos, nom));
	}
	
	public void colocarVibora(Snake s) {   //METODO DE PRUEBA
		serpientes.add(s);
	}

	public void colocarVibora(String nom) {
		serpientes.add(new Snake(generarPosicion(), nom));
	}

	public void colocarFrutas() {
		Posicion pos = generarPosicion();

		while (hayObjetoEnMapa(pos))
			pos = generarPosicion();

		frutas.add(new Fruta(pos));
		tablero[(int) pos.getX()][(int) pos.getY()] = IDFRUTA;
	}

	public void colocarPowerUp() {
		Posicion pos = generarPosicion();
		powerUps.add(new PowerUp(pos));
		tablero[(int) pos.getX()][(int) pos.getY()] = IDPU;
	}

	public void colision() {
		int fila, columna;
		Iterator<Snake> it = serpientes.iterator();
		
		//este for es para probar, despues se vera si se borra
		for(Snake s : serpientes) {
			fila = (int) s.getPosicion().getX();
			columna = (int) s.getPosicion().getY();

			if (tablero[fila][columna] == PARED || s.comeSuCuerpo()) {
				s.morir();
			}

			if (tablero[fila][columna] == IDFRUTA) {
				tablero[fila][columna] = 0;
				int i = buscarFruta(fila, columna);
				s.comerConsumible(frutas.get(i));
				frutas.remove(i);
				colocarFrutas();
			}

			if (tablero[fila][columna] == IDPU) {
				tablero[fila][columna] = 0;
				int i = buscarPowerUp(fila, columna);
				s.comerConsumible(powerUps.get(i));
				powerUps.remove(i);
			}

			for (Snake s1 : serpientes) {
				if (!s.equals(s1)) {
					if (s.getPosicion().equals(s1.getPosicion())) { // head to head
						s.morir();
						s1.morir();
					} else { // head to boody
						ArrayList<BodySnake> bodyS1 = s1.getCuerpo();

						for (BodySnake body : bodyS1) {
							if (s.getPosicion().equals(body.getPosicion())) {
								s.morir();
								break;
							}
						}
					}
				}
			} // fin for(Snake s1 : serpientes)
		}

		for(int i = 0; i < serpientes.size(); i ++) {
			if(!serpientes.get(i).getState())
				serpientes.remove(i);
		}
		/*while (it.hasNext()) {
			Snake s = it.next();
			fila = (int) s.getPosicion().getX();
			columna = (int) s.getPosicion().getY();

			if (tablero[fila][columna] == PARED || s.comeSuCuerpo()) {
				s.morir();
				it.remove();
			}

			if (tablero[fila][columna] == IDFRUTA) {
				tablero[fila][columna] = 0;
				int i = buscarFruta(fila, columna);
				s.comerConsumible(frutas.get(i));
				frutas.remove(i);
				colocarFrutas();
			}

			if (tablero[fila][columna] == IDPU) {
				tablero[fila][columna] = 0;
				int i = buscarPowerUp(fila, columna);
				s.comerConsumible(powerUps.get(i));
				powerUps.remove(i);
			}

			for (Snake s1 : serpientes) {
				if (!s.equals(s1)) {
					if (s.getPosicion().equals(s1.getPosicion())) { // head to head
						s.morir();
						s1.morir();
						it.remove();
					} else { // head to boody
						ArrayList<BodySnake> bodyS1 = s1.getCuerpo();

						for (BodySnake body : bodyS1) {
							if (s.getPosicion().equals(body.getPosicion())) {
								s.morir();
								break;
							}
						}
					}
				}
			} // fin for(Snake s1 : serpientes)
		}//*/
	}

	public Snake getSnake(int i) {
		return this.serpientes.get(i);
	}

	public int getCantidadFrutas() {
		return this.frutas.size();
	}

	public Posicion getPosicionFruta(int i) {
		return this.frutas.get(i).devolverPosicion();
	}

	public int getCantidadSnakes() {
		return this.serpientes.size();
	}
	
	public boolean hayPared(Posicion pos)
	{
		return this.tablero[(int)pos.getX()][(int)pos.getY()]==PARED;
	}
}
