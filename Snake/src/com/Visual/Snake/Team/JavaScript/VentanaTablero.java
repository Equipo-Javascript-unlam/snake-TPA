package com.Visual.Snake.Team.JavaScript;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Tablero;

import java.awt.Color;

public class VentanaTablero extends JFrame {

	private JPanel contentPane;
	private Tablero tablero = new Tablero(30,30, 1, 1);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}
	/**
	 * Create the frame.
	 */
	public VentanaTablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("Snake - JavaScript");
		
		tablero.colocarVibora(new Posicion(1, 1), "Nombre");
	}

}
