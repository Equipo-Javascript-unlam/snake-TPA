package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Client.Cliente;
import com.Client.Usuario;
import com.Server.DatoComunicacion;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 5031213658834160191L;
	static String nombre;
	private String pass;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JPasswordField passTextField;
	private JButton loginBtn;
	private JLabel lblNewLabel;
	private JButton forgotButton;
	private JLabel passwordTextField;
	private JButton registerButton;
	private static Cliente cliente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					cliente = new Cliente();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(75, 26, 143, 14);
		contentPane.add(lblNewLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(75, 41, 143, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		loginBtn = new JButton("Ingresar");
		loginBtn.setBounds(75, 126, 143, 23);
		contentPane.add(loginBtn);

		passwordTextField = new JLabel("Contraseña");
		passwordTextField.setBounds(75, 73, 143, 14);
		contentPane.add(passwordTextField);

		passTextField = new JPasswordField();
		passTextField.setToolTipText("");
		passTextField.setColumns(10);
		passTextField.setBounds(75, 88, 143, 20);
		contentPane.add(passTextField);

		forgotButton = new JButton("Olvide la contrase\u00F1a");
		forgotButton.setBounds(137, 193, 157, 29);
		contentPane.add(forgotButton);

		registerButton = new JButton("Registrarse");
		registerButton.setBounds(75, 160, 143, 23);
		contentPane.add(registerButton);

		addListener();
	}

	private void iniciarSesion() {
		nombre = nameTextField.getText();
		pass = String.valueOf(passTextField.getPassword());

		if (nombre.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese un usuario", "Error login", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (pass.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ingrese la contrase\u00F1a", "Error login",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		int state = cliente.loguear(new Usuario(nombre, pass)); 

		if (state == 1) {
			new Menu(nombre);
			dispose();
		} else if(state == 0){
			passTextField.setText("");
			nameTextField.setText("");
			JOptionPane.showMessageDialog(null, "Nombre de usuario o contrase\u00F1a incorrectos", "Error login",
					JOptionPane.WARNING_MESSAGE);
		} else {
			passTextField.setText("");
			nameTextField.setText("");
			JOptionPane.showMessageDialog(null, "Error al conectar con el servidor", "Error login",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// Aca se capturan las distintas acciones en la ventana
	private void addListener() {
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NuevoUsuario();
			}
		});

		passTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});

		nameTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}
		});

		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});

		forgotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("...");
			}
		});
	}
}
