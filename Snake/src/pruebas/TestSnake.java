package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Snake;

import junit.framework.Assert;

class TestSnake {

	@Test
	void moverseEnLineaRecta() {
		Snake s1 = new Snake(1,1); //setear la posición
		Posicion p = new Posicion(1,2);
		
		s1.moverse(Direccion.DRC);
		System.out.println(s1.getLocation());
		assertEquals(p, s1.getLocation());// El esperado sería Y+1
		
	}
	
	@Test
	void cambiarDireccion() {
		Snake s1 = new Snake(1,1);
		Posicion p = new Posicion(2,0);
		s1.moverse(Direccion.ABJ);
		
		s1.moverse(Direccion.IZQ);
		
		assertEquals(p, s1.getLocation());//El esperado sería x+1 e y-1
	}
	
	@Test
	void  choqueDeDosCabezasMismaPosición() {
		//Setear la posicion inicial
		Snake s1 = new Snake(1,1);//(1;1)
		Snake s2 = new Snake(1,3);//(1;3)
		
		s1.moverse(Direccion.DRC);
		s2.moverse(Direccion.IZQ);
		
		//Borrar la instancia, comprobar que la cabeza sea null
		//Assert.assertEquals(null, cabeza);
	}
	
	@Test
	void choqueConLaPared() {
		Snake s1 = new Snake(1,1);//Empezar cerca de la pared
		//Tablero t = new Tablero();
		
		//s1.moverse(a una pared);
		//t.colision();//mostrar mensaje de chocar contra pared
		//Assert.assertEquals(null, cabeza);
	}
	
	@Test
	void consumirFrutaYCrecer() {
		Snake s1 = new Snake(1,1);
	}
	
	@Test
	void choqueConCuerpoDeOtraSerpiente() {
		Snake s1 = new Snake(1,1);
		Snake s2 = new Snake(2,2);
	}
	
	@Test
	void moverseHaciaAtrasSinCuerpo() {
		Snake s1 = new Snake(1,1);
	}
	
	@Test
	void moverseHaciaAtrasConCuerpo() {
		Snake s1 = new Snake(1,5);
	}
	
	@Test
	void choqueConSuPropioCuerpo() {
		Snake s1 = new Snake(1,1);
	}
	
	@Test
	void dosCabezasSeCruzan() {
		Snake s1 = new Snake(1,1);
		Snake s2 = new Snake(2,2);
	}
	
}
