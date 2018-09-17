package controllers;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.rmi.Remote;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.RFSClient;
import net.miginfocom.swing.MigLayout;
import ui.RemoteFilesPanel;
import ui.SingleRemoteFilePanel;
import remoteobjects.FileMetadata;

public class RemoteFilesController implements ActionListener{

	RemoteFilesPanel view;
	RFSClient model;
	List<FileMetadata> files = new ArrayList<FileMetadata>();
	
	public RemoteFilesController(RFSClient model) {
		this.model = model;
	}
	
	public void setView(RemoteFilesPanel view) {
		this.view = view;
	}
	public RFSClient getModel() {
		return this.model;
	}
	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
		
		FileMetadata file = (FileMetadata) this.view.getFile(e.getActionCommand());
		
//		System.out.println("Nombre Archivo: "+file.getFileName());
//		System.out.println("Creacion: "+file.getCreationDate().toString());
//		System.out.println("Ultimo Acceso: "+file.getLastAccessTime().toString());
//		System.out.println(""+file.getLastModifiedTime().toString());
//		System.out.println("Tamaño: "+file.getSize());
//		System.out.println("Estado:"+file.getStatus());
		

		
		if(file != null) {
			SingleRemoteFileController controller = new SingleRemoteFileController(this.getModel());
			controller.setView(
					new SingleRemoteFilePanel(new JFrame(), true, file, controller)
			);
		}
     }
	


	
	public List<FileMetadata> getAvailableFiles(){		
		return this.getModel().getAvailableFiles();
		
	}
}

@SuppressWarnings("serial") class Dialogz extends JDialog{
	JButton close = new JButton("close");
	
	public Dialogz(JFrame owner,boolean modal, String command) {
		super(owner, modal);
		setSize(800, 400);
		System.out.println(this.getModalityType());
		close.setText(command);
		add(close);
		setLocationRelativeTo(owner);
		setVisible(true);
		
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				closez();
			}
		});
	} 
	
	void closez(){
		setModal(false);
		this.dispose();
		System.out.println("Method Done");
		
	}
	
}