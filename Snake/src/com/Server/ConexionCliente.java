package com.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.jboss.logging.Logger;

public class ConexionCliente extends Thread {
	private Logger log = Logger.getLogger(ConexionCliente.class);
	private ObjectInputStream entradaDatos;
	private ObjectOutputStream salidaDatos;
	private Socket socket;

	public ConexionCliente(Socket socket) {
		this.socket = socket;

		try {
			entradaDatos = new ObjectInputStream(socket.getInputStream());
			salidaDatos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			log.error("Error al crear los stream de entrada y salida : " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		DatoComunicacion data;
		boolean conectado = true;

		while (conectado) {
			try {
				// Lee un mensaje enviado por el cliente
				data = (DatoComunicacion) entradaDatos.readObject();
			} catch (IOException ex) {
				log.info("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
				conectado = false;
				// Si se ha producido un error al recibir datos del cliente se cierra la
				// conexion con el.
				try {
					entradaDatos.close();
					salidaDatos.close();
				} catch (IOException ex2) {
					log.error("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
				}
			} catch (ClassNotFoundException e) {
				log.error("Error al cerrar los stream de entrada y salida :" + e.getMessage());
			}
		}
	}
}
