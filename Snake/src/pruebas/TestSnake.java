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

public class TestSnake {

	@Test
	public void moverseEnLineaRecta() {
		Snake s1 = new Snake(new Posicion(1,1), "snake1");//Posicion inicial
		Posicion p = new Posicion(2,1);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.moverse();
		assertEquals(p, s1.getPosicion());		
	}
	
	@Test
	public void cambiarDireccion() {
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
	public void moverseHaciaAtrasSinCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "pepe");//Posicion inicial
		Posicion p = new Posicion(2,3);//posicion esperada
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.cambiarDireccion(Direccion.izquierda);
		s1.moverse();
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	public void moverseHaciaAtrasConCuerpo() {
		Snake s1 = new Snake(new Posicion(3,3), "Test");
		Posicion finalPosition = new Posicion(4,3);
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.crecer();
		s1.cambiarDireccion(Direccion.izquierda);
		s1.moverse();
		
		assertEquals(finalPosition, s1.getPosicion());
	}
	
	@Test
	public void choqueDeDosCabezasMismaPosicion() {
		Tablero t = new Tablero(10, 10, 1);
		t.colocarVibora(new Posicion(1,1), "Snake1");
		t.colocarVibora(new Posicion(3,1), "Snake2");
		
		t.getSnake(0).cambiarDireccion(Direccion.derecha);
		t.getSnake(1).cambiarDireccion(Direccion.izquierda);
		t.getSnake(0).moverse();
		t.getSnake(1).moverse();
		t.colision();
		
		assertEquals(true, t.serpientes.isEmpty());
	}
	
	@Test
	public void choqueConLaPared() {
		Tablero t = new Tablero(5, 5, 5);
		
		t.colocarVibora(new Posicion(1,1), "pepe");
		t.serpientes.get(0).cambiarDireccion(Direccion.izquierda);
		t.serpientes.get(0).moverse();
		t.colision();//mostrar mensaje de chocar contra pared
		
		assertEquals(true, t.serpientes.isEmpty());
	}
	
	@Test
	public void consumirFrutaYCrecer() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Fruta fruta = new Fruta(new Posicion(1,2));
		
		s1.cambiarDireccion(Direccion.derecha);
		s1.moverse();
		s1.comerConsumible(fruta);
		
		assertEquals(2, s1.getLongitud());
	}
	
	@Test
	public void choqueConSuPropioCuerpo() {
		Tablero tablero = new Tablero(10, 10, 0);
		
		tablero.colocarVibora(new Posicion(4,3), "Test");
		
		tablero.getSnake(0).cambiarDireccion(Direccion.derecha);
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).crecer();
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).crecer();
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).crecer();
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).crecer();
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).cambiarDireccion(Direccion.abajo);
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).cambiarDireccion(Direccion.izquierda);
		tablero.getSnake(0).moverse();
		tablero.getSnake(0).cambiarDireccion(Direccion.arriba);
		tablero.getSnake(0).moverse();
		tablero.colision();
		
		assertEquals(true, tablero.serpientes.isEmpty());
	}
	
	@Test
	public void choqueConCuerpoDeOtraSerpiente() {
		Tablero tablero = new Tablero(6, 6, 0);
		
		tablero.colocarVibora(new Snake(new Posicion(2,2), "snake1"));
		tablero.colocarVibora(new Snake(new Posicion(3,3), "snake2"));
		tablero.getSnake(0).cambiarDireccion(Direccion.abajo);
		tablero.getSnake(1).cambiarDireccion(Direccion.izquierda);
		tablero.getSnake(1).moverse();
		tablero.getSnake(1).crecer();
		tablero.getSnake(1).moverse();
		tablero.getSnake(0).moverse();
		tablero.colision();
		
		assertEquals(1, tablero.serpientes.size());
	}
	
	@Test
	public void dosCabezasSeCruzan() {
		Tablero tablero = new Tablero(6, 6, 0);
		Snake snake1 = new Snake(new Posicion(2,2), "snake1");
		Snake snake2 = new Snake(new Posicion(3,3), "snake2");
		tablero.colocarVibora(snake1);
		tablero.colocarVibora(snake2);
		tablero.getSnake(0).cambiarDireccion(Direccion.derecha);
		tablero.getSnake(1).cambiarDireccion(Direccion.arriba);
		tablero.getSnake(0).moverse();
		tablero.getSnake(1).moverse();
		tablero.colision();
		
		assertEquals(true, tablero.serpientes.isEmpty());
	}
}
