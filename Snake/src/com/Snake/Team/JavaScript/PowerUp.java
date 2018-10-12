package com.Snake.Team.JavaScript;

public class PowerUp extends Consumible{
	private Posicion posicion;
	
	public PowerUp(Posicion posicion) {
		this.posicion = posicion;
	}

	@Override
	public tipoConsumible queEs() {
		return tipoConsumible.POWERUP;
	}

	@Override
	public Posicion devolverPosicion() {
		return posicion;
	}
}
