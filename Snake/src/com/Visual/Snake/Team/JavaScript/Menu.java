package com.Visual.Snake.Team.JavaScript;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePartidas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Lista de Salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnCrearSala = new JButton("Nueva Sala ");
 		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NuevaSala();
				dispose();
			}
		});
		btnCrearSala.setBounds(321, 290, 110, 23);
		contentPane.add(btnCrearSala);
		
		tablePartidas = new JTable();
		tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePartidas.setBounds(10, 11, 421, 257);
		contentPane.add(tablePartidas);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		btnUnirse.setEnabled(false);
		btnUnirse.setBounds(10, 290, 89, 23);
		contentPane.add(btnUnirse);
		setVisible(true); 
	}
}
