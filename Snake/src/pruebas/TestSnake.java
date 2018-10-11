package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.TableColumn;

import org.junit.jupiter.api.Test;

import com.Snake.Team.JavaScript.Direccion;
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
		Snake s1 = new Snake(new Posicion(1,1), "pepe");//Posicion inicial
		Posicion p = new Posicion(2,0);//posicion esperada
		
		s1.cambiarDireccion(Direccion.DRC);
		s1.cambiarDireccion(Direccion.ABJ);
		s1.moverse();
		s1.cambiarDireccion(Direccion.IZQ);
		s1.moverse();
		
		assertEquals(p, s1.getPosicion());
	}
	
	@Test
	void  choqueDeDosCabezasMismaPosicion() {
		Tablero t = new Tablero(5,5,5,2);
		t.colocarVibora(new Posicion(1,1), "pepe");
		t.colocarVibora(new Posicion(1,3), "papa");
		//Setear la posicion inicial
		//Snake s1 = new Snake();//(1;1)
		//Snake s2 = new Snake(new Posicion(1,3));//(1;3)
		
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
		Snake s1 = new Snake(new Posicion(1,1), "pepe");//Empezar cerca de la pared
		//Tablero t = new Tablero();
		
		//s1.moverse(a una pared);
		//t.colision();//mostrar mensaje de chocar contra pared
		//Assert.assertEquals(null, cabeza);
	}
	
	@Test
	void consumirFrutaYCrecer() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
	}
	
	@Test
	void choqueConCuerpoDeOtraSerpiente() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Snake s2 = new Snake(new Posicion(2,2), "papa");
	}
	
	@Test
	void moverseHaciaAtrasSinCuerpo() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
	}
	
	@Test
	void moverseHaciaAtrasConCuerpo() {
		Snake s1 = new Snake(new Posicion(1,5), "pepe");
	}
	
	@Test
	void choqueConSuPropioCuerpo() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
	}
	
	@Test
	void dosCabezasSeCruzan() {
		Snake s1 = new Snake(new Posicion(1,1), "pepe");
		Snake s2 = new Snake(new Posicion(2,2), "papa");
	}
	
}
