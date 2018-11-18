package com.Visual.Snake.Team.JavaScript;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.Snake.Team.JavaScript.Posicion;
import com.Snake.Team.JavaScript.Snake;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

public class NuevaSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login.nombre = "Cascote";// agregado para pruebas

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevaSala frame = new NuevaSala();
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
	public NuevaSala() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// listado de jugadores
		java.awt.List listPlayers = new java.awt.List();
		listPlayers.setMultipleSelections(false);
		listPlayers.setBounds(10, 41, 260, 153);
		listPlayers.add(Login.nombre);
		contentPane.add(listPlayers);

		JLabel lblListaDeJugadores = new JLabel("Lista de jugadores");
		lblListaDeJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeJugadores.setFont(new Font("Arial", Font.PLAIN, 11));
		lblListaDeJugadores.setBounds(10, 11, 151, 14);
		contentPane.add(lblListaDeJugadores);

		JLabel lblCantidadDeSerpientes = new JLabel("Cantidad de serpientes");
		lblCantidadDeSerpientes.setBounds(291, 45, 139, 14);
		contentPane.add(lblCantidadDeSerpientes);

		JComboBox comboBoxCantSnakes = new JComboBox();
		comboBoxCantSnakes.setModel(new DefaultComboBoxModel(
				new String[] { "2 SERPIENTES", "3 SERPIENTES", "4 SERPIENTES", "5 SERPIENTES", "6 SERPIENTES" }));
		comboBoxCantSnakes.setSelectedIndex(0);
		comboBoxCantSnakes.setBounds(280, 70, 132, 20);
		contentPane.add(comboBoxCantSnakes);

		JLabel lblTamaoDelMapa = new JLabel("Tama\u00F1o del mapa");
		lblTamaoDelMapa.setBounds(284, 124, 114, 14);
		contentPane.add(lblTamaoDelMapa);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "20 X 20", "25 X 25", "MAX" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(276, 147, 136, 20);
		contentPane.add(comboBox);

		// Iniciar la partida
		JButton btnIniciar = new JButton("Iniciar");
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

					int largo = 20, ancho = 20;

					switch (comboBox.getSelectedIndex()) {
					case 0:
						largo = 20;
						ancho = 20;
						break;
					case 1:
						largo = 25;
						ancho = 25;
						break;
					case 2:
						largo = 27;
						ancho = 27;
						break;
					}
					new VentanaTablero(nombres, largo, ancho);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnIniciar.setBounds(383, 327, 127, 23);
		contentPane.add(btnIniciar);
		setVisible(true);
	}
}
