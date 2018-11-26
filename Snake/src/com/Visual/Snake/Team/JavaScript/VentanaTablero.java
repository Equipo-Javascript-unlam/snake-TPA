package com.Visual.Snake.Team.JavaScript;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Snake;
import com.Snake.Team.JavaScript.Tablero;

import java.awt.Color;

public class VentanaTablero extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Snake serpiente;// serpiente del servidor
	private static DibujoTablero contentPane;
	private static Tablero tablero;
	static int largo;
	static int ancho;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {
		List<String> names = new ArrayList<>();
		Login.nombre = "Cascote";
		names.add(Login.nombre);
		new VentanaTablero(names, 25, 25);

	}

	public VentanaTablero(List<String> nameSnakes, int largo, int ancho) throws InterruptedException {
		this.largo = largo;
		this.ancho = ancho;
		tablero = new Tablero(largo, ancho, 11);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 1000, 1000, 10000);
		contentPane = new DibujoTablero();
		contentPane.setBackground(Color.BLACK);
		MyKeyListener m = new MyKeyListener();
		addKeyListener(m);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		setVisible(true);
		getContentPane().add(contentPane);

		setLocationRelativeTo(null);
		setTitle("Snake - JavaScript");

		for (int i = 0; i < nameSnakes.size(); i++) {
			tablero.colocarVibora(nameSnakes.get(i));
			tablero.getSnake(i).cambiarDireccion(Direccion.getDirRand());
		}

		serpiente = tablero.getSnake(0);
		// Game Loop
		Thread hilo = new Thread(new Runnable() {
			public void run() {
				boolean salir = false;

				try {
					while (true) {
						MyKeyListener.inicializar();
						getContentPane().add(contentPane);
						TimeUnit.MILLISECONDS.sleep(200);
						salir = MyKeyListener.escapeKeyPressed();

						// reemplazar esto una vez creado servidor cliente
						if (!salir && tablero.getCantidadSnakes() > 0)
							for (int i = 0; i < tablero.getCantidadSnakes(); i++)
								tablero.getSnake(i).moverse();
						else {
							if (!salir)
								JOptionPane.showMessageDialog(null, "Has obtenido un pasaje de ida al cementerio =)."
										+ serpiente.getCantidadDeFruta());

							new NuevaSala();
							dispose();
							break;
						}

						tablero.colision();
						contentPane.repaint();
						repaint();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		hilo.start();
	}

	public static class DibujoTablero extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private final int TAM_TABLERO = VentanaTablero.ancho;
		private final int CUADRO = VentanaTablero.largo;
		private final int PARED = 5;
		private final int SEPARACION = 2;

		private Color changeColor(int color) {
			switch (color) {
			case 0:
				return Color.BLUE;
			case 1:
				return Color.MAGENTA;
			case 2:
				return Color.ORANGE;
			case 3:
				return Color.PINK;
			case 4:
				return Color.YELLOW;
			default:
				return Color.CYAN;
			}
		}

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			this.setBackground(Color.BLACK);
			Posicion p;
			g.setColor(Color.GREEN);

			for (int j = 0; j < tablero.getCantidadSnakes(); j++) {

				p = tablero.getSnake(j).getPosicion();
				g.fillRect(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION, CUADRO - SEPARACION);

				for (int i = 0; i < tablero.getSnake(j).getLongitud() - 1; i++) {
					p = tablero.getSnake(j).getCuerpo().get(i).getPosicion();
					g.fillRect(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION,
							CUADRO - SEPARACION);
				}

				g.setColor(changeColor(j));
			}

			g.setColor(Color.WHITE);

			g.fillRect(CUADRO - SEPARACION - PARED, CUADRO - SEPARACION - PARED, PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // ARRIBA

			g.fillRect(CUADRO - SEPARACION - PARED, (TAM_TABLERO - 1) * CUADRO,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + 2 * PARED, PARED); // ABAJO

			g.fillRect((TAM_TABLERO - 1) * CUADRO, CUADRO - SEPARACION - PARED, PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // 3

			g.fillRect(CUADRO - SEPARACION - PARED, CUADRO - SEPARACION - PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED, PARED);// 4

			g.setColor(Color.red);

			for (int i = 0; i < tablero.getCantidadFrutas(); i++) {
				p = tablero.getPosicionFruta(i);
				g.fillOval(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION, CUADRO - SEPARACION);
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	public static class MyKeyListener implements KeyListener {
		private static boolean TECLA_PRESIONADA;
		private static boolean TECLA_ESCAPE;

		public static void inicializar() {
			TECLA_PRESIONADA = false;
			TECLA_ESCAPE = false;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (!serpiente.getState())
				return;

			if (TECLA_PRESIONADA)
				return;

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				tablero.getSnake(0).cambiarDireccion(Direccion.arriba);
				System.out.println("arriba");
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				tablero.getSnake(0).cambiarDireccion(Direccion.abajo);

				System.out.println("abajo");
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				tablero.getSnake(0).cambiarDireccion(Direccion.izquierda);
			}
			System.out.println("izquierda");

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				tablero.getSnake(0).cambiarDireccion(Direccion.derecha);
				System.out.println("derecha");
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				TECLA_ESCAPE = true;
			}

			TECLA_PRESIONADA = true;
		}

		public static boolean escapeKeyPressed() {
			return TECLA_ESCAPE;
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// System.out.println("b");
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// System.out.println("c");
			// TODO Auto-generated method stub

		}
	}
}
