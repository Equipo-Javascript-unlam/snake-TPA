package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.Client.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Menu extends JFrame {

	private static final long serialVersionUID = -5222890400702756488L;
	private JPanel contentPane;
	private JTable tablePartidas;
	private JButton btnCrearSala;
	private JButton btnSalir;
	private JButton btnUnirse;
	private JButton puntajeButton;
	private String nombre;
	private Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu("Test", null);
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
	public Menu(String nombre, Cliente cliente) {
		this.nombre = nombre;
		this.cliente = cliente;

		setTitle("Lista de Salas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		btnCrearSala = new JButton("Nueva Sala ");
		btnCrearSala.setBounds(368, 308, 106, 23);
		contentPane.add(btnCrearSala);

		tablePartidas = new JTable();
		tablePartidas.setBounds(10, 10, 460, 285);
		tablePartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(tablePartidas);

		btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(10, 308, 106, 23);
		btnUnirse.setEnabled(false);
		contentPane.add(btnUnirse);

		puntajeButton = new JButton("Puntaje");
		puntajeButton.setBounds(128, 308, 106, 23);
		contentPane.add(puntajeButton);

		btnSalir = new JButton("Salir");
		btnSalir.setBounds(10, 343, 464, 29);
		contentPane.add(btnSalir);
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarListaDeSalas(false);
			}
		});
		btnRefrescar.setBounds(245, 305, 106, 29);
		contentPane.add(btnRefrescar);
		setVisible(true);

		addListener();
		actualizarListaDeSalas(true);
	}

	private void actualizarListaDeSalas(boolean flag) {
		ArrayList<NuevaSala> salas = cliente.listarSalas(flag);
		tablePartidas.removeAll();

		if (salas != null)
			for (NuevaSala sala : salas) {
				DefaultTableModel model = (DefaultTableModel) tablePartidas.getModel();
				model.addRow(new Object[] { sala.getNombre() });
			}
	}

	private void addListener() {
		puntajeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CuadroPuntuacion(nombre);
			}
		});

		btnCrearSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuevaSala sala = new NuevaSala(nombre, cliente);
				cliente.agregarSala(sala);
				dispose();
			}
		});

		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
