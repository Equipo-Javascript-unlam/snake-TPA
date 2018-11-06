package com.Visual.Snake.Team.JavaScript;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField passTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(75, 26, 143, 14);
		contentPane.add(lblNewLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(75, 41, 143, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JButton loginBtn = new JButton("Ingresar");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameTextField.getText().equals("Javascript") && passTextField.getText().equals("123")) {					
					new Menu();
					dispose();
				}
				else {
					passTextField.setText("");
					System.out.println("Contraseña o usuario invalido");
				}
			}
		});
		
		loginBtn.setBounds(75, 126, 143, 23);
		contentPane.add(loginBtn);
		
		JLabel passwordTextField = new JLabel("Contraseña");
		passwordTextField.setBounds(75, 73, 143, 14);
		contentPane.add(passwordTextField);
		
		passTextField = new JTextField();
		passTextField.setToolTipText("");
		passTextField.setColumns(10);
		passTextField.setBounds(75, 88, 143, 20);
		contentPane.add(passTextField);
		
		JButton btnNewButton = new JButton("Olvide la contraseña");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("...");
			}
		});
		btnNewButton.setBounds(137, 193, 157, 29);
		contentPane.add(btnNewButton);
	}
}
