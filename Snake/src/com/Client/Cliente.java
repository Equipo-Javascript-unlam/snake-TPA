package com.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import org.jboss.logging.Logger;

import com.Server.DatoComunicacion;

public class Cliente {
	private static Logger log = Logger.getLogger(Cliente.class);
	private static Socket socket;
	private static ObjectOutputStream salidaDatos;
	private static ObjectInputStream entradaDatos;
	private int puerto = 10000;
	private String host = "127.0.0.1";

	public Cliente() {
		// Se crea el socket para conectar con el Sevidor del Chat
		try {
			socket = new Socket(host, puerto);
			salidaDatos = new ObjectOutputStream(socket.getOutputStream());
			entradaDatos = new ObjectInputStream(socket.getInputStream());
		} catch (UnknownHostException ex) {
			log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		} catch (IOException ex) {
			log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
		}
	}

	public static boolean loguear(Usuario user) {
		try {
			salidaDatos.writeObject(user);
			String message = entradaDatos.readUTF();
			
			if(message.equals("error"))
				return false;
		} catch (IOException e) {
			log.error("Error al enviar el usuario" + e.getMessage() + "");
		}
		
		return true;
	}

	public static DatoComunicacion recibirRespuestaServidor() throws ClassNotFoundException, IOException {
		DatoComunicacion data;

		entradaDatos = new ObjectInputStream(socket.getInputStream());
		data = (DatoComunicacion) entradaDatos.readObject();
		return data;
	}

//	public void recibirMensajesServidor() {
//		// Obtiene el flujo de entrada del socket
//		ObjectInputStream entradaDatos = null;
//
//		try {
//			entradaDatos = new ObjectInputStream(socket.getInputStream());
//		} catch (IOException ex) {
//			log.error("Error al crear el stream de entrada: " + ex.getMessage());
//		} catch (NullPointerException ex) {
//			log.error("El socket no se creo correctamente. ");
//		}
//
//		// Bucle infinito que recibe mensajes del servidor
//		boolean conectado = true;
//		while (conectado) {
//			try {
//				//data = (DatoComunicacion) entradaDatos.readObject();
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
//	}

	public static void enviarData(DatoComunicacion data) {
		try {
			salidaDatos.writeObject(data);
		} catch (IOException e) {
			log.error("Error al enviar datos: " + e.getMessage() + ".");
		}
		
	}

	public static void main(String[] args) {

		// Connection conn = SqliteConection.dbConector();
		Cliente c = new Cliente();
		//c.recibirMensajesServidor();
	}
}
