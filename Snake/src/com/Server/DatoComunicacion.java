package com.Server;

import java.io.Serializable;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Tablero;

public class DatoComunicacion implements Serializable {

	private static final long serialVersionUID = -284764513422343245L;
	/*
	 * tipoDato 
	 * 0 - setear tablero 
	 * 1 - movimiento
	 * 2 - estado de la partida
	 */
	private int tipoDato;
	private int numeroSerpiente;
	private Tablero tablero;
	private Direccion movimiento;
	private int state;

	public DatoComunicacion() {
		tipoDato = 0;
		numeroSerpiente = 0;
		tablero = null;
		movimiento = null;
		state = 0;
	}
	
	public void setEstadoPartida(int estado) {
		state = estado;
	}
	
	public int getEstadoPartida() {
		return state;
	}

	public int getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(int tipoDato) {
		this.tipoDato = tipoDato;
	}

	public int getNumeroSerpiente() {
		return numeroSerpiente;
	}

	public void setNumeroSerpiente(int numeroSerpiente) {
		this.numeroSerpiente = numeroSerpiente;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	public void setMovimiento(Direccion movimiento) {
		this.movimiento = movimiento;
	}

	public Direccion getMovimiento() {
		return movimiento;
	}

}
