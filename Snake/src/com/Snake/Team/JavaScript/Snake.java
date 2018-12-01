package com.Snake.Team.JavaScript;

import java.awt.Color;
import java.util.ArrayList;

public class Snake {
	private boolean vivo;
	private HeadSnake headSnake;
	private ArrayList<BodySnake> bodySnake;
	private Direccion direccion;
	private String nombreJugador;
	private int cantidadDeFrutaConsumida;
	private Direccion orientacion;
	private Color color;

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
		case arriba:
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() - 1);
			break;
		case abajo:
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() + 1);
			break;
		case izquierda:
			headSnake.posicion.setLocation(lastPos.getX() - 1, lastPos.getY());
			break;
		case derecha:
			headSnake.posicion.setLocation(lastPos.getX() + 1, lastPos.getY());
			break;
		}

		if (!bodySnake.isEmpty()) {
			for (BodySnake nodo : bodySnake) {
				auxPos = new Posicion(nodo.posicion);
				nodo.posicion.setLocation(lastPos);
				lastPos.setLocation(auxPos);
			}
		}
	}

	public void cambiarDireccion(Direccion dir) {
		switch (dir) {
		case izquierda:
			setDirection(dir, Direccion.derecha);
			break;
		case derecha:
			setDirection(dir, Direccion.izquierda);
			break;
		case arriba:
			setDirection(dir, Direccion.abajo);
			break;
		case abajo:
			setDirection(dir, Direccion.arriba);
			break;
		}
	}

	private void setDirection(Direccion dir, Direccion opuesto) {
		if (bodySnake.isEmpty() || this.direccion != opuesto) 
			this.direccion = this.orientacion = dir;
	}

	public boolean comeSuCuerpo() {
		for (BodySnake bs : bodySnake)
			if (this.headSnake.posicion.equals(bs.getPosicion()))
				return true;

		return false;
	}

	public void crecer() {
		Posicion pos;
		BodySnake nuevaParte;

		if (bodySnake.isEmpty()) {
			pos = headSnake.posicion.sumar(orientacion.sentido());
		} else {
			if (bodySnake.size() == 1) {
				pos = bodySnake.get(bodySnake.size() - 1).posicion.sumar(orientacion.sentido());
			} else {
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
		switch (comida.queEs()) {
		case FRUTA :
			cantidadDeFrutaConsumida++;
			crecer();
			break;
		case POWERUP :
			// TO DO
			break;
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean getState() {
		return vivo;
	}

	public String getNombreJugador() {
		return nombreJugador;
	}
	
	public ArrayList<BodySnake> getCuerpo() {
		return this.bodySnake;
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
	
	public void setDireccion(Direccion direccion)
	{
		this.direccion=direccion;
	}
	
	public Direccion getDireccion() {
		return this.direccion;
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
