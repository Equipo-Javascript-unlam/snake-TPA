
package com.Snake.Team.JavaScript;

import java.util.ArrayList;

public class SnakeIA extends Snake {

	public SnakeIA() {
		super(new Posicion(7, 7), "Cerebro");
	}

	public Direccion getDireccionIA(Tablero tablero) {
		Direccion dir;
		Posicion move, fruta = getPosicionFrutaCercana(tablero);
		ArrayList<Direccion> direccionesPosibles = new ArrayList<Direccion>();
		boolean posicionPosible = true;
		for (int i = 0; i < 4; i++) // Una por cada posicion posible
		{
			dir = Direccion.numToDir(i);
			move = move(getPosicion(), dir);
			if (!tablero.hayPared(move) && !Direccion.esOpuesto(dir, getDireccion())) {
				for (int j = 0; j < tablero.getCantidadSnakes() && posicionPosible; j++) {
					posicionPosible = !move.equals(tablero.getSnake(j).getPosicion());
					for (int k = 0; k < tablero.getSnake(j).getCuerpo().size() && posicionPosible; k++)
						posicionPosible = !move.equals(tablero.getSnake(j).getCuerpo().get(k).getPosicion());
				}
				if (posicionPosible)
					direccionesPosibles.add(dir);
				posicionPosible = true;
			}

		}

		if (getPosicion().getX() != fruta.getX()) {
			if (getPosicion().getX() > fruta.getX() && esPosicionPosible(direccionesPosibles, Direccion.izquierda))
				return Direccion.izquierda;
			if (getPosicion().getX() < fruta.getX() && esPosicionPosible(direccionesPosibles, Direccion.derecha))
				return Direccion.derecha;
		} else {
			if (getPosicion().getY() > fruta.getY() && esPosicionPosible(direccionesPosibles, Direccion.arriba))
				return Direccion.arriba;
			if (getPosicion().getY() < fruta.getY() && esPosicionPosible(direccionesPosibles, Direccion.abajo))
				return Direccion.abajo;

		}
		if (direccionesPosibles.size() != 0)
			return direccionesPosibles.get((0));
		return Direccion.abajo;
	}

	public Posicion getPosicionFrutaCercana(Tablero tablero) {
		Posicion min = tablero.getPosicionFruta(0);
		for (int i = 0; i < tablero.getCantidadFrutas(); i++)
			if (tablero.getPosicionFruta(i).distance(getPosicion()) < min.distance(getPosicion()))
				min = new Posicion(tablero.getPosicionFruta(i));
		return min;
	}

	public boolean esPosicionPosible(ArrayList<Direccion> direccionesPosibles, Direccion d) {
		for (int i = 0; i < direccionesPosibles.size(); i++) {
			if (direccionesPosibles.get(i).equals(d))
				return true;
		}
		return false;
	}
	public Posicion move(Posicion pos, Direccion dir) {
		switch (dir) {
		case arriba:
			return new Posicion(pos.getX(), pos.getY() - 1);
		case abajo:
			return new Posicion(pos.getX(), pos.getY() + 1);
		case izquierda:
			return new Posicion(pos.getX() - 1, pos.getY());
		default:
			return new Posicion(pos.getX() + 1, pos.getY());
		}
	}
}
