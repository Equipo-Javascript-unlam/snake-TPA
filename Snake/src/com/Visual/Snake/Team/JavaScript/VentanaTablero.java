package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Tablero;

import java.awt.Color;

public class VentanaTablero extends JFrame {

	private static final long serialVersionUID = 1L;
	private DibujoTablero contentPane;
	private static Tablero tablero = new Tablero(30, 30, 11, 1);

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * 
	 * @throws InterruptedException
	 */

	public static void main(String[] args) throws InterruptedException {

		new VentanaTablero();

	}

	public VentanaTablero() throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 1000, 1000, 10000);
		contentPane = new DibujoTablero();
		contentPane.setBackground(Color.BLACK);
		MyKeyListener m = new MyKeyListener();
		addKeyListener(m);
		add(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		setVisible(true);
		add(contentPane);
		setLocationRelativeTo(null);
		setTitle("Snake - JavaScript");
		tablero.colocarVibora(new Posicion(1, 1), "Juan Vibora");
		tablero.getSnake(0).cambiarDireccion(Direccion.ABJ);

		// Game Loop
		Thread hilo = new Thread(new Runnable() {
			public void run() {
				try {
					while (true) {
						MyKeyListener.inicializar();
						add(contentPane);
						TimeUnit.MILLISECONDS.sleep(200);

						if (tablero.getCantidadSnakes() > 0)
							for (int i = 0; i < tablero.getCantidadSnakes(); i++)
								tablero.getSnake(i).moverse();
						else {
							JOptionPane.showMessageDialog(null, "Has obtenido un pasaje de ida al cementerio =).");
							new Menu();
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
		private final int TAM_TABLERO = 30;
		private final int CUADRO = 25;
		private final int PARED = 5;
		private final int SEPARACION = 2;

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
			}
			g.setColor(Color.WHITE);

			g.fillRect(CUADRO - SEPARACION - PARED,
						CUADRO - SEPARACION - PARED,
						PARED,
						(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // ARRIBA

			g.fillRect(CUADRO - SEPARACION - PARED,
						(TAM_TABLERO - 1) * CUADRO,
						(TAM_TABLERO - 2) * CUADRO + SEPARACION + 2 * PARED,
						PARED); // ABAJO

			g.fillRect((TAM_TABLERO - 1) * CUADRO,
						CUADRO - SEPARACION - PARED,
						PARED,
						(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED); // 3

			g.fillRect(CUADRO - SEPARACION - PARED,
						CUADRO - SEPARACION - PARED,
						(TAM_TABLERO - 2) * CUADRO + SEPARACION + PARED,
						PARED);// 4

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

		public static void inicializar() {
			TECLA_PRESIONADA = false;
		}

		@Override

		public void keyPressed(KeyEvent e) {

			if (TECLA_PRESIONADA)
				return;

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				tablero.getSnake(0).cambiarDireccion(Direccion.ARB);
				System.out.println("arriba");
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				tablero.getSnake(0).cambiarDireccion(Direccion.ABJ);

				System.out.println("abajo");
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				tablero.getSnake(0).cambiarDireccion(Direccion.IZQ);
			}
			System.out.println("izquierda");

			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				tablero.getSnake(0).cambiarDireccion(Direccion.DRC);
				System.out.println("derecha");}

			TECLA_PRESIONADA = true;
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
