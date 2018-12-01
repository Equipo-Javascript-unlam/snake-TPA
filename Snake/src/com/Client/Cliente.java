package com.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.jboss.logging.Logger;

import com.Server.DatoComunicacion;
import com.Visual.Snake.Team.JavaScript.Login;

public class Cliente {
	private static Logger log = Logger.getLogger(Cliente.class);
	private static Socket socket;
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static int puerto = 12345;
	private static String host = "127.0.0.1";

	public Cliente() {
		try {
			socket = new Socket(host, puerto);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException ex) {
			log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		}
	}

	public static boolean loguear(Usuario user) {
		try {
			out.writeObject(user);
			String message = in.readUTF();
			
			if(message.equals("error"))
				return false;
		} catch (IOException e) {
			log.error("Error al enviar el usuario" + e.getMessage() + "");
		}
		
		return true;
	}
	
	public static DatoComunicacion recibirRespuestaServidor() throws ClassNotFoundException, IOException {
		DatoComunicacion data;

		in = new ObjectInputStream(socket.getInputStream());
		data = (DatoComunicacion) in.readObject();
		return data;
	}

	public static void recibirMensajesServidor() {
		DatoComunicacion data;

		new Login();
		// Bucle infinito que recibe mensajes del servidor
		boolean conectado = true;
//		while (conectado) {
//			try {
//				in.readObject();
//			} catch (IOException ex) {
//				log.error("Error al leer el stream de entrada: " + ex.getMessage());
//				conectado = false;
//			} catch (NullPointerException ex) {
//				log.error("El socket no se creo correctamente. ");
//				conectado = false;
//			} catch (ClassNotFoundException e) {
//				log.error("Erro al leer el stream de entrada: " + e.getMessage());
//			}
//		}
	}

	public static void enviarData(DatoComunicacion data) {
		try {
			out.writeObject(data);
		} catch (IOException e) {
			log.error("Error al enviar datos: " + e.getMessage() + ".");
		}
		
	}

	public static void main(String[] args) {

		// Connection conn = SqliteConection.dbConector();
		Cliente c = new Cliente();
		Cliente.recibirMensajesServidor();
	}
}
