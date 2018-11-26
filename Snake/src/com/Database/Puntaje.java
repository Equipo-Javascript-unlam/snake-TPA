package com.Database;


public class Puntaje {
	private String nombre;
	private int victorias;
	private int derrotas;
	
	public Puntaje(String nom, int wins, int defeats) {
		nombre = nom;
		victorias = wins;
		derrotas = defeats;
	}

	public String getNombre() {
		return nombre;
	}

	public String getVictorias() {
		return String.valueOf(victorias);
	}

	public String getDerrotas() {
		return String.valueOf(derrotas);
	}
	
	
}
