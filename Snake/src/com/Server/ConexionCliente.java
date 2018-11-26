package com.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.jboss.logging.Logger;

public class ConexionCliente extends Thread {
	private Logger log = Logger.getLogger(ConexionCliente.class);
	private DataInputStream entradaDatos;
	private DataOutputStream salidaDatos;
	private Socket socket;

	public ConexionCliente(Socket socket) {
		this.socket = socket;

		try {
			entradaDatos = new DataInputStream(socket.getInputStream());
			salidaDatos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			log.error("Error al crear los stream de entrada y salida : " + ex.getMessage());
		}
	}

	@Override
	public void run() {
		String mensajeRecibido;
		boolean conectado = true;

		while (conectado) {
			try {
				// Lee un mensaje enviado por el cliente
				mensajeRecibido = entradaDatos.readUTF();
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
			}
		}
	}
}
