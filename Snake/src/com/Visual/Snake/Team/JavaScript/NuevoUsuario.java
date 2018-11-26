package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.Server.Servidor;

import java.awt.Component;
import javax.swing.JPasswordField;

public class NuevoUsuario extends JFrame {

	private static final long serialVersionUID = 1411561424786416872L;
	private JPanel contentPane;
	private JTextField newUserTextField;
	private JButton crearButton;
	private JButton cancelarButton;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoUsuario frame = new NuevoUsuario();
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
	public NuevoUsuario() {
		setType(Type.POPUP);
		setTitle("Nuevo Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 305, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		newUserTextField = new JTextField();
		newUserTextField.setBounds(27, 95, 86, 20);
		contentPane.add(newUserTextField);
		newUserTextField.setColumns(10);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(27, 75, 118, 14);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(155, 75, 86, 14);
		contentPane.add(lblContrasea);

		crearButton = new JButton("Crear");
		crearButton.setBounds(27, 147, 89, 23);
		contentPane.add(crearButton);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(155, 147, 89, 23);
		contentPane.add(cancelarButton);

		JLabel lblIngreseUsuarioY = new JLabel("Ingrese usuario y contraseña");
		lblIngreseUsuarioY.setFont(new Font("Arial Black", Font.PLAIN, 14));
		lblIngreseUsuarioY.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseUsuarioY.setBounds(10, 11, 252, 35);
		contentPane.add(lblIngreseUsuarioY);

		passwordField = new JPasswordField();

		passwordField.setBounds(155, 95, 86, 20);
		contentPane.add(passwordField);
		setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { newUserTextField, passwordField, crearButton, cancelarButton }));
		setVisible(true);
		addListener();
	}

	private void crearUsuario() {
		String nombre = newUserTextField.getText();
		String pass = String.valueOf(passwordField.getPassword());

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un nombre de usuario");
			return;
		}

		if (pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese una contrase\u00F1a");
			return;
		}
		// reemplazar esto una vez realizada la conexion cliente servidor
		Servidor sv = new Servidor();

		if (sv.registrarUsuario(nombre, pass)) {
			JOptionPane.showMessageDialog(null, "Usuario registrado con exito");
			dispose();
		}

	}

	private void addListener() {
		passwordField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		newUserTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		crearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crearUsuario();
			}
		});

		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
}
