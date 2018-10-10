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
		switch(dir) {
			case IZQ:
				if(this.direccion != Direccion.DRC && this.direccion != dir) 
					this.direccion = dir;
				break;
			case DRC:
				if(this.direccion != Direccion.IZQ && this.direccion != dir)
					this.direccion = dir;
				break;
			case ARB:
				if(this.direccion != Direccion.ABJ && this.direccion != dir)
					this.direccion = dir;
				break;
			case ABJ:
				if(this.direccion != Direccion.ARB && this.direccion != dir)
					this.direccion = dir;
				break;
			
		}
		headSnake.setPos(direccion);
		bodySnake.setPos(direccion);
	}
	
//	private BodySnake[] bodySnake;
//	
//	public Snake(int bodySnakeMaxLenght) {
//	super();
//	this.bodySnake = new bodySnake[bodySnakeLenght];
//}

	
	
}
