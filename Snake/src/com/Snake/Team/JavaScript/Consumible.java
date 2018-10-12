package com.Snake.Team.JavaScript;

public abstract class Consumible {	
	public enum tipoConsumible{
		FRUTA,
		POWERUP;
	}
	
	public abstract tipoConsumible queEs();
	public abstract Posicion devolverPosicion();
}
