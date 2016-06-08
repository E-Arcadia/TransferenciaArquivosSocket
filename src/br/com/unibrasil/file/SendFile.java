package br.com.unibrasil.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * 
 * @author Camila Lemes
 *
 */
public class SendFile extends Thread {
	
	private File f;
	
	public final static int SOCKET_PORT = 13267;
	public final static String SERVER = "127.0.0.1";
	
	public SendFile(File f){ 
		this.f = f;
	}

	public void run () {
		try {
			System.out.println(new Date() + " Conectando...");
			Socket socket = new Socket(SERVER, SOCKET_PORT);
			System.out.println(new Date() + " Realizando upload do arquivo: " + f.getName() + " na Thread: " + getName());
			
			OutputStream output = socket.getOutputStream();
			OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream());
			
			outputStream.write(f.getName() + "\n");
			outputStream.flush();

			BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String serverStatus = inReader.readLine();

			if (serverStatus.equals("READY")) {
				FileInputStream file = new FileInputStream(f);

				byte[] buffer = new byte[socket.getSendBufferSize()];

				int bytesRead = 0;

				while ((bytesRead = file.read(buffer)) >= 0) {
					output.write(buffer, 0, bytesRead);
				}
				file.close();
			}
			output.close();
			outputStream.close();
			inReader.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
