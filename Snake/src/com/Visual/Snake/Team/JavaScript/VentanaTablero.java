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
import com.Snake.Team.JavaScript.*;
import com.Client.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class VentanaTablero extends JFrame {
	private static final long serialVersionUID = 4392485967342779458L;
	private static Snake serpiente;// serpiente del servidor
	private static DibujoTablero contentPane;
	private static Tablero tablero;
	private java.awt.List listPlayers;
	private static int largo;
	private static int ancho;
	private Cliente cliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<String> names = new ArrayList<>();
					Login.nombre = "Test";
					names.add(Login.nombre);
					VentanaTablero frame = new VentanaTablero(names, 25, 25, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Color changeColor(int color) {
		switch (color) {
		case 0:
			return Color.GREEN;
		case 1:
			return Color.MAGENTA;
		case 2:
			return Color.ORANGE;
		case 3:
			return Color.PINK;
		case 4:
			return Color.YELLOW;
		case 5:
			return Color.BLUE;
		default:
			return Color.CYAN;
		}
	}

	public VentanaTablero(List<String> nameSnakes, int largo, int ancho, Cliente cliente) throws InterruptedException {
		VentanaTablero.largo = largo;
		VentanaTablero.ancho = ancho;
		this.cliente = cliente;
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

		listPlayers = new java.awt.List();
		listPlayers.setBackground(Color.WHITE);
		listPlayers.setBounds(650, 20, 300, 500);
		listPlayers.setFocusable(false);
		listPlayers.setFont(getFont().deriveFont(Font.BOLD, 20));
		for(String nombreJugador: nameSnakes) {
			listPlayers.add(nombreJugador);
		}

		contentPane.add(listPlayers);

		for (int i = 0; i < nameSnakes.size(); i++) {
			tablero.colocarVibora(nameSnakes.get(i));
			tablero.getSnake(i).cambiarDireccion(Direccion.getDirRand());
			tablero.getSnake(i).setColor(changeColor(i));
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
						if (!salir && tablero.getCantidadSnakes() > 0){
							for (int i = 0; i < tablero.getCantidadSnakes(); i++)
								tablero.getSnake(i).moverse();
							}
							
						else {
							if (!salir)
								JOptionPane.showMessageDialog(null,
										"Has obtenido un pasaje de ida al cementerio =).\nPuntaje:"
												+ serpiente.getCantidadDeFruta());

							new NuevaSala(serpiente.getNombreJugador(), cliente);
							dispose();
							break;
						}
						//tablero = dataDelServer
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

		public void paintComponent(Graphics graphics) {

			super.paintComponent(graphics);
			this.setBackground(Color.BLACK);
			Posicion p;

			for (int j = 0; j < tablero.getCantidadSnakes(); j++) {
				graphics.setColor(tablero.getSnake(j).getColor());
				p = tablero.getSnake(j).getPosicion();
				graphics.fillRect(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION, CUADRO - SEPARACION);

				for (int i = 0; i < tablero.getSnake(j).getLongitud() - 1; i++) {
					p = tablero.getSnake(j).getCuerpo().get(i).getPosicion();
					graphics.fillRect(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION,
							CUADRO - SEPARACION);
				}
			}

			graphics.setColor(Color.WHITE);

			graphics.fillRect(CUADRO - SEPARACION - PARED, CUADRO - SEPARACION - PARED, PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // ARRIBA

			graphics.fillRect(CUADRO - SEPARACION - PARED, (TAM_TABLERO - 1) * CUADRO,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + 2 * PARED, PARED); // ABAJO

			graphics.fillRect((TAM_TABLERO - 1) * CUADRO, CUADRO - SEPARACION - PARED, PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // 3

			graphics.fillRect(CUADRO - SEPARACION - PARED, CUADRO - SEPARACION - PARED,
					(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED, PARED);// 4

			graphics.setColor(Color.red);

			for (int i = 0; i < tablero.getCantidadFrutas(); i++) {
				p = tablero.getPosicionFruta(i);
				graphics.fillOval(CUADRO * (int) p.getX(), CUADRO * (int) p.getY(), CUADRO - SEPARACION, CUADRO - SEPARACION);
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

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

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}
}
