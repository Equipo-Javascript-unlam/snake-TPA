package com.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.jboss.logging.Logger;
import com.Database.Puntaje;
import com.Database.SqliteConection;
import com.Snake.Team.JavaScript.Tablero;

public class Servidor {
	private Connection connection = null;
	private Tablero tablero;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public static void main(String[] args) {
		Servidor server = new Servidor();
		try {
			server.ProcesarTablero();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Servidor() {
		final int PUERTO = 12345;
		Logger log = Logger.getLogger(Servidor.class);

		int maximoConexiones = 10; // Maximo de conexiones simultaneas
		ServerSocket servidor = null;
		Socket socket = null;

		try {
			servidor = new ServerSocket(PUERTO, maximoConexiones);
			System.out.println("Server on");

			while (true) {
				socket = servidor.accept();
				ConexionCliente cc = new ConexionCliente(socket, servidor);
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



	public void ProcesarTablero() throws InterruptedException {
		//va aca?
		TimeUnit.MILLISECONDS.sleep(200);
		
		for (int i = 0; i < tablero.getCantidadSnakes(); i++)
			tablero.getSnake(i).moverse();

		tablero.colision();
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
		// String query = "SELECT * FROM Usuarios WHERE user=? AND pass=?";
		String query = "SELECT * FROM Usuarios WHERE user=?";

		try {
			connection = SqliteConection.dbConector();
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, user);
			// pst.setString(2, pass);

			ResultSet rs = pst.executeQuery();
			int res = 0;

			if (rs.next())
				res++;

			if (res == 0) {// usuario inexistente
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
