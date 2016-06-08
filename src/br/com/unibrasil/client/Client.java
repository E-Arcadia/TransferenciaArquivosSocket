package br.com.unibrasil.client;

import java.io.File;
import java.util.Date;

import br.com.unibrasil.file.SendFile;

/**
 * 
 * @author Camila Lemes
 *
 */
public class Client {

	public void enviarArquivo(File[] files) {
		for (File f : files) {
			System.out.println(new Date() + " Arquivo a ser enviado: " + f.getName());
			SendFile send = new SendFile(f);
			send.start();
		}
	}
}
