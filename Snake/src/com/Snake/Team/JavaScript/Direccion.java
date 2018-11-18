package com.Snake.Team.JavaScript;

public enum Direccion {
	ARB, ABJ, IZQ, DRC;
	
	public static Direccion getDirRand() {
		int i = (int) (Math.random() * 4) + 1;
				
		switch(i) {
		case 1:
			return ARB;
		case 2:
			return ABJ;
		case 3:
			return IZQ;
		default:
			return DRC;
		}
	}
}
