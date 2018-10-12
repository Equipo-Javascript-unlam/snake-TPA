package com.Snake.Team.JavaScript;

import java.util.ArrayList;

import com.Snake.Team.JavaScript.Consumible.tipoConsumible;

public class Snake {
	private HeadSnake headSnake;
	private ArrayList<BodySnake> bodySnake;
	private Direccion direccion;
	private String nombreJugador;
	private int cantidadDeFrutaConsumida;
	private Dir orientacion;
	
	public String getNombreJugador() {
		return nombreJugador;
	}

	public Snake(Posicion pos, String nombre){
		nombreJugador = nombre;
		headSnake = new HeadSnake(pos);
		bodySnake = new ArrayList<BodySnake>();
		cantidadDeFrutaConsumida = 0;
	}

	public void moverse() {
		Posicion lastPos = headSnake.posicion;
		
		//seteo la nueva posicion de la serpiente
		switch(this.direccion) {
		case IZQ:
			//disminuye 1 en X
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() - 1);
			break;
		case DRC:
//			aumenta 1 en X
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY() + 1);
			break;
		case ARB:
			//aumenta 1 en Y
			headSnake.posicion.setLocation(lastPos.getX() - 1, lastPos.getY());
			break;
		case ABJ:
			//disminuye 1 en Y
			headSnake.posicion.setLocation(lastPos.getX() + 1, lastPos.getY());
			break;
		}
		
		if(!bodySnake.isEmpty()) {
			for(BodySnake nodo: bodySnake) {
				Posicion auxPos = nodo.posicion;
				nodo.posicion = lastPos;
				lastPos = auxPos;
			}
		}
	}
	
	public void cambiarDireccion(Direccion dir) {
		//pregunto si hay cambio de direccion o no
			switch(dir) {
				case IZQ:
					if(bodySnake.isEmpty() || this.direccion != Direccion.DRC) {
						this.direccion = dir;
						this.orientacion = this.orientacion.izquierda;
					}
					break;
				case DRC:
					if(bodySnake.isEmpty() || this.direccion != Direccion.IZQ) {
						this.direccion = dir;
						this.orientacion = this.orientacion.derecha;
					}
					break;		
				case ARB:
					if(bodySnake.isEmpty() || this.direccion != Direccion.ABJ) {
						this.direccion = dir;
						this.orientacion = this.orientacion.arriba;
					}
					break;
				case ABJ:
					if(bodySnake.isEmpty() || this.direccion != Direccion.ARB) {
						this.direccion = dir;
						this.orientacion = this.orientacion.abajo;
					}
					break;
				}
	}
	
//	private BodySnake[] bodySnake;
//	
//	public Snake(int bodySnakeMaxLenght) {
//	super();
//	this.bodySnake = new bodySnake[bodySnakeLenght];
//}
	enum Dir{
		arriba(-1, 0), abajo(1, 0), izquierda(0, -1), derecha(0, 1);
		final int f, c;
		
		Dir(int f, int c){
			this.f = f;
			this.c = c;
		}
	}
		
	public void crecer() {
		int f, c;
		Posicion pos;
		BodySnake nuevaParte;
		
		if(bodySnake.isEmpty()) {
			f = (int)headSnake.posicion.getX() + orientacion.f;
			c = (int)headSnake.posicion.getY() + orientacion.c;
			pos = new Posicion(f, c);
		}
		else {
			f = (int)bodySnake.get(bodySnake.size() - 1).posicion.getX() + orientacion.f;
			c = (int)bodySnake.get(bodySnake.size() - 1).posicion.getY() + orientacion.c;
			pos = new Posicion(f, c);
		}	
		
		nuevaParte = new BodySnake(pos);
		bodySnake.add(nuevaParte);
	}
	
	public void comerConsumible(Consumible comida) {
		if(comida.queEs() == tipoConsumible.FRUTA) {
			cantidadDeFrutaConsumida ++;
			crecer();
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
	}
	
	public class HeadSnake{
		private Posicion posicion;
		
		public HeadSnake(Posicion pos) {
			this.posicion = pos;
		}
	}	
}
