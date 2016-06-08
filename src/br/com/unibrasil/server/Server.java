package br.com.unibrasil.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 
 * @author Camila Lemes
 *
 */
public class Server {
	public final static int SOCKET_PORT = 13267;

	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(SOCKET_PORT);
			while (true) {
				System.out.println(new Date() + " Esperando...");
				try {
					socket = serverSocket.accept();
					System.out.println(new Date() + " Aceitou a conexao: " + socket);
					ServerThread server = new ServerThread(socket);
					server.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}
		}
	}
}
