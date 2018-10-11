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
		
		//pregunto si hay cambio de direccion o no
		switch(dir) {
			case IZQ:
				if((this.direccion == Direccion.DRC && bodySnake.isEmpty()) || 
						this.direccion != Direccion.DRC)
					this.direccion = dir;
				break;
			case DRC:
				if((this.direccion == Direccion.IZQ && bodySnake.isEmpty()) || 
						this.direccion != Direccion.IZQ)
					this.direccion = dir;
				break;
			case ARB:
				if((this.direccion == Direccion.ABJ && bodySnake.isEmpty()) || 
						this.direccion != Direccion.ABJ)
					this.direccion = dir;
				break;
			case ABJ:
				if((this.direccion == Direccion.ARB && bodySnake.isEmpty()) || 
						this.direccion != Direccion.ARB)
					this.direccion = dir;
				break;
		}
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
	
//	private BodySnake[] bodySnake;
//	
//	public Snake(int bodySnakeMaxLenght) {
//	super();
//	this.bodySnake = new bodySnake[bodySnakeLenght];
//}

	public class BodySnake {
		private Posicion posicion;
	}
	
	public class HeadSnake{
		private Posicion posicion;

	}

	
}
