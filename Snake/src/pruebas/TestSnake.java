package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Snake;

class TestSnake {

	@Test
	void moverseEnLineaRecta() {
		Snake s1 = new Snake(); //setear la posición
		
		s1.moverse(Direccion.DRC);
		
	}
	
	@Test
	void cambiarDireccion() {
		
	}
	
	@Test
	void dosCabezasMismaPosicion() {
		
	}
	
	@Test
	void choqueConLaPared() {
		
	}
	
	@Test
	void consumirFrutaYCrecer() {
		
	}
	
	@Test
	void choqueConCuerpoDeOtraSerpiente() {
		
	}
	
	@Test
	void moverseHaciaAtrasSinCuerpo() {
		
	}
	
	@Test
	void moverseHaciaAtrasConCuerpo() {
		
	}
	
	@Test
	void choqueConSuPropioCuerpo() {
		
	}
	
	@Test
	void dosCabezasSeCruzan() {
		
	}
	
}
