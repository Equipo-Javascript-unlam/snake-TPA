package com.Snake.Team.JavaScript;

import java.util.ArrayList;

import com.Snake.Team.JavaScript.Consumible.tipoConsumible;

public class Snake {
	private boolean vivo;
	private HeadSnake headSnake;
	private ArrayList<BodySnake> bodySnake;
	private Direccion direccion;
	private String nombreJugador;
	private int cantidadDeFrutaConsumida;
	private Dir orientacion;

	public boolean getState() {
		return vivo;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}

	public ArrayList<BodySnake> getCuerpo() {
		return this.bodySnake;
	}

	public Snake(Posicion pos, String nombre) {
		nombreJugador = nombre;
		headSnake = new HeadSnake(pos);
		bodySnake = new ArrayList<BodySnake>();
		cantidadDeFrutaConsumida = 0;
		vivo = true;
	}

	public void moverse() {
		Posicion lastPos = new Posicion(headSnake.posicion), auxPos;

		switch (this.direccion) {
		case ARB:
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() - 1);
			break;
		case ABJ:
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() + 1);
			break;
		case IZQ:
			headSnake.posicion.setLocation(lastPos.getX() - 1, lastPos.getY());
			break;
		case DRC:
			headSnake.posicion.setLocation(lastPos.getX() + 1, lastPos.getY());
			break;
		}

		if (!bodySnake.isEmpty()) {
			for (BodySnake nodo : bodySnake) {
				auxPos = new Posicion(nodo.posicion);
				nodo.posicion = new Posicion(lastPos);
				lastPos = new Posicion(auxPos);
			}
		}
	}

	public void cambiarDireccion(Direccion dir) {
		switch (dir) {
		case IZQ:
			setDirection(dir, Direccion.DRC, Dir.izquierda);
			break;
		case DRC:
			setDirection(dir, Direccion.IZQ, Dir.derecha);
			break;
		case ARB:
			setDirection(dir, Direccion.ABJ, Dir.arriba);
			break;
		case ABJ:
			setDirection(dir, Direccion.ARB, Dir.abajo);
			break;
		}
	}

	private void setDirection(Direccion dir, Direccion opuesto, Dir orientacion) {
		if (bodySnake.isEmpty() || this.direccion != opuesto) {
			this.direccion = dir;
			this.orientacion = orientacion;
		}
	}

	public boolean comeSuCuerpo() {
		for (BodySnake bs : bodySnake)
			if (this.headSnake.posicion.equals(bs.getPosicion()))
				return true;

		return false;
	}

	enum Dir {
		izquierda(-1, 0), derecha(1, 0), abajo(0, -1), arriba(0, 1);
		final int fila, columna;

		Dir(int fila, int columna) {
			this.fila = fila;
			this.columna = columna;
		}

		Posicion sentido() {
			return new Posicion(this.fila, this.columna);
		}
	}

	public void crecer() {
		Posicion pos;
		BodySnake nuevaParte;

		if (bodySnake.isEmpty())
			pos = headSnake.posicion.sumar(orientacion.sentido());

		else {

			if (bodySnake.size() == 1)
				pos = bodySnake.get(bodySnake.size() - 1).posicion.sumar(orientacion.sentido());
			else {
				pos = bodySnake.get(bodySnake.size() - 1).posicion;
				pos = pos.sumar(pos);
				pos = pos.restar(bodySnake.get(bodySnake.size() - 2).posicion);
			}
		}

		nuevaParte = new BodySnake(pos);
		bodySnake.add(nuevaParte);
	}

	public void morir() {
		bodySnake.clear();
		vivo = false;
	}

	public void comerConsumible(Consumible comida) {
		if (comida.queEs() == tipoConsumible.FRUTA) {
			cantidadDeFrutaConsumida++;
			crecer();
		}

		if (comida.queEs() == tipoConsumible.POWERUP) {
			System.out.println("comiste un poder");
		}
	}

	public int getCantidadDeFruta() {
		return cantidadDeFrutaConsumida;
	}

	public Posicion getPosicion() {
		return headSnake.posicion;
	}

	public int getLongitud() {
		return bodySnake.size() + 1;
	}

	public class BodySnake {
		private Posicion posicion;

		public BodySnake(Posicion pos) {
			this.posicion = pos;
		}

		public Posicion getPosicion() {
			return posicion;
		}
	}

	public class HeadSnake {
		private Posicion posicion;

		public HeadSnake(Posicion pos) {
			this.posicion = pos;
		}
	}
}
