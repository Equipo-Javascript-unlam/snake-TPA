package pruebas;


import static org.junit.Assert.*;

import org.junit.Test;

//import static org.junit.Assert.*;

//import org.junit.Test;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Fruta;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Snake;
import com.Snake.Team.JavaScript.Tablero;

class TestSnake {

	@Test
	void moverseEnLineaRecta() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");//Posicion inicial
		Posicion p = new Posicion(2,1);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.moverse();
		assertEquals(p, s1.getPosicion());		
	}
	
	@Test
	void cambiarDireccion() {
		Snake s1 = new Snake(new Posicion(1,2), "pepe");//Posicion inicial
		Posicion p = new Posicion(0,3);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.cambiarDireccion(Direccion.abajo);
		s1.moverse();
		s1.cambiarDireccion(Direccion.izquierda);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void moverseHaciaAtrasSinCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "pepe");//Posicion inicial
		Posicion p = new Posicion(2,3);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.cambiarDireccion(Direccion.izquierda);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void moverseHaciaAtrasConCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "pepe");//Posicion inicial
		Posicion p = new Posicion(4,3);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.crecer();
		s1.cambiarDireccion(Direccion.izquierda);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void  choqueDeDosCabezasMismaPosicion() {
		Tablero t = new Tablero(5, 5, 1);
		t.colocarVibora(new Posicion(1,1), "cabeza1");
		t.colocarVibora(new Posicion(1,3), "cabeza2");
		
		t.serpientes.get(0).cambiarDireccion(Direccion.derecha);
		t.serpientes.get(1).cambiarDireccion(Direccion.izquierda);
		t.serpientes.get(0).moverse();
		t.serpientes.get(1).moverse();
		t.colision();
	}
	
	@Test
	void choqueConLaPared() {
		Tablero t = new Tablero(5, 5, 5);
		
		t.colocarVibora(new Posicion(1,1), "pepe");
		t.serpientes.get(0).cambiarDireccion(Direccion.izquierda);
		t.serpientes.get(0).moverse();
		t.colision();//mostrar mensaje de chocar contra pared
	}
	
	@Test
	void consumirFrutaYCrecer() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Fruta fruta = new Fruta(new Posicion(1,2));
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.moverse();
		s1.comerConsumible(fruta);
		
		assertEquals(2, s1.getLongitud());
	}
	
	@Test
	void choqueConSuPropioCuerpo() {
		Tablero t = new Tablero(6, 6, 0);
		
		t.colocarVibora(new Posicion(4,3), "Aldo");
		
		t.serpientes.get(0).cambiarDireccion(Direccion.derecha);
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).cambiarDireccion(Direccion.abajo);
		t.serpientes.get(0).moverse();
		t.serpientes.get(0).cambiarDireccion(Direccion.izquierda);
		t.serpientes.get(0).moverse();
		t.serpientes.get(0).cambiarDireccion(Direccion.arriba);
		t.serpientes.get(0).moverse();
		t.colision();
	}
	
	@Test
	void choqueConCuerpoDeOtraSerpiente() {
		Snake snake1 = new Snake(new Posicion(1,1), "pepe");
		Snake snake2 = new Snake(new Posicion(2,2), "papa");
		
		
		
	}
	
	@Test
	void dosCabezasSeCruzan() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Snake s2 = new Snake(new Posicion(2,2), "papa");
	}
}
