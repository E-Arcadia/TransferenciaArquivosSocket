package br.com.unibrasil.view;

import java.awt.FlowLayout;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * 
 * @author Camila Lemes
 *
 */
public class UploadPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public UploadPanel(StartFrame frame) {
		
		setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setMultiSelectionEnabled(true);
		
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.APPROVE_OPTION) {
			
			List<File> files = (List<File>) Arrays.asList(fileChooser.getSelectedFiles());
			
			String[] nomes = new String[files.size()];
			int i = 0;
			for (File file : files) {
				nomes[i] = file.getPath();
				i++;
			}
			
			JList<String> jList = new JList<String>(nomes);
			
			JScrollPane jScrollPane = new JScrollPane(jList);
			
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			frame.getContentPane().add(jScrollPane);
			frame.setFiles(fileChooser.getSelectedFiles());
		}
	
	
	}
}
