package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class NuevaSala extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int LARGO = 25;
	private final int ANCHO = 25;
	private JPanel contentPane;
	private JButton btnIniciar;
	private JButton cancelarButton;
	private JComboBox<?> comboBoxCantSnakes;
	private java.awt.List listPlayers;
	private String nombreJugador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login.nombre = "Test";

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaSala frame = new NuevaSala("Test");
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
	public NuevaSala(String nombreJugador) {
		this.nombreJugador = nombreJugador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// listado de jugadores
		listPlayers = new java.awt.List();
		listPlayers.setBackground(Color.WHITE);
		listPlayers.setMultipleSelections(false);
		listPlayers.setBounds(10, 41, 260, 153);
		listPlayers.add(nombreJugador);
		contentPane.add(listPlayers);

		JLabel lblListaDeJugadores = new JLabel("Lista de jugadores");
		lblListaDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeJugadores.setFont(new Font("Arial", Font.PLAIN, 11));
		lblListaDeJugadores.setBounds(10, 11, 151, 14);
		contentPane.add(lblListaDeJugadores);

		JLabel lblCantidadDeSerpientes = new JLabel("Cantidad de serpientes");
		lblCantidadDeSerpientes.setBounds(291, 45, 155, 14);
		contentPane.add(lblCantidadDeSerpientes);

		comboBoxCantSnakes = new JComboBox();
		comboBoxCantSnakes.setModel(new DefaultComboBoxModel(
				new String[] { "2 SERPIENTES", "3 SERPIENTES", "4 SERPIENTES", "5 SERPIENTES", "6 SERPIENTES" }));
		comboBoxCantSnakes.setSelectedIndex(0);
		comboBoxCantSnakes.setBounds(280, 70, 166, 20);
		contentPane.add(comboBoxCantSnakes);

		// Iniciar la partida
		btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(383, 327, 127, 23);
		contentPane.add(btnIniciar);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(259, 327, 89, 23);
		contentPane.add(cancelarButton);
		setVisible(true);
		addListener();
	}

	private void addListener() {
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> nombres = new ArrayList<>();

					for (int i = 0; i < comboBoxCantSnakes.getSelectedIndex() + 2; i++) {
						if (i < listPlayers.getItemCount())
							nombres.add(listPlayers.getItem(i));
						else
							nombres.add("Snake" + i);
					}
					new VentanaTablero(nombres, LARGO, ANCHO);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Menu(nombreJugador);
				dispose();
			}
		});
	}
}
