package br.com.unibrasil.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 
 * @author Camila Lemes
 *
 */
public class ServerFileService extends Thread {

	private Socket socket;
	
	private final static String PATH_DESTINO = "C://Users//Camila//Desktop//MeusArquivosRecebidos//";

	public ServerFileService(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			receberArquivo();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void receberArquivo() throws IOException, FileNotFoundException {
		InputStream input = socket.getInputStream();
		BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedWriter outReader = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

		String filename = inReader.readLine();

		if (!filename.equals("")) {
			outReader.write("READY\n");
			outReader.flush();
			System.out.println(new Date() + " Requisição para fazer upload do arquivo: " + filename
					+ ". Recebido de " + socket.getInetAddress().getHostName());
		}

		FileOutputStream wr = new FileOutputStream(new File(PATH_DESTINO + filename));

		byte[] buffer = new byte[socket.getReceiveBufferSize()];

		int bytesRecebidos = 0;

		while ((bytesRecebidos = input.read(buffer)) >= 0) {
			wr.write(buffer, 0, bytesRecebidos);
		}
		wr.close();
	}

}
