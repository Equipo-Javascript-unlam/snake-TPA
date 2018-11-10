package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.Snake.Team.JavaScript.Direccion;
import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Tablero;

import java.awt.Color;

public class VentanaTablero extends JFrame {

	private static final long serialVersionUID = 1L;
	private DibujoTablero contentPane;
	private static Tablero tablero = new Tablero(30,30, 11, 1);
	
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTablero frame = new VentanaTablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public static void main(String[] args) throws InterruptedException {
		
					new VentanaTablero();
				
	}
	public VentanaTablero() throws InterruptedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000,1000,1000,10000);
		contentPane = new DibujoTablero();
		contentPane.setBackground(Color.BLACK);
		MyKeyListener m=new MyKeyListener();
		addKeyListener(m);
		//setBackground(Color.GREEN);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		add(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		setVisible(true);
		add(contentPane);
		setLocationRelativeTo(null);
		setTitle("Snake - JavaScript");
		//int j=0;
		tablero.colocarVibora(new Posicion(1, 1), "Juan Vibora");
		tablero.getSnake(0).cambiarDireccion(Direccion.ABJ);
		
		// Game Loop
		while(true) {
			MyKeyListener.inicializar();
			add(contentPane);
			TimeUnit.MILLISECONDS.sleep(400);
		for(int i=0;i<tablero.getCantidadSnakes();i++)
			tablero.getSnake(i).moverse();
		tablero.colision();
		contentPane.repaint();
		repaint();
		}
		
		//tablero.getSnake(0).cambiarDireccion(Direccion.ABJ);
		/*while(true)
		{
			//System.out.println("a");
			MyKeyListener.inicializar();
			add(contentPane);
			TimeUnit.MILLISECONDS.sleep(400);
			for(int i=0;i<tablero.getCantidadSnakes();i++)
				tablero.getSnake(i).moverse();
			System.out.println("b");
			tablero.colision();
			getContentPane().removeAll();
			repaint();
		
			
		}
		*/
		
	}
	
	public static  class DibujoTablero extends JPanel implements ActionListener {

		private static final long serialVersionUID = 1L;
		private final int TAM_TABLERO = 30;
		private final int CUADRO=25;
		private final int PARED=5;
		private final int SEPARACION=2;
		
		
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			this.setBackground(Color.BLACK);
			Posicion p;
			g.setColor(Color.GREEN);
			
			//g.fillRect(0+TAM_PARED+SEPARACION_CUERPO,0+TAM_PARED+SEPARACION_CUERPO,TAM_CUADRO-SEPARACION_CUERPO,TAM_CUADRO-SEPARACION_CUERPO);
			//g.fillRect(0+TAM_PARED+SEPARACION_CUERPO,TAM_CUADRO+TAM_PARED+SEPARACION_CUERPO,TAM_CUADRO-SEPARACION_CUERPO,TAM_CUADRO-SEPARACION_CUERPO);
			//System.out.println(p);
			
			for (int j=0;j<tablero.getCantidadSnakes();j++) {
			p=tablero.getSnake(j).getPosicion();	
			g.fillRect(CUADRO*(int)p.getX(),CUADRO*(int)p.getY(),CUADRO-SEPARACION,CUADRO-SEPARACION);		
			//System.out.println(tablero.getSnake(0).getLongitud());
			for(int i=0;i<tablero.getSnake(j).getLongitud()-1;i++)
			{
				p=tablero.getSnake(j).getCuerpo().get(i).getPosicion();
				//p=tablero.getSnake(0).getPosicionCuerpo(i);
				g.fillRect(CUADRO*(int)p.getX(),CUADRO*(int)p.getY(),CUADRO-SEPARACION,CUADRO-SEPARACION);		
				//g.fillRect(CUADRO*(int)p.getX()+PARED+SEPARACION,CUADRO*(int)p.getY()+PARED+SEPARACION,CUADRO-SEPARACION,CUADRO-SEPARACION);	
			}
			}
			

			
			//g.fillRect(CUADRO*tablero+PARED+SEPARACION_CUERPO,CUADRO*i+PARED+SEPARACION_CUERPO,CUADRO-SEPARACION_CUERPO,CUADRO-SEPARACION_CUERPO);
			
			//for(int i=1;i<10;i++)
				//g.fillRect(CUADRO+PARED+SEPARACION_CUERPO,CUADRO*i+PARED+SEPARACION_CUERPO,CUADRO-SEPARACION_CUERPO,CUADRO-SEPARACION_CUERPO);
			
			g.setColor(Color.WHITE);
			
			
			
			g.fillRect(CUADRO-SEPARACION-PARED,CUADRO-SEPARACION-PARED,PARED,(TAM_TABLERO-2)*CUADRO+SEPARACION+PARED); //ARRIBA
			
			g.fillRect(CUADRO-SEPARACION-PARED,(TAM_TABLERO-1)*CUADRO,(TAM_TABLERO-2)*CUADRO+SEPARACION+2*PARED,PARED); //ABAJO
			
			g.fillRect((TAM_TABLERO-1)*CUADRO,CUADRO-SEPARACION-PARED,PARED,(TAM_TABLERO-2)*CUADRO+SEPARACION+PARED); //3
			
			g.fillRect(CUADRO-SEPARACION-PARED,CUADRO-SEPARACION-PARED,(TAM_TABLERO-2)*CUADRO+SEPARACION+PARED,PARED);//4
			
			g.setColor(Color.red);
			
			for(int i=0;i<tablero.getCantidadFrutas();i++)
			{
				p=tablero.getPosicionFruta(i);
				g.fillOval(CUADRO*(int)p.getX(),CUADRO*(int)p.getY(),CUADRO-SEPARACION,CUADRO-SEPARACION);		
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			g.fillRect(0,0,PARED,TAM_TABLERO*CUADRO);
			
			g.fillRect(0,TAM_TABLERO*CUADRO,TAM_TABLERO*CUADRO,PARED);
			
			g.fillRect(TAM_TABLERO*CUADRO,0,PARED,TAM_TABLERO*CUADRO);
			
			g.fillRect(0,0,TAM_TABLERO*CUADRO,PARED);
			*/
			
			
			
			
			
			
			
			
			
				
			//g.fillOval(25,25, 24,24);
			/*g.fillRect(TAM_CUADRO,TAM_CUADRO,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*2,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*3,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*4,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*5,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*6,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*7,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*8,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(TAM_CUADRO,TAM_CUADRO*9,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(2*TAM_CUADRO,TAM_CUADRO*9,TAM_CUADRO-1,TAM_CUADRO-1);
			g.fillRect(3*TAM_CUADRO,TAM_CUADRO*9,TAM_CUADRO-1,TAM_CUADRO-1);*/
			
			//g.fillRect(x, y, width, height);
		}



		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class MyKeyListener implements KeyListener
	{
		private static boolean TECLA_PRESIONADA;
		
		
		public static void inicializar()
		{
			TECLA_PRESIONADA=false;
		}

		
		@Override
		
		public void keyPressed(KeyEvent e) {
			
			if(TECLA_PRESIONADA)
				return;
			
			if(e.getKeyCode()==38) {
				tablero.getSnake(0).cambiarDireccion(Direccion.ARB);
				System.out.println("arriba");}

			if(e.getKeyCode()==40) {
				tablero.getSnake(0).cambiarDireccion(Direccion.ABJ);
			
				System.out.println("abajo");
			}
			
			if(e.getKeyCode()==37) {
				tablero.getSnake(0).cambiarDireccion(Direccion.IZQ);
			}
				System.out.println("izquierda");

			if(e.getKeyCode()==39) {
				tablero.getSnake(0).cambiarDireccion(Direccion.DRC);
			
				System.out.println("derecha");
			}
			
			TECLA_PRESIONADA=true;
			
			//System.out.println(e.getKeyCode());
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			//System.out.println("b");
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			//System.out.println("c");
			// TODO Auto-generated method stub
			
		}
		
	}

			
	
		
}


