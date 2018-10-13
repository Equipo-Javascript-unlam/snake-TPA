package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Fruta;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Snake;
import com.Snake.Team.JavaScript.Tablero;

class TestSnake {

	@Test
	void moverseEnLineaRecta() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");//Posicion inicial
		Posicion p = new Posicion(1,2);//posicion esperada
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.moverse();
		assertEquals(p, s1.getPosicion());		
	}
	
	@Test
	void cambiarDireccion() {
		Snake s1 = new Snake(new Posicion(1,2), "pepe");//Posicion inicial
		Posicion p = new Posicion(2,1);//posicion esperada
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.cambiarDireccion(Direccion.ABJ);
		s1.moverse();
		s1.cambiarDireccion(Direccion.IZQ);
		s1.moverse();
		
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void  choqueDeDosCabezasMismaPosicion() {
		Tablero t = new Tablero(5, 5, 1, 2);
		t.colocarVibora(new Posicion(1,1), "pepe");
		t.colocarVibora(new Posicion(1,3), "papa");
		
		t.serpientes.get(0).cambiarDireccion(Direccion.DRC);
		t.serpientes.get(1).cambiarDireccion(Direccion.IZQ);
		t.serpientes.get(0).moverse();
		t.serpientes.get(1).moverse();
		t.colision();
		//Borrar la instancia, comprobar que la cabeza sea null
		//Assert.assertEquals(null, cabeza);
	}
	
	@Test
	void choqueConLaPared() {
		Tablero t = new Tablero(5, 5, 5, 2);
		
		t.colocarVibora(new Posicion(1,1), "pepe");
		t.serpientes.get(0).cambiarDireccion(Direccion.IZQ);
		t.serpientes.get(0).moverse();
		t.colision();//mostrar mensaje de chocar contra pared
	}
	
	@Test
	void consumirFrutaYCrecer() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Fruta fruta = new Fruta(new Posicion(1,2));
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.moverse();
		s1.comerConsumible(fruta);
		
		assertEquals(2, s1.getLongitud());
	}
	
	@Test
	void choqueConCuerpoDeOtraSerpiente() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Snake s2 = new Snake(new Posicion(2,2), "papa");
	}
	
	@Test
	void moverseHaciaAtrasSinCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "pepe");//Posicion inicial
		Posicion p = new Posicion(3,2);//posicion esperada
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.cambiarDireccion(Direccion.IZQ);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
		
	}
	
	@Test
	void moverseHaciaAtrasConCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "pepe");//Posicion inicial
		Posicion p = new Posicion(3,4);//posicion esperada
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.crecer();
		s1.cambiarDireccion(Direccion.IZQ);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void choqueConSuPropioCuerpo() {
		Tablero t = new Tablero(6, 6, 0, 1);
		
		t.colocarVibora(new Posicion(4,3), "pepe");
		
		t.serpientes.get(0).cambiarDireccion(Direccion.DRC);
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).crecer();
		t.serpientes.get(0).cambiarDireccion(Direccion.ABJ);
		t.serpientes.get(0).moverse();
		t.serpientes.get(0).cambiarDireccion(Direccion.IZQ);
		t.serpientes.get(0).moverse();
		t.serpientes.get(0).cambiarDireccion(Direccion.ARB);
		t.serpientes.get(0).moverse();
		t.colision();
	}
	
	@Test
	void dosCabezasSeCruzan() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Snake s2 = new Snake(new Posicion(2,2), "papa");
	}
	
}
