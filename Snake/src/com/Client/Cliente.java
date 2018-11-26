package com.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;

import org.jboss.logging.Logger;

import com.Database.SqliteConection;

public class Cliente {
	private Logger log = Logger.getLogger(Cliente.class);
	private Socket socket;

	private int puerto = 1234;
	private String host = "127.0.0.1";
	private String usuario;
	
	public Cliente() {
		// Se crea el socket para conectar con el Sevidor del Chat
        try {
            socket = new Socket(host, puerto);
        } catch (UnknownHostException ex) {
            log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        } catch (IOException ex) {
            log.error("No se ha podido conectar con el servidor (" + ex.getMessage() + ").");
        }
	}

	public void recibirMensajesServidor() {
		// Obtiene el flujo de entrada del socket
		DataInputStream entradaDatos = null;
		String mensaje;
		try {
			entradaDatos = new DataInputStream(socket.getInputStream());
		} catch (IOException ex) {
			log.error("Error al crear el stream de entrada: " + ex.getMessage());
		} catch (NullPointerException ex) {
			log.error("El socket no se creo correctamente. ");
		}

		// Bucle infinito que recibe mensajes del servidor
		boolean conectado = true;
		while (conectado) {
			try {
				mensaje = entradaDatos.readUTF();
			} catch (IOException ex) {
				log.error("Error al leer del stream de entrada: " + ex.getMessage());
				conectado = false;
			} catch (NullPointerException ex) {
				log.error("El socket no se creo correctamente. ");
				conectado = false;
			}
		}
	}

	public static void main(String[] args) {

		Connection conn = SqliteConection.dbConector();
//		Cliente c = new Cliente();
//		c.recibirMensajesServidor();
	}
}
