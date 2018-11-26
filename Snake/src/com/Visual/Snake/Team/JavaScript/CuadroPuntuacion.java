package com.Visual.Snake.Team.JavaScript;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Database.Puntaje;
import com.Server.Servidor;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CuadroPuntuacion extends JFrame {

	private static final long serialVersionUID = -7667391753383253690L;
	private JPanel contentPane;
	private JButton btnAceptar;

	public CuadroPuntuacion(String nom) {
		setTitle("Puntuacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 254);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Reemplazar una vez creado cliente servidor
		Servidor sv = new Servidor();
		Puntaje puntaje = sv.buscarPuntaje(nom);
		
		JLabel userNameLabel = new JLabel(nom);
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userNameLabel.setBounds(51, 11, 167, 14);
		contentPane.add(userNameLabel);
		
		JLabel lblPartidasGanadas = new JLabel("Partidas ganadas:");
		lblPartidasGanadas.setBounds(10, 55, 114, 14);
		contentPane.add(lblPartidasGanadas);
		
		JLabel lblPartidasPerdidas = new JLabel("Partidas perdidas: ");
		lblPartidasPerdidas.setBounds(10, 94, 114, 14);
		contentPane.add(lblPartidasPerdidas);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(88, 153, 89, 23);
		contentPane.add(btnAceptar);
		
		JLabel winsLabel = new JLabel(puntaje.getVictorias());
		winsLabel.setBounds(147, 55, 128, 14);
		contentPane.add(winsLabel);
		
		JLabel defeatsLabel = new JLabel(puntaje.getDerrotas());
		defeatsLabel.setBounds(147, 94, 128, 14);
		contentPane.add(defeatsLabel);
		setLocationRelativeTo(null);
		setVisible(true);
		addListener();
	}

	private void addListener() {
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
