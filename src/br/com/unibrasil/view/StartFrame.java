package br.com.unibrasil.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.unibrasil.client.Client;

/**
 * 
 * @author Camila Lemes
 *
 */
public class StartFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private StartFrame frame = this;
	private File[] files;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 252);
		
		setMinimumSize(new Dimension(366, 394));
		setPreferredSize(new Dimension(366, 394));
		setMaximumSize(new Dimension(366, 394));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmBrowse = new JMenuItem("Browse");
		mntmBrowse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UploadPanel(frame);
				pack();
			}
		});
		mnFile.add(mntmBrowse);
		
		JMenuItem mntmSend = new JMenuItem("Send");
		mntmSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (files != null && files.length > 0) {
					new Client().enviarArquivo(files);
					JOptionPane.showMessageDialog(frame, "Enviado");
				} else {
					JOptionPane.showMessageDialog(frame, "Selecione arquivos para enviar.", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnFile.add(mntmSend);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

}
