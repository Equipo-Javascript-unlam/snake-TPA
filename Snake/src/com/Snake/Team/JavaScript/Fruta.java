package com.Snake.Team.JavaScript;

public class Fruta extends Consumible{
	private Posicion posicion;
	
	public Fruta(Posicion posicion) {
		this.posicion = posicion;
	}

	@Override
	public tipoConsumible queEs() {
		return tipoConsumible.FRUTA;
	}

	@Override
	public Posicion devolverPosicion() {
		return posicion;
	}
}
