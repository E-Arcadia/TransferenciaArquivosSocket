package br.com.unibrasil.server;

import java.net.Socket;
import java.util.Date;

import br.com.unibrasil.file.ServerFileService;

/**
 * 
 * @author Camila Lemes
 *
 */
public class ServerThread extends Thread {

	private Socket socket;
	
	public ServerThread (Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		ServerFileService fileService = new ServerFileService(socket);
		fileService.start();
		System.out.println(new Date() + " Server na thread: " + getName());
	}
}
