package com.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.jboss.logging.Logger;

import com.Visual.Snake.Team.JavaScript.Login;

public class ConexionCliente extends Thread {
	private Logger log = Logger.getLogger(ConexionCliente.class);
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;

	public ConexionCliente(Socket socket) {
		this.socket = socket;

		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			
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
				String name = in.readUTF();
			} catch (IOException ex) {
				log.info("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
				conectado = false;

				try {
					in.close();
					out.close();
				} catch (IOException ex2) {
					log.error("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
				}
			}
		}
	}
}
