package com.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import org.jboss.logging.Logger;
import com.Client.Usuario;
import com.Snake.Team.JavaScript.Tablero;
import com.Visual.Snake.Team.JavaScript.NuevaSala;

public class ConexionCliente extends Thread {
	private Logger log = Logger.getLogger(ConexionCliente.class);
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	private ArrayList<Tablero> partidasActivas;
	private NuevaSala sala;
	private ObjectInputStream inServer;
	private ObjectOutputStream outServer;
	private static ArrayList<NuevaSala> SalasEnEspera = new ArrayList<NuevaSala>();
	private boolean enMenu = false;

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
		boolean usuarioValido = false;
		while (conectado) {
			try {
				while (!usuarioValido) {
					Usuario user = (Usuario) in.readObject();
					if (Servidor.buscarUsuario(user.getUser(), user.getPass()) == 1) {
						out.writeObject(1);
						usuarioValido = true;
						enMenu = true;
					} else
						out.writeObject(0);
				}

				Thread hilo = new Thread(new Runnable() {
					public void run() {
						try {
							while (true) {
								sala = (NuevaSala) in.readObject();
								Servidor.addSalas(sala);
							}

						} catch (ClassNotFoundException | IOException e) {
							e.printStackTrace();
						}
					}
				});

				while (enMenu) {
					out.writeObject(Servidor.listarSalas());
					enMenu = (boolean) in.readObject();
				}

			} catch (IOException | ClassNotFoundException ex) {
				log.info("Cliente con la IP " + socket.getInetAddress().getHostName() + " desconectado.");
				conectado = false;

				try {
					in.close();
					out.close();
				} catch (IOException ex2) {
					log.error("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
