package com.Snake.Team.JavaScript;

import java.util.ArrayList;

public class Snake {
	private HeadSnake headSnake;
	private ArrayList<BodySnake> bodySnake;
	private Direccion direccion;
	
	public Snake(){
		bodySnake = new ArrayList<BodySnake>();
	}
	
	public void moverse(Direccion dir) {
		Posicion lastPos = headSnake.posicion;
		
	
		//seteo la nueva posicion de la serpiente
		switch(this.direccion) {
		case IZQ:
			//disminuye 1 en X
			headSnake.posicion.setLocation(lastPos.getX()-1, lastPos.getY());
			break;
		case DRC:
//			aumenta 1 en X
			headSnake.posicion.setLocation(lastPos.getX()+1, lastPos.getY());
			break;
		case ARB:
			//aumenta 1 en Y
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY()-1);
			break;
		case ABJ:
			//disminuye 1 en Y
			headSnake.posicion.setLocation(lastPos.getX(), lastPos.getY()+1);
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
					if(bodySnake.isEmpty() || this.direccion != Direccion.DRC)
						this.direccion = dir;
					break;
				case DRC:
					if(bodySnake.isEmpty() || this.direccion != Direccion.IZQ)
						this.direccion = dir;
					break;		
				case ARB:
					if(bodySnake.isEmpty() || this.direccion != Direccion.ABJ)
						this.direccion = dir;
					break;
				case ABJ:
					if(bodySnake.isEmpty() || this.direccion != Direccion.ARB)
						this.direccion = dir;
					break;
				}
	}
	
//	private BodySnake[] bodySnake;
//	
//	public Snake(int bodySnakeMaxLenght) {
//	super();
//	this.bodySnake = new bodySnake[bodySnakeLenght];
//}
	public void crecer() {
		
	}
	
	public class BodySnake {
		private Posicion posicion;
	}
	
	public class HeadSnake{
		private Posicion posicion;

	}

	
}
