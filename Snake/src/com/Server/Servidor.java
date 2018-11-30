package com.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.jboss.logging.Logger;

import com.Database.Puntaje;
import com.Database.SqliteConection;

public class Servidor {
	private Connection connection = null;

	public static void main(String[] args) {
		Logger log = Logger.getLogger(Servidor.class);
		int puerto = 1234;
		int maximoConexiones = 10; // Maximo de conexiones simultaneas
		ServerSocket servidor = null;
		Socket socket = null;
		// MensajesChat mensajes = new MensajesChat();

		try {
			// Se crea el serverSocket
			servidor = new ServerSocket(puerto, maximoConexiones);

			// Bucle infinito para esperar conexiones
			while (true) {
				socket = servidor.accept();
				
				System.out.println("cliente conectado");
				ConexionCliente cc = new ConexionCliente(socket);
				cc.start();

			}
		} catch (IOException ex) {
			log.error("Error: " + ex.getMessage());
		} finally {
			try {
				socket.close();
				servidor.close();
			} catch (IOException ex) {
				log.error("Error al cerrar el servidor: " + ex.getMessage());
			}
		}
	}

	public Puntaje buscarPuntaje(String user) {
		Puntaje puntaje = null;
		String query = "SELECT * FROM Usuarios WHERE user=?";

		try {
			connection = SqliteConection.dbConector();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user);
			
			ResultSet rs = pst.executeQuery();
			
			puntaje = new Puntaje(user, rs.getInt("wins"), rs.getInt("defeats"));
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return puntaje; 
	}
	
	public int buscarUsuario(String user, String pass) {
		//String query = "SELECT * FROM Usuarios WHERE user=? AND pass=?";
		String query = "SELECT * FROM Usuarios WHERE user=?";

		try {
			connection = SqliteConection.dbConector();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user);
			//pst.setString(2, pass);
			
			ResultSet rs = pst.executeQuery();
			int res = 0;

			if (rs.next())
				res++;

			if(res == 0) {//usuario inexistente
				rs.close();
				pst.close();
				return 2;
			}
			
			query = "SELECT * FROM Usuarios WHERE user=? AND pass=?";
			pst = connection.prepareStatement(query);
			pst.setString(1, user);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			
			if (rs.next())
				res++;
			
			rs.close();
			pst.close();
			
				return res > 0 ? 1 : 0;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return 0;
		}
	}
	
	public boolean registrarUsuario(String user, String pass) {
		String query = "INSERT INTO Usuarios(user, pass) VALUES(?, ?);";

		try {
			connection = SqliteConection.dbConector();
			PreparedStatement pst = connection.prepareStatement(query);	
			pst.setString(1, user);
			pst.setString(2, pass);
			pst.executeUpdate();
			
			pst.close();
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
}
